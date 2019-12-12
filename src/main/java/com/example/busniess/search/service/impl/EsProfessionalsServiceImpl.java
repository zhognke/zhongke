package com.example.busniess.search.service.impl;

import com.example.busniess.search.common.IndexKey;
import com.example.busniess.search.dao.EsProfessionalsDao;
import com.example.busniess.search.model.EsProfessionalsModel;
import com.example.busniess.search.repository.EsProfessionalsRepository;
import com.example.busniess.search.service.EsProfessionalsService;
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


@Service("esProfessionalsService")
public class EsProfessionalsServiceImpl implements EsProfessionalsService {

    @Autowired
    private EsProfessionalsDao esProfessionalsDao;
    @Autowired
    private EsProfessionalsRepository esProfessionalsRepository;

    @Override
    public int importAll() {
        List<EsProfessionalsModel> EsDemandsList = esProfessionalsDao.selectAll(null);
        Iterable<EsProfessionalsModel> EsDemandsIterable = esProfessionalsRepository.saveAll(EsDemandsList);
        Iterator<EsProfessionalsModel> iterator = EsDemandsIterable.iterator();
        int result = 0;
        while (iterator.hasNext()) {
            result++;
            iterator.next();
        }
        return result;
    }

    @Override
    public void delete(Integer id) {
        esProfessionalsRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        esProfessionalsRepository.deleteAll();
    }

    @Override
    public EsProfessionalsModel create(Integer id) {
        EsProfessionalsModel result = null;
        List<EsProfessionalsModel> EsDemandsList = esProfessionalsDao.selectAll(id);
        if (EsDemandsList.size() > 0) {
            EsProfessionalsModel EsDemands = EsDemandsList.get(0);
            result = esProfessionalsRepository.save(EsDemands);
        }
        return result;
    }

    @Override
    public boolean create(EsProfessionalsModel esProfessionalsModel) {
        return esProfessionalsRepository.save(esProfessionalsModel)!=null;
    }

    @Override
    public void delete(List<Integer> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            List<EsProfessionalsModel> EsDemandsList = new ArrayList<>();
            for (Integer id : ids) {
                EsProfessionalsModel EsDemands = new EsProfessionalsModel();
                EsDemands.setId(id);
                EsDemandsList.add(EsDemands);
            }
            esProfessionalsRepository.deleteAll(EsDemandsList);
        }
    }

    @Override
    public EsProfessionalsModel selectById(Integer id) {
        return esProfessionalsRepository.queryEsProfessionalsById(id);
    }

    @Override
    public Page<EsProfessionalsModel> search(EsProfessionalsModel esProfessionalsModel, Integer pageNum, Integer pageSize) {

        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        //行业
        if(!StringUtils.isEmpty(esProfessionalsModel.getTechnologyScope())){
            boolQueryBuilder.must(QueryBuilders.matchQuery(IndexKey.INDEX_INDUSTRY,esProfessionalsModel.getTechnologyScope()));
        }
        //行业明细
        if(!StringUtils.isEmpty(esProfessionalsModel.getTechnologyScopeDetail())){
            boolQueryBuilder.must(QueryBuilders.matchQuery(IndexKey.INDEX_INDUSTRY_DETAIL,esProfessionalsModel.getTechnologyScopeDetail()));
        }//省
        if(!StringUtils.isEmpty(esProfessionalsModel.getProvince())){
            boolQueryBuilder.must(QueryBuilders.matchQuery(IndexKey.INDEX_PROVINCE,esProfessionalsModel.getProvince()));
        }
        //市
        if(!StringUtils.isEmpty(esProfessionalsModel.getCity())){
            boolQueryBuilder.must(QueryBuilders.matchQuery(IndexKey.INDEX_CITY,esProfessionalsModel.getCity()));
        }
        //区
        if(!StringUtils.isEmpty(esProfessionalsModel.getDistrict())){
            boolQueryBuilder.must(QueryBuilders.matchQuery(IndexKey.INDEX_DISTRICT,esProfessionalsModel.getDistrict()));
        }
        if(esProfessionalsModel.getStatus()!=null){
            boolQueryBuilder.must(QueryBuilders.termQuery(IndexKey.INDEX_STATUS,esProfessionalsModel.getStatus()));
        }
        if(esProfessionalsModel.getApprovalStatus()!=null){
            boolQueryBuilder.must(QueryBuilders.termQuery(IndexKey.INDEX_APPROVAL_STATUS,esProfessionalsModel.getApprovalStatus()));
        }
        boolQueryBuilder.must(QueryBuilders.termQuery(IndexKey.INDEX_DEL_FLAG,0));

        //模糊匹配标题 详情 公司名称
        if(!StringUtils.isEmpty(esProfessionalsModel.getKeyword())){
            //多个字段匹配，只要满足一个即可返回结果
            MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(esProfessionalsModel.getKeyword(),
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

        return esProfessionalsRepository.search(searchQuery);
    }

}
