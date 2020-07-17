package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.GraphsDTO;
import com.jugu.www.pcbonlinev2.domain.dto.GraphsQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.GraphsDO;
import com.jugu.www.pcbonlinev2.mapper.GraphsMapper;
import com.jugu.www.pcbonlinev2.service.GraphsService;
import com.jugu.www.pcbonlinev2.utils.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("graphsService")
public class GraphsServiceImpl extends ServiceImpl<GraphsMapper, GraphsDO> implements GraphsService {

    @Autowired
    private GraphsMapper graphsMapper;

    @Override
    public  PageResult<List<GraphsDTO>> queryPage(PageQuery<GraphsQueryDTO, GraphsDO> params) {
        ValidatorUtil.validate(params);

        GraphsDO query = new GraphsDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<GraphsDO> graphsDOPage = graphsMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<GraphsDTO> graphsDTOList  = Optional.ofNullable(graphsDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(GraphsDO -> {
                    GraphsDTO graphsDTO = new GraphsDTO();
                    BeanUtils.copyProperties(GraphsDO, graphsDTO);
                    return graphsDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(graphsDOPage,graphsDTOList);
    }

}