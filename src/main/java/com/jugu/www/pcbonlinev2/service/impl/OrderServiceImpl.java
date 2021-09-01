package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.jugu.www.pcbonlinev2.aspect.EmailSmsSend;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.common.Result;
import com.jugu.www.pcbonlinev2.domain.dto.*;
import com.jugu.www.pcbonlinev2.domain.dto.order.*;
import com.jugu.www.pcbonlinev2.domain.entity.*;
import com.jugu.www.pcbonlinev2.domain.vo.*;
import com.jugu.www.pcbonlinev2.mapper.OrderMapper;
import com.jugu.www.pcbonlinev2.service.*;
import com.jugu.www.pcbonlinev2.state.constant.State;
import com.jugu.www.pcbonlinev2.utils.RedisUtil;
import com.jugu.www.pcbonlinev2.utils.ThreadSessionLocal;
import com.jugu.www.pcbonlinev2.validator.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("orderService")
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderDO> implements OrderService {

    private final OrderMapper orderMapper;

    private final QuoteService quoteService;

    private final SmlStencilService smlStencilService;

    private final AssemblyService assemblyService;

    private final RedisUtil redisUtil;

    private final UserService userService;

    private final MemberLevelService memberLevelService;

    private final CouponService couponService;

    private final ReceiverAddersService receiverAddersService;

    @Value("${pcbonline.merchant-id}")
    private String merchantId;

    @Value("${pcbonline.business-id}")
    private String businessId;

    @Value("${pcbonline.card-pay-url}")
    private String cardPayUrl;

    @Value("${pcbonline.sign-key}")
    private String signKey;

    @Value("${pcbonline.redirect-url}")
    private String redirectUrl;


    private final RestTemplate restTemplate;


    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper, QuoteService quoteService, SmlStencilService smlStencilService, AssemblyService assemblyService, RedisUtil redisUtil, UserService userService, MemberLevelService memberLevelService, CouponService couponService, ReceiverAddersService receiverAddersService, RestTemplate restTemplate) {
        this.orderMapper = orderMapper;
        this.quoteService = quoteService;
        this.smlStencilService = smlStencilService;
        this.assemblyService = assemblyService;
        this.redisUtil = redisUtil;
        this.userService = userService;
        this.memberLevelService = memberLevelService;
        this.couponService = couponService;
        this.receiverAddersService = receiverAddersService;
        this.restTemplate = restTemplate;
    }

    @Override
    public PageResult<List<OrderDTO>> queryPage(PageQuery<OrderQueryDTO, OrderDO> params) {
        ValidatorUtil.validate(params);

        OrderDO query = new OrderDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<OrderDO> orderDOPage = orderMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<OrderDTO> orderDTOList = Optional.ofNullable(orderDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(OrderDO -> {
                    OrderDTO orderDTO = new OrderDTO();
                    BeanUtils.copyProperties(OrderDO, orderDTO);
                    return orderDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(orderDOPage, orderDTOList);
    }

    /**
     * 下单业务处理
     *
     * @param orderSaveDTO
     * @return
     */
    @EmailSmsSend
    @Override
    public Result saveOrder(OrderSaveDTO orderSaveDTO) {
        QuoteSubtotal subtotal = orderSaveDTO.getSubtotal();
        boolean r = false;
        StringBuilder pns = new StringBuilder();
        //pcb报价
        if (subtotal.getBoardFee() != null && subtotal.getBoardFee().compareTo(new BigDecimal("0")) >= 1) {
            QuoteDO quoteDO = conversionToQuoteDO(orderSaveDTO);
            quoteDO.setGerberName(orderSaveDTO.getFileName());
            quoteDO.setGerberPath(orderSaveDTO.getFileUploadPtah());
            quoteDO.setWeight(subtotal.getTotalWeight());
            quoteDO.setBoardFee(subtotal.getBoardFee());
            quoteDO.setOverworkFee(subtotal.getUrgentFee());
            quoteDO.setEngineeringFee(subtotal.getEngineeringFee());
            quoteDO.setTestCostFee(subtotal.getTestFee());
            quoteDO.setSubtotal(
                    subtotal.getBoardFee()
                            .add(subtotal.getEngineeringFee())
                            .add(subtotal.getTestFee())
                            .add(subtotal.getUrgentFee())
            );
            quoteDO.setTotalFee(quoteDO.getSubtotal());
            quoteDO.setBuildTime(subtotal.getBuildTime());
            log.info("插入pcb报价：【{}】", quoteDO.toString());
            boolean saveQuote = quoteService.save(quoteDO);
            log.info("插入pcb结果【{}】,返回id：[{}]", saveQuote, quoteDO.getId());
            orderSaveDTO.setIsExistPcb(true);
            orderSaveDTO.setPcbId(quoteDO.getId());
            orderSaveDTO.setPno(quoteDO.getProductNo());
            r = saveQuote;
            pns.append(quoteDO.getProductNo()).append(" ");
        }
        if (subtotal.getStencilFee() != null && subtotal.getStencilFee().compareTo(new BigDecimal("0")) >= 1) {
            SmlStencilDO smlStencilDO = conversionToStencilDO(orderSaveDTO);
            log.info("插入钢网报价：【{}】", smlStencilDO.toString());
            boolean saveStencil = smlStencilService.save(smlStencilDO);
            log.info("插入钢网结果【{}】", saveStencil);
            r = saveStencil;
            pns.append(smlStencilDO.getProductNo()).append(" ");
        }
        if (subtotal.getAssemblyFee() != null && subtotal.getAssemblyFee().compareTo(new BigDecimal("0")) >= 1) {
            AssemblyDO assemblyDO = conversionToAssemblyDO(orderSaveDTO);
            //插入价格
            assemblyDO.setTotalAssemblyFee(subtotal.getAssemblyFee());

            log.info("插入贴片信息:[{}]", assemblyDO.toString());
            boolean saveAssembly = assemblyService.save(assemblyDO);
            log.info("插入贴片结果:[{}]", saveAssembly);
            r = saveAssembly;
            pns.append(assemblyDO.getProductNo()).append(" ");
        }
        return Result.builder().isSuccess(r).pns(pns.toString().trim()).msgType(1).build();
    }

    /**
     * 支付后的下单处理业务方法
     *
     * @param paymentParameterDTO 支付业务对象
     */
    @EmailSmsSend
    @Override
    public Result createOrder(PaymentParameterDTO paymentParameterDTO) {
        //转化为do对象
        OrderDO orderDO = conversionToOrderDO(paymentParameterDTO);

        //用户余额支付，更新点数
        if (paymentParameterDTO.getPaymentType() == 4) {
            userService.updatePoints(paymentParameterDTO.getPaymentTotal(), orderDO.getUserId());
        }else if(paymentParameterDTO.getPaymentType() == 5){ //信用卡支付
            log.info("进入使用信用卡支付流程");
            CardPaymentDTO cardPaymentDTO = conversionToCardPaymentDTO(paymentParameterDTO);
            Result result = this.payCard(cardPaymentDTO);
            if (!result.isSuccess()) return  result;
        }
//        int insertOrderResult = orderMapper.insert(orderDO);
        boolean insertOrderResult = this.saveOrUpdate(orderDO);
        StringBuilder pns = new StringBuilder();
        if (insertOrderResult) {
            List<OrderDetails> orderDetailsList = paymentParameterDTO.getOrderDetailsList();
            for (OrderDetails o : orderDetailsList) {
                pns.append(o.getProductNo()).append(" ");
                if (o.getType() == 1) {
                    QuoteDO quoteDO = conversionToPayAfterPcb(o.getId(), orderDO.getId(), orderDO.getOrderno(), orderDO.getCorderNo(), orderDO.getPaymentTime());
                    insertOrderResult = quoteService.updateById(quoteDO);
                    log.info("支付PCB订单后修改状态结果：[{}]", insertOrderResult);
                } else if (o.getType() == 2) {
                    SmlStencilDO stencilDO = conversionToPayAgterSmt(o.getId(), orderDO.getId(), orderDO.getOrderno(), orderDO.getCorderNo(), orderDO.getPaymentTime());
                    insertOrderResult = smlStencilService.updateById(stencilDO);
                    log.info("支付SMT订单后修改状态结果：[{}]", insertOrderResult);
                } else if (o.getType() == 3) {
                    AssemblyDO assemblyDO = conversionToPayAfterAss(o.getId(), orderDO.getId(), orderDO.getOrderno(), orderDO.getCorderNo(), orderDO.getPaymentTime());
                    insertOrderResult = assemblyService.updateById(assemblyDO);
                    log.info("支付贴片订单后修改状态结果：[{}]", insertOrderResult);
                }
            }
        }

        return Result.builder().isSuccess(insertOrderResult).pns(pns.toString().trim()).msgType(2).total(orderDO.getTotalFee()).id(orderDO.getId()).build();
    }

    private CardPaymentDTO conversionToCardPaymentDTO(PaymentParameterDTO paymentParameterDTO) {
        List<OrderDetails> orderDetailsList = paymentParameterDTO.getOrderDetailsList();
        List<ProductInfo> productInfoList = new ArrayList<>();
        List<Goods> goodsList = new ArrayList<>();
        List<Buried> buriedList = new ArrayList<>();

        buriedList.add(new Buried());

        for (OrderDetails o:orderDetailsList) {
            ProductInfo productInfo = new ProductInfo();
            productInfo.setName(o.getProductNo());
            productInfo.setPrice(o.getSubtotal().toString());
            productInfo.setUrl(o.getType().toString());
            if (o.getQty() != null){
                productInfo.setQty(o.getQty());
            }else{
                productInfo.setQty(o.getType().toString());
            }

            productInfoList.add(productInfo);

            Goods goods = new Goods();
            goods.setSku(o.getProductNo());
            goods.setName(o.getProductNo());
            goods.setPrice(o.getSubtotal().toString());
            goods.setQty(o.getType().toString());
            goodsList.add(goods);
        }
        Cust cust = new Cust();
        cust.setIp("149.28.144.221");
        cust.setEmail("6kf6bcat6ps@thrubay.com");
        cust.setPhone("435-577-7055");

        Ship ship = new Ship();
        ship.setFirst_name(paymentParameterDTO.getPayMethodInfo().getFirst_name());
        ship.setLast_name(paymentParameterDTO.getPayMethodInfo().getLast_name());
        ship.setEmail("6kf6bcat6ps@thrubay.com");
        ship.setPhone("435-577-7055");
        ship.setCity("Circleville");
        ship.setAddress("3888 Austin SecretLane");
        ship.setState("Utah");
        ship.setPostcode("84723");
        ship.setCountry("US");



        Bill bill = new Bill();
        bill.setFirst_name(paymentParameterDTO.getPayMethodInfo().getFirst_name());
        bill.setLast_name(paymentParameterDTO.getPayMethodInfo().getLast_name());
        bill.setEmail("6kf6bcat6ps@thrubay.com");
        bill.setPhone("435-577-7055");
        bill.setCity("Circleville");
        bill.setAddress("3888 Austin SecretLane");
        bill.setState("Utah");
        bill.setPostcode("84723");
        bill.setCountry("Us");

        RiskInfo riskInfo = new RiskInfo();
        riskInfo.setGoods(goodsList);
        riskInfo.setTrade(new Trade());
        riskInfo.setDevice(new Device());
        riskInfo.setCust(cust);
        riskInfo.setBuried(buriedList);
        riskInfo.setShip(ship);
        riskInfo.setBill(bill);


        CardPaymentDTO cardPaymentDTO = new CardPaymentDTO();
        cardPaymentDTO.setAmount(paymentParameterDTO.getAmount());
        cardPaymentDTO.setOrderNumber(paymentParameterDTO.getOrderNoBySys());
        cardPaymentDTO.setProductInfo(productInfoList);
        cardPaymentDTO.setPayMethodInfo(paymentParameterDTO.getPayMethodInfo());
        cardPaymentDTO.setRiskInfo(riskInfo);
        cardPaymentDTO.setSettleCurrency("USD");
        return cardPaymentDTO;
    }

    private AssemblyDO conversionToPayAfterAss(Integer id, Integer oid, String orderno, String corderNo, Date paymentTime) {
        AssemblyDO assemblyDO = new AssemblyDO();
        assemblyDO.setId(id);
        assemblyDO.setOrderId(oid);
        assemblyDO.setInvoiceNo(orderno);
        assemblyDO.setOrderNo(corderNo);
        assemblyDO.setOrderTime(paymentTime);
        assemblyDO.setStatus(3);
        return assemblyDO;
    }

    private SmlStencilDO conversionToPayAgterSmt(Integer id, Integer oid, String orderno, String corderNo, Date paymentTime) {
        SmlStencilDO stencilDO = new SmlStencilDO();
        stencilDO.setId(id);
        stencilDO.setOrderId(oid);
        stencilDO.setOrderNo(corderNo);
        stencilDO.setInvoiceNo(orderno);
        stencilDO.setOrderTime(paymentTime);
        stencilDO.setStatus(3);
        return stencilDO;
    }

    private QuoteDO conversionToPayAfterPcb(Integer id, Integer oid, String orderno, String corderNo, Date paymentTime) {
        QuoteDO quoteDO = new QuoteDO();
        quoteDO.setId(id);
        quoteDO.setOrderId(oid);
        quoteDO.setOrderNo(corderNo);
        quoteDO.setInvoiceNo(orderno);
        quoteDO.setOrderTime(paymentTime);
        quoteDO.setStatus(3);
        return quoteDO;
    }

    private OrderDO conversionToOrderDO(PaymentParameterDTO paymentParameterDTO) {
        UserDetailsDTO userInfo = ThreadSessionLocal.getUserInfo();

        OrderDO orderDO = new OrderDO();
        orderDO.setOrderId(paymentParameterDTO.getPayPayOrderId());
        orderDO.setUserId(userInfo.getId());
        orderDO.setBusinessId(userInfo.getBusinessId());
        orderDO.setBusinessName(userInfo.getBusinessName());
        orderDO.setReceiverAddressId(paymentParameterDTO.getReceiverAddressId());
        orderDO.setWeight(paymentParameterDTO.getTotalWeight());
        orderDO.setDestinationCountry(paymentParameterDTO.getCountryName());
        orderDO.setShippingName(paymentParameterDTO.getCourierCompanyName());
        orderDO.setOrderno(paymentParameterDTO.getOrderNoBySys());
        orderDO.setCorderNo(paymentParameterDTO.getOrderNo());
        orderDO.setDisCouponStr(paymentParameterDTO.getDisCouponStr());
        orderDO.setDisMemberStr(paymentParameterDTO.getDisMemberStr());
        orderDO.setPaymentTime(new Date());
        orderDO.setPaymentType(paymentParameterDTO.getPaymentType());
        orderDO.setStatus(1);


        orderDO.setPostFee(paymentParameterDTO.getShipping());
        orderDO.setTotalFee(paymentParameterDTO.getPaymentTotal());
        orderDO.setTotalSubtotal(paymentParameterDTO.getSubtotal());
        orderDO.setAmountFee(paymentParameterDTO.getAmount());

        orderDO.setPaypalFee(paymentParameterDTO.getPaypalFee());

        return orderDO;
    }

    @Override
    public String createOrderNo() {
        String datetime = new SimpleDateFormat("yyyyMMdd").format(new Date());
        return redisUtil.SeqGenerator(datetime, 5, RedisUtil.DEFAULT_EXPIRE);
    }

    @Override
    public PaymentParameterDTO getToPaymentInfo(ToPaymentParameterDTO toPaymentParameterDTO) {
        PaymentParameterDTO result = new PaymentParameterDTO();
        //查询用户积分
        Integer points = userService.getUserPoint(toPaymentParameterDTO.getUserId());
        MemberLevelDO memberLevelDO = memberLevelService.getMemberLevelByPoint(points);
        MemberLevelVO memberLevelVO = new MemberLevelVO().conversionToVo(memberLevelDO);

        //会员优惠
        BigDecimal memberPreferential = new BigDecimal(memberLevelDO.getPreferentialDetail()).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
        BigDecimal member = memberPreferential.multiply(toPaymentParameterDTO.getSubtotal()).setScale(2, RoundingMode.HALF_UP);

        BigDecimal totalPrice = toPaymentParameterDTO.getSubtotal().add(toPaymentParameterDTO.getShipping());
        //计算小计
        BigDecimal amount = totalPrice.subtract(member);

        //payPal手续费
        BigDecimal payPalFee = amount.multiply(new BigDecimal("0.044")).add(new BigDecimal("0.3")).setScale(2, RoundingMode.HALF_UP);

        //总计
        BigDecimal paymentTotal = amount.add(payPalFee);

        //查找当前可用优惠券
        List<CouponDO> couponDOList = couponService.getValidCoupon(toPaymentParameterDTO.getUserId());
        //数据转换
        List<CouponVO> couponVOList = Optional.ofNullable(couponDOList)
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(data -> {
                    CouponVO couponVO = new CouponVO();
                    BeanUtils.copyProperties(data, couponVO);
                    return couponVO;
                })
                .collect(Collectors.toList());
        result.setSubtotal(toPaymentParameterDTO.getSubtotal());
        result.setShipping(toPaymentParameterDTO.getShipping());
        result.setDisMemberStr(member.toString());
        result.setTotalPrice(totalPrice);
        result.setMemberLevelVO(memberLevelVO);
        result.setAmount(amount);
        result.setPaypalFee(payPalFee);
        result.setPaymentTotal(paymentTotal);
        result.setOrderNoBySys(createOrderNo());
        result.setCouponVOList(couponVOList);
        result.setTotalWeight(toPaymentParameterDTO.getTotalWeight());
        result.setReceiverAddressId(toPaymentParameterDTO.getReceiverAddressId());
        return result;
    }

    @Override
    public InvoiceInfoVO getInvoiceInfo(Integer orderId) {
        OrderDO orderDO = this.getById(orderId);
        List<QuoteDO> quoteDOList = quoteService.list(new QueryWrapper<QuoteDO>().eq("order_id", orderId));
        List<SmlStencilDO> smlStencilDOList = smlStencilService.list(new QueryWrapper<SmlStencilDO>().eq("order_id", orderId));
        List<AssemblyDO> assemblyDOList = assemblyService.list(new QueryWrapper<AssemblyDO>().eq("order_id", orderId));
        ReceiverAddersDO receiverAddersDO = receiverAddersService.getById(orderDO.getReceiverAddressId());

        //转换为vo
        List<QuoteVO> quoteVOList = Optional.ofNullable(quoteDOList)
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(data -> {
                    QuoteVO quoteVO = new QuoteVO();
                    BeanUtils.copyProperties(data, quoteVO);
                    return quoteVO;
                }).collect(Collectors.toList());
        List<SmlStencilVO> smlStencilVOList = Optional.ofNullable(smlStencilDOList)
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(data -> {
                    SmlStencilVO smlStencilVO = new SmlStencilVO();
                    BeanUtils.copyProperties(data, smlStencilVO);
                    return smlStencilVO;
                }).collect(Collectors.toList());
        List<AssemblyVO> assemblyVOList = Optional.ofNullable(assemblyDOList)
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(data -> {
                    AssemblyVO assemblyVO = new AssemblyVO();
                    BeanUtils.copyProperties(data, assemblyVO);
                    return assemblyVO;
                }).collect(Collectors.toList());
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(orderDO, orderVO);
        ReceiverAddersVO receiverAddersVO = new ReceiverAddersVO();
        BeanUtils.copyProperties(receiverAddersDO, receiverAddersVO);

        return InvoiceInfoVO.builder()
                .quoteVOList(quoteVOList)
                .smlStencilVOS(smlStencilVOList)
                .assemblyVOList(assemblyVOList)
                .orderVO(orderVO)
                .receiverAddersVO(receiverAddersVO).build();
    }

    @Override
    public Result payCard(CardPaymentDTO cardPaymentDTO) {
        Gson gson = new Gson();
        //封装请求实体
        HttpEntity<MultiValueMap<String, String>> requestEntity = fullPayRequestEntity(cardPaymentDTO);
        log.info("发送请求实体：[{}]", requestEntity.toString());
        //发送交易授权信息
        ResponseEntity<String> entity = restTemplate.postForEntity(cardPayUrl, requestEntity, String.class);

        String entityBody = entity.getBody();
        log.info("支付授权请求返回:[{}]",entityBody);

        CardPayAuthResponse cardPayAuthResponse = gson.fromJson(entityBody, CardPayAuthResponse.class);
        log.info("序列化为javabean结果:[{}]",cardPayAuthResponse);

        if ("1".equals(cardPayAuthResponse.getStatus()) && "0000".equals(cardPayAuthResponse.getResp_code())){
            //发送交易请款
            HttpEntity<MultiValueMap<String, String>> captureRequestEntity = fullCaptureRequestEntity(cardPayAuthResponse);
            ResponseEntity<String> captureResponse = restTemplate.postForEntity(cardPayUrl, captureRequestEntity, String.class);
            log.info("capture请求返回:[{}]",captureResponse.getBody());

            CardPayAuthResponse result = gson.fromJson(captureResponse.getBody(), CardPayAuthResponse.class);
            if ("1".equals(result.getStatus()) && "0000".equals(result.getResp_code())){
                return Result.builder().isSuccess(true).build();
            }else{
                log.info("支付授权发送成功，请款交易失败");
                return Result.builder().isSuccess(false).errorMsg("晶粒信用卡支付授权成功，请款交易失败！详细:code->"+result.getResp_code()+",msg->"+result.getResp_msg()).build();
            }
        }else {
            if (cardPayAuthResponse.getCode() != null && "1002".equals(cardPayAuthResponse.getCode())){
                return Result.builder().isSuccess(false).errorMsg("晶粒信用卡授权请求参数无效,详细: code->"+cardPayAuthResponse.getCode()+",msg->"+cardPayAuthResponse.getMsg()).build();
            }
            return Result.builder().isSuccess(false).errorMsg("晶粒信用卡支付授权请求失败,详细:code->"+cardPayAuthResponse.getResp_code()+",msg->"+cardPayAuthResponse.getResp_msg()).build();
        }


    }

    @Override
    public boolean auditOrderDetails(List<OrderDetails> orderDetailsList) {
        //for (OrderDetails o:orderDetailsList) {
        //    if (o.getType() == 1){
        //        QuoteDO quoteDO = new QuoteDO();
        //        quoteDO.setId(o.getId());
        //        quoteDO.setStatus();
        //    }else if(o.getType() == 2){
        //
        //    }else if(o.getType() == 3){
        //
        //    }
        //}

        return false;
    }

    private HttpEntity<MultiValueMap<String,String>> fullCaptureRequestEntity(CardPayAuthResponse cardPayAuthResponse) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("version", "3.0");
        params.add("merchant_id", merchantId);
        params.add("business_id", businessId);
        params.add("access_type", "s2s");
        params.add("trans_channel", "");
        params.add("original_order_id", cardPayAuthResponse.getOrder_id());
        params.add("trans_type", "capture");
        params.add("amount", "");
        params.add("notify_url", "");
        params.add("req_reserved", "");
        params.add("reserved", "");
        params.add("sign_type", "MD5");

        String ss = "3.0"+merchantId+businessId+"s2s"+cardPayAuthResponse.getOrder_id()+"captureMD5"+signKey;
        log.info("加密前:[{}]", ss);
        String sign = DigestUtils.md5DigestAsHex(ss.getBytes());
        log.info("加密后:[{}]",sign);

        params.add("sign",sign);

        return new HttpEntity<>(params, headers);

    }

    private HttpEntity<MultiValueMap<String, String>> fullPayRequestEntity(CardPaymentDTO cardPaymentDTO) {
        HttpHeaders headers = new HttpHeaders();
        Gson gson = new Gson();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //避免403
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("version", "3.0");
        params.add("merchant_id", merchantId);
        params.add("business_id", businessId);
        params.add("access_type", "s2s");
        params.add("order_number", cardPaymentDTO.getOrderNumber());
        params.add("trans_type", "authorization");
        params.add("trans_channel", "cc");
        params.add("pay_method", "normal");
        params.add("trans_timeout", "60");
        params.add("url", "www.test.com");
        params.add("country", "US");
        params.add("language", "zh");
        params.add("currency", "USD");
        params.add("amount", cardPaymentDTO.getAmount().toString());
        params.add("settle_currency", cardPaymentDTO.getSettleCurrency());
        params.add("product_info", gson.toJson(cardPaymentDTO.getProductInfo()));
        params.add("pay_method_info", gson.toJson(cardPaymentDTO.getPayMethodInfo()));
        params.add("terminal_type", "0");
        params.add("risk_info", gson.toJson(cardPaymentDTO.getRiskInfo()));
        params.add("redirect_url", redirectUrl);
        params.add("sign_type", "MD5");
        params.add("skin_code","");
        params.add("logo","");
        params.add("dcc","");
        params.add("notify_url", "");
        params.add("req_reserved","");
        params.add("reserved","");



        String ss = merchantId + businessId + cardPaymentDTO.getOrderNumber() + "authorizationccnormalwww.test.comUSD" + cardPaymentDTO.getAmount() + cardPaymentDTO.getSettleCurrency() + signKey;
        log.info("加密前:[{}]", ss);
        String sign = DigestUtils.md5DigestAsHex(ss.getBytes());
        log.info("加密后:[{}]", sign);
        params.add("sign", sign);//加密
        return new HttpEntity<>(params, headers);
    }

    private AssemblyDO conversionToAssemblyDO(OrderSaveDTO orderSaveDTO) {
        AssemblyDO assemblyDO = new AssemblyDO();
        AssemblyFieldDTO assemblyField = orderSaveDTO.getAssemblyField();

        BeanUtils.copyProperties(assemblyField, assemblyDO);

        //登录后的信息
        UserDetailsDTO userDetailsDTO = ThreadSessionLocal.getUserInfo();

        if (orderSaveDTO.getIsExistPcb()) {
            assemblyDO.setProductNo(orderSaveDTO.getPno() + "a");
            assemblyDO.setQuoteId(orderSaveDTO.getPcbId());
        } else {
            assemblyDO.setProductNo(redisUtil.SeqGenerator(userDetailsDTO.getUserSystemId(), 5, RedisUtil.NOT_EXPIRE) + "aa");
            if (StringUtils.isEmpty(orderSaveDTO.getPno())) orderSaveDTO.setPno(assemblyDO.getProductNo());
        }

        assemblyDO.setUserId(userDetailsDTO.getId());
        assemblyDO.setGerberName(orderSaveDTO.getFileName());
        assemblyDO.setGerberPath(orderSaveDTO.getFileUploadPtah());
        assemblyDO.setBusinessId(userDetailsDTO.getBusinessId());
        assemblyDO.setBusinessName(userDetailsDTO.getBusinessName());

        if (userDetailsDTO.getAuditMark() == 1) {
            assemblyDO.setStatus(State.FORMAL_ORDER.status);
        } else {
            assemblyDO.setStatus(State.TEMPORARY_ORDER.status);
        }

        return assemblyDO;
    }

    private SmlStencilDO conversionToStencilDO(OrderSaveDTO orderSaveDTO) {
        SmlStencilDO stencilDO = new SmlStencilDO();
        StencilField stencilField = orderSaveDTO.getStencilField();
        QuoteStencil quoteStencil = stencilField.getDetailed();

        stencilDO.setQuantity(stencilField.getQuantity());
        stencilDO.setThickness(stencilField.getThickness());
        stencilDO.setExistingFiducials(stencilField.getExistingFiducials());

        stencilDO.setStencilType(quoteStencil.getMaterialName());
        stencilDO.setSize(quoteStencil.getId());
        stencilDO.setStencilSizeX(quoteStencil.getStencilSizex().toString());
        stencilDO.setStencilSizeY(quoteStencil.getStencilSizey().toString());
        stencilDO.setStencilAreaX(quoteStencil.getStencilAreax().toString());
        stencilDO.setStencilAreaY(quoteStencil.getStencilAreay().toString());
        stencilDO.setWeight(quoteStencil.getWeight());

        stencilDO.setTotalStencilFee(quoteStencil.getPriceToUSD().multiply(new BigDecimal(stencilField.getQuantity())));

        //登录后的信息
        UserDetailsDTO userDetailsDTO = ThreadSessionLocal.getUserInfo();

        if (orderSaveDTO.getIsExistPcb()) {
            stencilDO.setProductNo(orderSaveDTO.getPno() + "s");
            stencilDO.setQuoteId(orderSaveDTO.getPcbId());
        } else {
            stencilDO.setProductNo(redisUtil.SeqGenerator(userDetailsDTO.getUserSystemId(), 5, RedisUtil.NOT_EXPIRE) + "s");
            orderSaveDTO.setPno(stencilDO.getProductNo());
        }

        stencilDO.setUserId(userDetailsDTO.getId());
        stencilDO.setBusinessId(userDetailsDTO.getBusinessId());
        stencilDO.setBusinessName(userDetailsDTO.getBusinessName());
        stencilDO.setGerberName(orderSaveDTO.getFileName());
        stencilDO.setGerberPath(orderSaveDTO.getFileUploadPtah());

        if (userDetailsDTO.getAuditMark() == 1) {
            stencilDO.setStatus(State.FORMAL_ORDER.status);
        } else {
            stencilDO.setStatus(State.TEMPORARY_ORDER.status);
        }

        return stencilDO;
    }


    private QuoteDO conversionToQuoteDO(OrderSaveDTO orderSaveDTO) {
        QuoteDO quote = new QuoteDO();
        PcbSizeField pcbSizeField = orderSaveDTO.getPcbSizeField();
        int boardType = pcbSizeField.getBoardType().equals("Single") ? 1 : 2;
        quote.setBoardType(boardType);
        quote.setRemark(orderSaveDTO.getRemark());
        // TODO: 2020-08-27 需要计算
        if (boardType == 2) { //拼板需要计算p
            quote.setQuantityPanel(pcbSizeField.getQuantity());
            quote.setQuantityPcs(Integer.valueOf(pcbSizeField.getPanelSize().getSizeY()) * Integer.valueOf(pcbSizeField.getPanelSize().getSizeX()) * Integer.valueOf(pcbSizeField.getQuantity()));
        } else {
            quote.setQuantityPcs(pcbSizeField.getQuantity());
        }
        quote.setAreaSq(computeAreaSq(new BigDecimal(pcbSizeField.getSingleSize().getSizeX()), new BigDecimal(pcbSizeField.getSingleSize().getSizeY()), quote.getQuantityPcs()));
        if (boardType == 1) {
            quote.setDimensionsX(new BigDecimal(pcbSizeField.getSingleSize().getSizeX()));
            quote.setDimensionsY(new BigDecimal(pcbSizeField.getSingleSize().getSizeY()));
        } else {
            quote.setPanelSizeX(new BigDecimal(pcbSizeField.getSingleSize().getSizeX()));
            quote.setPanelSizeY(new BigDecimal(pcbSizeField.getSingleSize().getSizeY()));
            quote.setPanelWayX(Integer.valueOf(pcbSizeField.getPanelSize().getSizeX()));
            quote.setPanelWayY(Integer.valueOf(pcbSizeField.getPanelSize().getSizeY()));
        }

        //常规处理字段
        PcbStandardField pcbStandardField = orderSaveDTO.getPcbStandardField();
//        BeanUtils.copyProperties(pcbStandardField,quote);
        quote.setBgaSize(pcbStandardField.getBgaSize());
        quote.setCti(pcbStandardField.getCti());
        quote.setHeatConductivity(pcbStandardField.getHeatConductivity());
        quote.setPthCopper(pcbStandardField.getHoleCopper());
        quote.setInnerLayerCopper(pcbStandardField.getInnerCopper());
        quote.setLayerNum(Integer.valueOf(pcbStandardField.getLayer().substring(0, 1)));
        quote.setMinHoleSize(new BigDecimal(pcbStandardField.getMinHoleSize()));
        quote.setOuterMinTrack(pcbStandardField.getMinTrack());
        quote.setOuterLayerCopper(pcbStandardField.getOuterCopper());
        quote.setSilkScreenBotColor(pcbStandardField.getSilkscreen());
        quote.setSilkScreenTopColor(pcbStandardField.getSilkscreen());
        quote.setSolderMaskBotColor(pcbStandardField.getSolderMask());
        quote.setSolderMaskTopColor(pcbStandardField.getSolderMask());
        quote.setSurfaceFinish(pcbStandardField.getSurfaceFinish());
        // TODO: 2020-05-19 surfaceThickness
        if (pcbStandardField.getSurfaceFinish().equals("Immersion Gold")) {
            String th = pcbStandardField.getSurfaceThickness().split(" ")[1].split(":")[1];
            quote.setThickness(th.substring(0, th.length() - 1));
        } else {
            quote.setThickness(pcbStandardField.getThickness());
        }
        quote.setTg(pcbStandardField.getTg());

        quote.setPcbType(pcbStandardField.getMaterial());
        quote.setFinishThickness(pcbStandardField.getThickness());


        // TODO: 2020-05-22 特殊处理字段
        PcbSpecialField pcbSpecialField = orderSaveDTO.getPcbSpecialField();
        quote.setBlindHoles(pcbSpecialField.getHdi());
        quote.setEdgePlated(pcbSpecialField.getEdgePlating());
        quote.setHalfHoleLated(pcbSpecialField.getHalfHolePlated());
        quote.setContrlImpeance(pcbSpecialField.getImpedanceControl());

        quote.setPeelMark(pcbSpecialField.getPeelableSolderMask());
        quote.setCarbon(pcbSpecialField.getCarbonMask());

        quote.setBevellingCamfer(pcbSpecialField.getBevellingCamfer());
        quote.setDeepMillRouting(pcbSpecialField.getControlConcaveRouting());

        quote.setBackDrill(pcbSpecialField.getBackDrill());
        quote.setViaInPad(pcbSpecialField.getViaInPad());
        quote.setNegativePostitiveCopper(pcbSpecialField.getNegativePostitiveCopper());
        quote.setCountersinks(pcbSpecialField.getCountersinks());
        quote.setPressHoles(pcbSpecialField.getPressHoles());
        quote.setAcceptableQualityLevels(pcbSpecialField.getAcceptableQualityLevels());

        //登录后的信息
        UserDetailsDTO userDetailsDTO = ThreadSessionLocal.getUserInfo();
        if (userDetailsDTO != null) {
            quote.setUserId(userDetailsDTO.getId());
            quote.setBusinessId(userDetailsDTO.getBusinessId());
            quote.setBusinessName(userDetailsDTO.getBusinessName());
            quote.setProductNo(redisUtil.SeqGenerator(userDetailsDTO.getUserSystemId(), 5, RedisUtil.NOT_EXPIRE) + "a");

            if (userDetailsDTO.getAuditMark() == 1) {
                quote.setStatus(State.FORMAL_ORDER.status);
            } else {
                quote.setStatus(State.TEMPORARY_ORDER.status);
            }
        }


        return quote;
    }

    private BigDecimal computeAreaSq(BigDecimal dimensionsX, BigDecimal dimensionsY, Integer quantityPcs) {
        return dimensionsX.multiply(dimensionsY).multiply(new BigDecimal(quantityPcs)).divide(new BigDecimal("1000000"), 2, RoundingMode.HALF_UP);
    }

}