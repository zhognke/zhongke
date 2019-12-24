package com.example.busniess.search.service.impl;

import com.example.busniess.search.common.IndexKey;
import com.example.busniess.search.dao.EsTalentDemandDao;
import com.example.busniess.search.model.EsTalentDemandModel;
import com.example.busniess.search.repository.EsTalentDemandRepository;
import com.example.busniess.search.service.EsTalentDemandService;
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


@Service("esTalentDemandService")
public class EsTalentDemandServiceImpl implements EsTalentDemandService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EsTalentDemandServiceImpl.class);

    @Autowired
    private EsTalentDemandDao esTalentDemandDao;
    @Autowired
    private EsTalentDemandRepository esTalentDemandRepository;

    @Override
    public int importAll() {
        List<EsTalentDemandModel> EsDemandsList = esTalentDemandDao.selectAll();
        Iterable<EsTalentDemandModel> EsDemandsIterable = esTalentDemandRepository.saveAll(EsDemandsList);
        Iterator<EsTalentDemandModel> iterator = EsDemandsIterable.iterator();
        int result = 0;
        while (iterator.hasNext()) {
            result++;
            iterator.next();
        }
        return result;
    }

    @Override
    public void delete(Integer id) {
        esTalentDemandRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        esTalentDemandRepository.deleteAll();
    }

    @Override
    public EsTalentDemandModel create(Integer id) {
        EsTalentDemandModel esTalentDemandModel = esTalentDemandDao.selectById(id);
        return esTalentDemandRepository.save(esTalentDemandModel);
    }

    @Override
    public boolean create(EsTalentDemandModel EsTalentDemandModel) {
        return esTalentDemandRepository.save(EsTalentDemandModel)!=null;
    }

    @Override
    public void delete(List<Integer> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            List<EsTalentDemandModel> EsDemandsList = new ArrayList<>();
            for (Integer id : ids) {
                EsTalentDemandModel EsDemands = new EsTalentDemandModel();
                EsDemands.setId(id);
                EsDemandsList.add(EsDemands);
            }
            esTalentDemandRepository.deleteAll(EsDemandsList);
        }
    }

    @Override
    public EsTalentDemandModel selectById(Integer id) {
        return esTalentDemandRepository.queryModelById(id);
    }

    @Override
    public Page<EsTalentDemandModel> search(EsTalentDemandModel esTalentDemandModel, Integer pageNum, Integer pageSize) {

        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        //行业
        if(!StringUtils.isEmpty(esTalentDemandModel.getEngagedIndustry())){
            boolQueryBuilder.must(QueryBuilders.matchQuery(IndexKey.INDEX_INDUSTRY,esTalentDemandModel.getEngagedIndustry()));
        }
        //行业明细
        if(!StringUtils.isEmpty(esTalentDemandModel.getEngagedIndustryDetail())){
            boolQueryBuilder.must(QueryBuilders.matchQuery(IndexKey.INDEX_INDUSTRY_DETAIL,esTalentDemandModel.getEngagedIndustryDetail()));
        }
        //学历
        if(!StringUtils.isEmpty(esTalentDemandModel.getDegree())){
            boolQueryBuilder.must(QueryBuilders.termsQuery(IndexKey.INDEX_TALENT_DEGREE, Arrays.asList(esTalentDemandModel.getDegree().split(","))));
        }
        //薪资
        if(!StringUtils.isEmpty(esTalentDemandModel.getSalary())){
            boolQueryBuilder.must(QueryBuilders.termsQuery(IndexKey.INDEX_TALENT_SALARY, Arrays.asList(esTalentDemandModel.getSalary().split(","))));
        }
        //需求类别
        if(!StringUtils.isEmpty(esTalentDemandModel.getDemandsType())){
            boolQueryBuilder.must(QueryBuilders.termsQuery(IndexKey.INDEX_TALENT_DEMAND_TYPE,Arrays.asList(esTalentDemandModel.getDemandsType().split(","))));
        }
        //状态
        if(esTalentDemandModel.getStatus()!=null){
            boolQueryBuilder.must(QueryBuilders.termQuery(IndexKey.INDEX_STATUS,esTalentDemandModel.getStatus()));
        }
        //审批状态
        if(esTalentDemandModel.getApprovalStatus()!=null){
            boolQueryBuilder.must(QueryBuilders.termQuery(IndexKey.INDEX_APPROVAL_STATUS,esTalentDemandModel.getApprovalStatus()));
        }
        boolQueryBuilder.must(QueryBuilders.termQuery(IndexKey.INDEX_DEL_FLAG,0));

        //模糊匹配标题 详情 公司名称
        if(!StringUtils.isEmpty(esTalentDemandModel.getKeyword())){
            //多个字段匹配，只要满足一个即可返回结果
            MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(esTalentDemandModel.getKeyword(),
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

        return esTalentDemandRepository.search(searchQuery);
    }

}
