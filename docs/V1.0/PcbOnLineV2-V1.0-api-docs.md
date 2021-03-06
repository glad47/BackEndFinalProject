> This documentation is generated by [JApiDocs](https://japidocs.agilestudio.cn/).
---
# 订单相关接口
## 下单接口

*作者: turing*

**请求URL**

/api/order/save `POST` 

**请求体**

```json
{
	"pcbSizeField":{
		"boardType":"string",
		"quantity":"int",
		"panelSize":{
			"sizeX":"string【必须】",
			"sizeY":"string【必须】"
		},
		"singleSize":{
			"sizeX":"string【必须】",
			"sizeY":"string【必须】"
		}
	},
	"pcbSpecialField":{
		"acceptableQualityLevels":"boolean",
		"backDrill":"boolean",
		"carbonMask":"boolean",
		"controlConcaveRouting":"boolean",
		"countersinks":"boolean",
		"customStackup":"boolean",
		"edgePlating":"boolean",
		"halfHolePlated":"boolean",
		"hdi":"boolean",
		"impedanceControl":"boolean",
		"negativePostitiveCopper":"boolean",
		"peelableSolderMask":"boolean",
		"pressHoles":"boolean",
		"viaInPad":"boolean",
		"bevellingCamfer":"boolean"
	},
	"pcbStandardField":{
		"bgaSize":"string",
		"cti":"string",
		"heatConductivity":"string",
		"holeCopper":"string",
		"innerCopper":"string",
		"layer":"string",
		"material":"string",
		"minHoleSize":"string",
		"minTrack":"string",
		"outerCopper":"string",
		"silkscreen":"string",
		"solderMask":"string",
		"surfaceFinish":"string",
		"surfaceThickness":"string",
		"tg":"string",
		"thickness":"string"
	},
	"subtotal":{
		"boardFee":"double",
		"engineeringFee":"double",
		"shippingFee":"double",
		"testFee":"double",
		"totalWeight":"double",
		"urgentFee":"double",
		"stencilFee":"double",
		"assemblyFee":"double",
		"buildTime":"string"
	},
	"stencilField":{
		"quantity":"int",
		"stencilSide":"string",
		"thickness":"string",
		"existingFiducials":"string",
		"dimensions":"string",
		"detailed":{
			"id":"int",
			"stencilSizex":"int",
			"stencilSizey":"int",
			"stencilAreax":"int",
			"stencilAreay":"int",
			"price":"double",
			"priceToUSD":"double",
			"weight":"double",
			"materialName":"string"
		}
	},
	"assemblyField":{
		"assemblySide":"string",
		"assemblyType":"int",
		"quantity":"int",
		"smtPartNum":"int",
		"throughHolePartNum":"int",
		"uniquePartNum":"int"
	},
	"isExistPcb":"boolean //是否存在pcb",
	"pcbId":"int //pcbid",
	"pno":"string //客户型号",
	"fileName":"string //gerber文件名【必须】",
	"fileUploadPtah":"string //gerber上传后路径【必须】"
}
```

**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":{}
}
```
## 创建系统订单编号及计算基本价格信息和优惠券信息

*作者: turing*

**请求URL**

/api/order/createOrderNo `POST` 

**请求体**

```json
{
	"receiverAddressId":"int【必须】",
	"shipping":"double【必须】",
	"totalWeight":"string【必须】",
	"subtotal":"double【必须】",
	"userId":"int"
}
```

**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":{}
}
```
## 支付后回调接口

*作者: turing*

**请求URL**

/api/order/save `POST` 

**请求体**

```json
{
	"orderDetailsList":[{
		"id":"int【必须】",
		"productNo":"string",
		"oType":"int【必须】",
		"weight":"double",
		"subtotal":"double"
	}],
	"totalPrice":"double //此次订单的积分数",
	"shipping":"double //总邮费【必须】",
	"subtotal":"double //总合计(多个报价项的合计和)【必须】",
	"courierCompanyName":"string //物流名称【必须】",
	"countryName":"string //运送到那个国家【必须】",
	"totalWeight":"string //总重量【必须】",
	"orderNo":"string //客户填写的订单号，可为空",
	"isUseCoupon":"int //是否使用优惠劵 默认0没有 1使用",
	"disCouponStr":"string //优惠金额(字符串类型：-10)",
	"disMemberStr":"string //会员优惠金额",
	"couponId":"int //券id",
	"paymentTotal":"double //实际支付金额=(paypalFee + amount)【必须】",
	"paypalFee":"double //paypal手续费【必须】",
	"amount":"double //扣除优惠后的总价格【必须】",
	"orderNoBySys":"string //生成的order订单号【必须】",
	"payPayOrderId":"string //payPay返回的orderId【必须】",
	"receiverAddressId":"int //送货地址id【必须】",
	"paymentType":"int //支付类型1->payPal、2->BankTransfer、3->WesternUnion、4->PayWithAccountBalance【必须】",
	"couponVOList":[{
		"id":"int //id",
		"couponCode":"string //优惠券id",
		"couponMoney":"int //优惠券金额",
		"startTime":"date //有效开始时间",
		"endTime":"date //有效结束时间",
		"couponStatus":"int //劵状态"
	}],
	"memberLevelVO":{
		"id":"int //id",
		"levelMember":"string //等级",
		"preferentialDetail":"int //优惠值(1整数)",
		"membersStr":"string //优惠百分比"
	}
}
```

**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":{}
}
```
## 发票信息查询接口

*作者: turing*

**请求URL**

/api/order/invoice `GET` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
orderId|int|是|订单id

**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":{}
}
```
# 帮助主题接口
## 查询帮助信息

*作者: turing*

**请求URL**

/api/articlehelp/queryPage `GET` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
pageNo|int|是|页码
pageSize|int|是|显示多少条
id|int|否|
title|string|否|
classifyOne|string|否|
classifyTwo|string|否|
content|string|否|
gmtCreate|date|否|
gmtModified|date|否|

**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":{
		"pageNo":"int //当前页号",
		"pageSize":"int //每页行数",
		"total":"long //总记录数",
		"pageNum":"long //总页数",
		"data":{}
	}
}
```
# 统计接口
## 统计各类型订单各个月份销售额

**

**请求URL**

/api/stat/orderAmount `GET` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
currYears|string|是|年份

**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":[{
		"name":"string",
		"time":"string",
		"amount":"double"
	}]
}
```
# 系统消息通知接口
## 查询用户系统消息

*作者: turing*

**请求URL**

/api/message/queryCurrUserMsg `GET` `POST` 


**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":{}
}
```
## 标记消息为已读

*作者: turing*

**请求URL**

/api/message/readMsg/{id} `PUT` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
id|int|是|消息id

**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":{}
}
```
# 授权接口
## 登录接口

**

**请求URL**

/api/auth/login `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
username|string|是|用户邮箱
password|string|是|用户密码
recaptchaResponse|string|是|google校验码 reCaptcha,返回的g-recaptcha-response

**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":{}
}
```
## google登录接口

**

**请求URL**

/api/auth/googlelogin `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
gid|string|是|google授权后的id
username|string|否|用户名
email|string|是|用户邮箱
favicon|string|否|用户头像

**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":{}
}
```
## 注册接口

**

**请求URL**

/api/auth/register `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
username|string|是|用户名
password|string|是|密码
recaptchaResponse|string|是|google校验码 reCaptcha,返回的g-recaptcha-response
invite|string|否|邀请码
from|string|否|来自那个页面标识

**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":{}
}
```
## 激活接口

**

**请求URL**

/api/active `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
token|string|是|token

**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":{}
}
```
## 发送重置密码邮件接口

**

**请求URL**

/api/requestPasswordResetByEmail `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
email|string|是|邮箱
recaptchaResponse|string|是|google校验码 reCaptcha,返回的g-recaptcha-response

**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":{}
}
```
## 重置密码接口

**

**请求URL**

/api/requestPasswordReset `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
token|string|是|token
nowPwd|string|是|新密码

**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":{}
}
```
## 首页发送联系邮件接口

**

**请求URL**

/api/sendContactEmail `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
email|string|是|邮箱
name|string|是|名字
msg|string|是|信息

**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":{}
}
```
## feedback发送反馈邮件接口

**

**请求URL**

/api/sendFeedbackReviewEmail `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
email|string|是|邮箱
name|string|是|名字
msg|string|是|信息
recaptchaResponse|string|是|google校验码 reCaptcha,返回的g-recaptcha-response

**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":{}
}
```
# 优惠券接口
## 查询当前用户的所有优惠券信息

*作者: turing*

**请求URL**

/api/coupon/queryPage `GET` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
userId|int|否|
couponStatus|int|否|

**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":{}
}
```
## 生成优惠券

*作者: turing*

**请求URL**

/api/coupon/generate `GET` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
flag|int|是|对应优惠券规则表code_flag字段

**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":{}
}
```
## 检验及兑换优惠券

*作者: turing*

**请求URL**

/api/coupon/verify `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
code|string|是|优惠码

**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":{}
}
```
# 生产日志接口
## 查询生产日志信息

*作者: turing*

**请求URL**

/api/productionlog/queryPage `GET` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
productId|int|是|对应pcb的id

**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":{}
}
```
# 文件管理接口
## 上传图片

**

**请求URL**

/api/file/upload/img `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
file|file|是|图片文件

**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":{}
}
```
## 上传压缩包

**

**请求URL**

/api/file/upload/zip `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
file|file|是|压缩包文件

**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":{}
}
```
## 下载压缩包

**

**请求URL**

/api/file/download/zip `GET` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
filename|string|否|文件上传的路径名

**返回结果**

```json
{}
```
# 国家信息接口
## 查询所有国家

*作者: turing*

**请求URL**

/api/country/all `GET` 


**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":[{
		"id":"int //id",
		"name":"string //name",
		"fbCountryCode":"string //简码"
	}]
}
```
# 报价订单接口
## save

*作者: turing*

**请求URL**

/api/quote/save `POST` 


**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":{}
}
```
## update

*作者: turing*

**请求URL**

/api/quote/{id} `PUT` 


**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":{}
}
```
## delete

*作者: turing*

**请求URL**

/api/quote/{id} `DELETE` 


**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":{}
}
```
## queryPage

*作者: turing*

**请求URL**

/api/quote/query `POST` 


**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":{
		"pageNo":"int //当前页号",
		"pageSize":"int //每页行数",
		"total":"long //总记录数",
		"pageNum":"long //总页数",
		"data":{}
	}
}
```
# 收货地址
## 新增收货地址信息

*作者: turing*

**请求URL**

/api/receiveraddress/save `POST` 

**请求体**

```json
{
	"id":"int",
	"userId":"int",
	"receiverCompany":"string",
	"receiverEmail":"string",
	"receiverName":"string【必须】",
	"receiverTelephone":"string【必须】",
	"receiverCountry":"string【必须】",
	"receiverCountryName":"string",
	"receiverCity":"string【必须】",
	"receiverPostcode":"string【必须】",
	"receiverAddress":"string【必须】",
	"gmtModified":"date",
	"gmtCreate":"date",
	"isDefault":"int",
	"courierName":"string",
	"courierAccount":"string"
}
```

**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":{}
}
```
## 修改收货地址信息

*作者: turing*

**请求URL**

/api/receiveraddress/{id} `PUT` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
id|int|是|id
**请求体**

```json
{
	"id":"int",
	"userId":"int",
	"receiverCompany":"string",
	"receiverEmail":"string",
	"receiverName":"string【必须】",
	"receiverTelephone":"string【必须】",
	"receiverCountry":"string【必须】",
	"receiverCountryName":"string",
	"receiverCity":"string【必须】",
	"receiverPostcode":"string【必须】",
	"receiverAddress":"string【必须】",
	"gmtModified":"date",
	"gmtCreate":"date",
	"isDefault":"int",
	"courierName":"string",
	"courierAccount":"string"
}
```

**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":{}
}
```
## delete

*作者: turing*

**请求URL**

/api/receiveraddress/{id} `DELETE` 


**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":{}
}
```
## 查询收货地址

*作者: turing*

**请求URL**

/api/receiveraddress/queryPage `GET` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
pageNo|int|是|页码
pageSize|int|是|显示多少条
id|int|否|
userId|int|否|
receiverCompany|string|否|
receiverEmail|string|否|
receiverName|string|否|
receiverTelephone|string|否|
receiverCountry|string|否|
receiverCountryName|string|否|
receiverCity|string|否|
receiverPostcode|string|否|
receiverAddress|string|否|
gmtModified|date|否|
gmtCreate|date|否|
isDefault|int|否|
courierName|string|否|
courierAccount|string|否|

**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":{
		"pageNo":"int //当前页号",
		"pageSize":"int //每页行数",
		"total":"long //总记录数",
		"pageNum":"long //总页数",
		"data":{}
	}
}
```
## 查询当前用户默认地址信息

*作者: turing*

**请求URL**

/api/receiveraddress/curr `GET` 


**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":{}
}
```
# 用户管理接口
## 查询用户信息

**

**请求URL**

/api/users/query `GET` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
pageNo|int|是|页码
pageSize|int|是|显示多少条
id|int|否|
userName|string|否|
password|string|否|
email|string|否|
skypeId|string|否|
googleId|string|否|
facebookId|string|否|
mobilePhone|string|否|
address|string|否|
country|string|否|
city|string|否|
businessType|string|否|
jobrole|string|否|
applications|string|否|
invalidMark|int|否|
favicon|string|否|
gmtCreate|date|否|
gmtModified|date|否|
uuid|string|否|
userSystemId|string|否|
businessId|int|否|
orderNumNo|int|否|
facebookLoginId|string|否|
googleLoginId|string|否|
invitationUserId|int|否|
integral|int|否|
userIp|string|否|
userType|int|否|
companName|string|否|
auditMark|int|否|
postcode|string|否|
paymentType|string|否|
deliveryType|string|否|
contact|string|否|
userCourierName|string|否|
userCourierAccount|string|否|
packageRequirements|string|否|
deliveryReport|string|否|
customerStandards|string|否|
productionVerification|int|否|
siteUrl|string|否|
channel|string|否|
commission|string|否|
firstPayTime|date|否|
businessName|string|否|
status|int|否|
isSourceCompany|int|否|

**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":{
		"pageNo":"int //当前页号",
		"pageSize":"int //每页行数",
		"total":"long //总记录数",
		"pageNum":"long //总页数",
		"data":{}
	}
}
```
## 修改用户信息

**

**请求URL**

/api/users/update `PUT` 

**请求体**

```json
{
	"id":"int",
	"userName":"string",
	"password":"string",
	"email":"string",
	"skypeId":"string",
	"googleId":"string",
	"facebookId":"string",
	"mobilePhone":"string",
	"address":"string",
	"country":"string",
	"city":"string",
	"businessType":"string",
	"jobrole":"string",
	"applications":"string",
	"invalidMark":"int",
	"favicon":"string",
	"gmtCreate":"date",
	"gmtModified":"date",
	"uuid":"string",
	"userSystemId":"string",
	"businessId":"int",
	"orderNumNo":"int",
	"facebookLoginId":"string",
	"googleLoginId":"string",
	"invitationUserId":"int",
	"integral":"int",
	"userIp":"string",
	"userType":"int",
	"companName":"string",
	"auditMark":"int",
	"postcode":"string",
	"paymentType":"string",
	"deliveryType":"string",
	"contact":"string",
	"userCourierName":"string",
	"userCourierAccount":"string",
	"packageRequirements":"string",
	"deliveryReport":"string",
	"customerStandards":"string",
	"productionVerification":"int",
	"siteUrl":"string",
	"channel":"string",
	"commission":"string",
	"firstPayTime":"date",
	"businessName":"string",
	"status":"int",
	"isSourceCompany":"int"
}
```

**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":{}
}
```
## 查询当前登录用户信息

**

**请求URL**

/api/users/info `GET` 


**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":{}
}
```
## 校验当前密码

**

**请求URL**

/api/users/verifyCurrPwd `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
currPwd|string|是|当前密码

**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":{}
}
```
## 修改密码

**

**请求URL**

/api/users/changePwd `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
newPwd|string|是|新密码

**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":{}
}
```
# 跟单员信息接口
## 查询当前登录用户对应跟单员信息

**

**请求URL**

/api/business-users/info `GET` 


**返回结果**

```json
{
	"success":"boolean //是否成功",
	"code":"string //编码",
	"message":"string //描述信息",
	"result":{}
}
```
