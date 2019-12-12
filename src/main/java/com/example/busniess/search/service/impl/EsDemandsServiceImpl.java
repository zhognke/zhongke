package com.example.busniess.search.service.impl;

import com.example.busniess.search.dao.EsDemandsDao;
import com.example.busniess.search.common.IndexKey;
import com.example.busniess.search.model.EsDemandsModel;
import com.example.busniess.search.repository.EsDemandsRepository;
import com.example.busniess.search.service.EsDemandsService;
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


@Service("esDemandsService")
public class EsDemandsServiceImpl implements EsDemandsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EsDemandsServiceImpl.class);

    @Autowired
    private EsDemandsDao esDemandsDao;
    @Autowired
    private EsDemandsRepository esDemandsRepository;

    @Override
    public int importAll() {
        List<EsDemandsModel> EsDemandsList = esDemandsDao.getAllEsDemandsList(null);
        Iterable<EsDemandsModel> EsDemandsIterable = esDemandsRepository.saveAll(EsDemandsList);
        Iterator<EsDemandsModel> iterator = EsDemandsIterable.iterator();
        int result = 0;
        while (iterator.hasNext()) {
            result++;
            iterator.next();
        }
        return result;
    }

    @Override
    public void delete(Integer id) {
        esDemandsRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        esDemandsRepository.deleteAll();
    }

    @Override
    public EsDemandsModel create(Integer id) {
        EsDemandsModel result = null;
        List<EsDemandsModel> EsDemandsList = esDemandsDao.getAllEsDemandsList(id);
        if (EsDemandsList.size() > 0) {
            EsDemandsModel EsDemands = EsDemandsList.get(0);
            result = esDemandsRepository.save(EsDemands);
        }
        return result;
    }

    @Override
    public boolean create(EsDemandsModel esDemandsModel) {
        return esDemandsRepository.save(esDemandsModel)!=null;
    }

    @Override
    public void delete(List<Integer> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            List<EsDemandsModel> EsDemandsList = new ArrayList<>();
            for (Integer id : ids) {
                EsDemandsModel EsDemands = new EsDemandsModel();
                EsDemands.setId(id);
                EsDemandsList.add(EsDemands);
            }
            esDemandsRepository.deleteAll(EsDemandsList);
        }
    }

    @Override
    public EsDemandsModel selectById(Integer id) {
        return esDemandsRepository.queryEsDemandsById(id);
    }

    @Override
    public Page<EsDemandsModel> search(EsDemandsModel esDemandsModel, Integer pageNum, Integer pageSize) {

        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        //行业
        if(!StringUtils.isEmpty(esDemandsModel.getDemandIndustry())){
            boolQueryBuilder.must(QueryBuilders.matchQuery(IndexKey.INDEX_INDUSTRY,esDemandsModel.getDemandIndustry()));
        }
        //行业明细
        if(!StringUtils.isEmpty(esDemandsModel.getDemandIndustryDetail())){
            boolQueryBuilder.must(QueryBuilders.matchQuery(IndexKey.INDEX_INDUSTRY_DETAIL,esDemandsModel.getDemandIndustryDetail()));
        }
        //需求类别
        if(!StringUtils.isEmpty(esDemandsModel.getDemandType())){
            boolQueryBuilder.must(QueryBuilders.termsQuery(IndexKey.INDEX_DEMAND_TYPE,Arrays.asList(esDemandsModel.getDemandType().split(","))));
        }
        //合作方式
        if(!StringUtils.isEmpty(esDemandsModel.getCooperationType())){
            boolQueryBuilder.must(QueryBuilders.termsQuery(IndexKey.INDEX_DEMAND_COOPERATION_TYPE, Arrays.asList(esDemandsModel.getCooperationType().split(","))));
        }
        //合作倾向
        if(!StringUtils.isEmpty(esDemandsModel.getCooperationIntention())){
            boolQueryBuilder.must(QueryBuilders.termsQuery(IndexKey.INDEX_DEMAND_COOPERATION_INTENTION,Arrays.asList(esDemandsModel.getCooperationIntention().split(","))));
        }
        if(!StringUtils.isEmpty(esDemandsModel.getPreInvestmentAmountBegin())){
            boolQueryBuilder.must(QueryBuilders.rangeQuery(IndexKey.INDEX_PRE_INVESTMENT_AMOUNT).gte(esDemandsModel.getPreInvestmentAmountBegin()));
        }
        if(!StringUtils.isEmpty(esDemandsModel.getPreInvestmentAmountEnd())){
            boolQueryBuilder.must(QueryBuilders.rangeQuery(IndexKey.INDEX_PRE_INVESTMENT_AMOUNT).lte(esDemandsModel.getPreInvestmentAmountEnd()));
        }
        if(esDemandsModel.getStatus()!=null){
            boolQueryBuilder.must(QueryBuilders.termQuery(IndexKey.INDEX_STATUS,esDemandsModel.getStatus()));
        }
        if(esDemandsModel.getApprovalStatus()!=null){
            boolQueryBuilder.must(QueryBuilders.termQuery(IndexKey.INDEX_APPROVAL_STATUS,esDemandsModel.getApprovalStatus()));
        }
        boolQueryBuilder.must(QueryBuilders.termQuery(IndexKey.INDEX_DEL_FLAG,0));

        //模糊匹配标题 详情 公司名称
        if(!StringUtils.isEmpty(esDemandsModel.getKeyword())){
            //多个字段匹配，只要满足一个即可返回结果
            MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(esDemandsModel.getKeyword(),
                    IndexKey.INDEX_TITLE,
                    IndexKey.INDEX_CONTENT,
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

        return esDemandsRepository.search(searchQuery);
    }

}
