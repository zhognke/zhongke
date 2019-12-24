package com.example.busniess.search.service.impl;

import com.example.busniess.search.common.IndexKey;
import com.example.busniess.search.dao.EsOccupancyDao;
import com.example.busniess.search.model.EsOccupancyModel;
import com.example.busniess.search.repository.EsOccupancyRepository;
import com.example.busniess.search.service.EsOccupancyService;
import com.example.busniess.search.service.EsOccupancyService;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


@Service("esOccupancyService")
public class EsOccupancyServiceImpl implements EsOccupancyService {

    @Autowired
    private EsOccupancyDao esOccupancyDao;
    @Autowired
    private EsOccupancyRepository esOccupancyRepository;

    @Override
    public int importAll() {
        List<EsOccupancyModel> EsDemandsList = esOccupancyDao.selectAll();
        Iterable<EsOccupancyModel> EsDemandsIterable = esOccupancyRepository.saveAll(EsDemandsList);
        Iterator<EsOccupancyModel> iterator = EsDemandsIterable.iterator();
        int result = 0;
        while (iterator.hasNext()) {
            result++;
            iterator.next();
        }
        return result;
    }

    @Override
    public void delete(Integer id) {
        esOccupancyRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        esOccupancyRepository.deleteAll();
    }

    @Override
    public EsOccupancyModel create(Integer id) {
        EsOccupancyModel esOccupancyModel = esOccupancyDao.selectById(id);
        return esOccupancyRepository.save(esOccupancyModel);
    }

    @Override
    public boolean create(EsOccupancyModel esOccupancyModel) {
        return esOccupancyRepository.save(esOccupancyModel)!=null;
    }

    @Override
    public void delete(List<Integer> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            List<EsOccupancyModel> EsDemandsList = new ArrayList<>();
            for (Integer id : ids) {
                EsOccupancyModel EsDemands = new EsOccupancyModel();
                EsDemands.setId(id);
                EsDemandsList.add(EsDemands);
            }
            esOccupancyRepository.deleteAll(EsDemandsList);
        }
    }

    @Override
    public EsOccupancyModel selectById(Integer id) {
        return esOccupancyRepository.queryEsOccupancyById(id);
    }

    @Override
    public Page<EsOccupancyModel> search(EsOccupancyModel esOccupancyModel, Integer pageNum, Integer pageSize) {

        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        //行业
        if(!StringUtils.isEmpty(esOccupancyModel.getIndustry())){
            boolQueryBuilder.must(QueryBuilders.matchQuery(IndexKey.INDEX_INDUSTRY,esOccupancyModel.getIndustry()));
        }
        //行业明细
        if(!StringUtils.isEmpty(esOccupancyModel.getIndustryDetail())){
            boolQueryBuilder.must(QueryBuilders.matchQuery(IndexKey.INDEX_INDUSTRY_DETAIL,esOccupancyModel.getIndustryDetail()));
        }
        //省
        if(!StringUtils.isEmpty(esOccupancyModel.getProvince())){
            boolQueryBuilder.must(QueryBuilders.matchQuery(IndexKey.INDEX_PROVINCE,esOccupancyModel.getProvince()));
        }
        //市
        if(!StringUtils.isEmpty(esOccupancyModel.getCity())){
            boolQueryBuilder.must(QueryBuilders.matchQuery(IndexKey.INDEX_CITY,esOccupancyModel.getCity()));
        }
        //区
        if(!StringUtils.isEmpty(esOccupancyModel.getDistrict())){
            boolQueryBuilder.must(QueryBuilders.matchQuery(IndexKey.INDEX_DISTRICT,esOccupancyModel.getDistrict()));
        }
        //成果类型
        if(!StringUtils.isEmpty(esOccupancyModel.getAdvantages())){
            boolQueryBuilder.must(QueryBuilders.termsQuery(IndexKey.INDEX_OCCUPANCY_ADVANTAGES,Arrays.asList(esOccupancyModel.getAdvantages().split(","))));
        }
        //转让方式
        if(!StringUtils.isEmpty(esOccupancyModel.getTransferType())){
            boolQueryBuilder.must(QueryBuilders.termsQuery(IndexKey.INDEX_OCCUPANCY_TRANSFER_TYPE, Arrays.asList(esOccupancyModel.getTransferType().split(","))));
        }
        //成果阶段
        if(!StringUtils.isEmpty(esOccupancyModel.getStage())){
            boolQueryBuilder.must(QueryBuilders.termsQuery(IndexKey.INDEX_OCCUPANCY_STAGE, Arrays.asList(esOccupancyModel.getStage().split(","))));
        }
        //完成方式
        if(!StringUtils.isEmpty(esOccupancyModel.getAttribute())){
            boolQueryBuilder.must(QueryBuilders.termsQuery(IndexKey.INDEX_OCCUPANCY_ATTRIBUTE, Arrays.asList(esOccupancyModel.getAttribute().split(","))));
        }
        //转让金额
        if(esOccupancyModel.getPriceBegin()!=null&&esOccupancyModel.getPriceBegin().doubleValue()>0d){
            boolQueryBuilder.must(QueryBuilders.rangeQuery(IndexKey.INDEX_OCCUPANCY_PRICE).gte(esOccupancyModel.getPriceBegin()));
        }
        if(esOccupancyModel.getPriceEnd()!=null&&esOccupancyModel.getPriceEnd().doubleValue()>0d){
            boolQueryBuilder.must(QueryBuilders.rangeQuery(IndexKey.INDEX_OCCUPANCY_PRICE).lte(esOccupancyModel.getPriceEnd()));
        }
        //发布状态
        if(esOccupancyModel.getApprovalStatus()!=null){
            boolQueryBuilder.must(QueryBuilders.termQuery(IndexKey.INDEX_APPROVAL_STATUS,esOccupancyModel.getApprovalStatus()));
        }
        //审批状态
        if(esOccupancyModel.getStatus()!=null){
            boolQueryBuilder.must(QueryBuilders.termQuery(IndexKey.INDEX_STATUS,esOccupancyModel.getStatus()));
        }

        //模糊匹配标题 详情 公司名称
        if(!StringUtils.isEmpty(esOccupancyModel.getKeyword())){
            //多个字段匹配，只要满足一个即可返回结果
            MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(esOccupancyModel.getKeyword(),
                    IndexKey.INDEX_TITLE,
                    IndexKey.INDEX_CONTENT,
                    IndexKey.INDEX_OUTLINE,
                    IndexKey.INDEX_COMPANY_NAME
            );
            boolQueryBuilder.must(multiMatchQueryBuilder);
        }
        //分页
        Pageable pageable = PageRequest.of(pageNum, pageSize);

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .withPageable(pageable)
                .build();

        return esOccupancyRepository.search(searchQuery);
    }

}
