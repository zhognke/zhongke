package com.example.busniess.search.service.impl;

import com.example.busniess.search.common.IndexKey;
import com.example.busniess.search.dao.EsFinancingDao;
import com.example.busniess.search.model.EsFinancingModel;
import com.example.busniess.search.repository.EsFinancingRepository;
import com.example.busniess.search.service.EsFinancingService;
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


@Service("esFinancingService")
public class EsFinancingServiceImpl implements EsFinancingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EsFinancingServiceImpl.class);

    @Autowired
    private EsFinancingDao esFinancingDao;
    @Autowired
    private EsFinancingRepository esFinancingRepository;

    @Override
    public int importAll() {
        List<EsFinancingModel> EsDemandsList = esFinancingDao.selectAllFinancing(null);
        Iterable<EsFinancingModel> EsDemandsIterable = esFinancingRepository.saveAll(EsDemandsList);
        Iterator<EsFinancingModel> iterator = EsDemandsIterable.iterator();
        int result = 0;
        while (iterator.hasNext()) {
            result++;
            iterator.next();
        }
        return result;
    }

    @Override
    public void delete(Integer id) {
        esFinancingRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        esFinancingRepository.deleteAll();
    }

    @Override
    public EsFinancingModel create(Integer id) {
        EsFinancingModel result = null;
        List<EsFinancingModel> EsDemandsList = esFinancingDao.selectAllFinancing(id);
        if (EsDemandsList.size() > 0) {
            EsFinancingModel EsDemands = EsDemandsList.get(0);
            result = esFinancingRepository.save(EsDemands);
        }
        return result;
    }

    @Override
    public boolean create(EsFinancingModel esFinancingModel) {
        return esFinancingRepository.save(esFinancingModel)!=null;
    }

    @Override
    public void delete(List<Integer> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            List<EsFinancingModel> EsDemandsList = new ArrayList<>();
            for (Integer id : ids) {
                EsFinancingModel EsDemands = new EsFinancingModel();
                EsDemands.setId(id);
                EsDemandsList.add(EsDemands);
            }
            esFinancingRepository.deleteAll(EsDemandsList);
        }
    }

    @Override
    public EsFinancingModel selectById(Integer id) {
        return esFinancingRepository.queryEsFinancingById(id);
    }

    @Override
    public Page<EsFinancingModel> search(EsFinancingModel esFinancingModel, Integer pageNum, Integer pageSize) {

        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        //行业
        if(!StringUtils.isEmpty(esFinancingModel.getIndustry())){
            boolQueryBuilder.must(QueryBuilders.matchQuery(IndexKey.INDEX_INDUSTRY,esFinancingModel.getIndustry()));
        }
        //行业明细
        if(!StringUtils.isEmpty(esFinancingModel.getIndustryDetail())){
            boolQueryBuilder.must(QueryBuilders.matchQuery(IndexKey.INDEX_INDUSTRY_DETAIL,esFinancingModel.getIndustryDetail()));
        }
        //省
        if(!StringUtils.isEmpty(esFinancingModel.getProvince())){
            boolQueryBuilder.must(QueryBuilders.matchQuery(IndexKey.INDEX_PROVINCE,esFinancingModel.getProvince()));
        }
        //市
        if(!StringUtils.isEmpty(esFinancingModel.getCity())){
            boolQueryBuilder.must(QueryBuilders.matchQuery(IndexKey.INDEX_CITY,esFinancingModel.getCity()));
        }
        //区
        if(!StringUtils.isEmpty(esFinancingModel.getDiscribe())){
            boolQueryBuilder.must(QueryBuilders.matchQuery("discribe",esFinancingModel.getDiscribe()));
        }
        //项目阶段
        if(!StringUtils.isEmpty(esFinancingModel.getProjecrPhase())){
            boolQueryBuilder.must(QueryBuilders.termsQuery(IndexKey.INDEX_FINANCIAL_PROJECT_PHASE,Arrays.asList(esFinancingModel.getProjecrPhase().split(","))));
        }
        //融资方式
        if(!StringUtils.isEmpty(esFinancingModel.getFinancing())){
            boolQueryBuilder.must(QueryBuilders.termsQuery(IndexKey.INDEX_FINANCIAL_FINANCING, Arrays.asList(esFinancingModel.getFinancing().split(","))));
        }
        //周期
        if(esFinancingModel.getPgreat()!=null){
            boolQueryBuilder.must(QueryBuilders.rangeQuery(IndexKey.INDEX_FINANCIAL_PERIOD).lte(esFinancingModel.getPgreat()));
        }
        if(esFinancingModel.getPless()!=null){
            boolQueryBuilder.must(QueryBuilders.rangeQuery(IndexKey.INDEX_FINANCIAL_PERIOD).gte(esFinancingModel.getPless()));
        }
        //金额
        if(esFinancingModel.getGreater()!=null&&esFinancingModel.getGreater().doubleValue()>0d){
            boolQueryBuilder.must(QueryBuilders.rangeQuery(IndexKey.INDEX_FINANCIAL_INCOME).lte(esFinancingModel.getGreater()));
        }
        if(esFinancingModel.getLess()!=null&&esFinancingModel.getLess().doubleValue()>0d){
            boolQueryBuilder.must(QueryBuilders.rangeQuery(IndexKey.INDEX_FINANCIAL_INCOME).gte(esFinancingModel.getLess()));
        }
        //时间单位
        if(!StringUtils.isEmpty(esFinancingModel.getUnit())){
            boolQueryBuilder.must(QueryBuilders.termQuery(IndexKey.INDEX_FINANCIAL_UNIT,esFinancingModel.getUnit()));
        }
        //状态
        if(esFinancingModel.getApprovalStatus()!=null){
            boolQueryBuilder.must(QueryBuilders.termQuery(IndexKey.INDEX_APPROVAL_STATUS,esFinancingModel.getApprovalStatus()));
        }

        //模糊匹配标题 详情 公司名称
        if(!StringUtils.isEmpty(esFinancingModel.getKeyword())){
            //多个字段匹配，只要满足一个即可返回结果
            MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(esFinancingModel.getKeyword(),
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

        return esFinancingRepository.search(searchQuery);
    }

}
