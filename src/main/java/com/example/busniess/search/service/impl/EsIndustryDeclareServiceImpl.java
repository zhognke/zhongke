package com.example.busniess.search.service.impl;

import com.example.busniess.search.common.IndexKey;
import com.example.busniess.search.dao.EsIndustryDeclareDao;
import com.example.busniess.search.model.EsIndustryDeclareModel;
import com.example.busniess.search.repository.EsIndustryDeclareRepository;
import com.example.busniess.search.service.EsIndustryDeclareService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service("esIndustryDeclareService")
public class EsIndustryDeclareServiceImpl implements EsIndustryDeclareService {

    @Autowired
    private EsIndustryDeclareDao esIndustryDeclareDao;
    @Autowired
    private EsIndustryDeclareRepository esIndustryDeclareRepository;

    @Override
    public int importAll() {
        List<EsIndustryDeclareModel> esIndustryDeclareModelsList = esIndustryDeclareDao.getAllEsDemandsList(null);
        Iterable<EsIndustryDeclareModel> esDemandsIterable = esIndustryDeclareRepository.saveAll(esIndustryDeclareModelsList);
        Iterator<EsIndustryDeclareModel> iterator = esDemandsIterable.iterator();
        int result = 0;
        while (iterator.hasNext()) {
            result++;
            iterator.next();
        }
        return result;
    }

    @Override
    public void delete(Integer id) {
        esIndustryDeclareRepository.deleteById(id);
    }

    @Override
    public EsIndustryDeclareModel create(Integer id) {
        EsIndustryDeclareModel result = null;
        List<EsIndustryDeclareModel> esIndustryDeclareModelsList = esIndustryDeclareDao.getAllEsDemandsList(id);
        if (esIndustryDeclareModelsList.size() > 0) {
            EsIndustryDeclareModel esIndustryDeclareModel = esIndustryDeclareModelsList.get(0);
            result = esIndustryDeclareRepository.save(esIndustryDeclareModel);
        }
        return result;
    }

    @Override
    public void delete(List<Integer> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            List<EsIndustryDeclareModel> EsDemandsList = new ArrayList<>();
            for (Integer id : ids) {
                EsIndustryDeclareModel EsDemands = new EsIndustryDeclareModel();
                EsDemands.setId(id);
                EsDemandsList.add(EsDemands);
            }
            esIndustryDeclareRepository.deleteAll(EsDemandsList);
        }
    }

    @Override
    public EsIndustryDeclareModel selectById(Integer id) {
        return esIndustryDeclareRepository.queryEsIndustryDeclareById(id);
    }

    @Override
    public Page<EsIndustryDeclareModel> search(EsIndustryDeclareModel esIndustryDeclareModel, Integer pageNum, Integer pageSize) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        //多个字段匹配，只要满足一个即可返回结果
        String keyword = esIndustryDeclareModel.getKeyword();
        if(!StringUtils.isEmpty(keyword)){
            MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(keyword,
                    IndexKey.INDEX_TITLE,
                    IndexKey.INDEX_CONTENT,
                    IndexKey.INDEX_COMPANY_NAME
            );
            boolQueryBuilder.must(multiMatchQueryBuilder);
        }
        //申报类型
        if(!StringUtils.isEmpty(esIndustryDeclareModel.getDeclarationType())){
            boolQueryBuilder.must(QueryBuilders.matchQuery(IndexKey.INDEX_DECLARATION_TYPE,esIndustryDeclareModel.getDeclarationType()));
        }
        //所属行业
        if(!StringUtils.isEmpty(esIndustryDeclareModel.getProjectType())){
            boolQueryBuilder.must(QueryBuilders.matchQuery(IndexKey.INDEX_INDUSTRY,esIndustryDeclareModel.getProjectType()));
        }
        //申报明细
        if(!StringUtils.isEmpty(esIndustryDeclareModel.getProjectTypeDetail())){
            boolQueryBuilder.must(QueryBuilders.matchQuery(IndexKey.INDEX_INDUSTRY_DETAIL,esIndustryDeclareModel.getProjectTypeDetail()));
        }
        if(esIndustryDeclareModel.getDeclarationYearBegin()!=null){
            boolQueryBuilder.must(QueryBuilders.rangeQuery(IndexKey.INDEX_CREATE_TIME).gte(esIndustryDeclareModel.getDeclarationYearBegin()));
        }
        if(esIndustryDeclareModel.getDeclarationYearEnd()!=null){
            boolQueryBuilder.must(QueryBuilders.rangeQuery(IndexKey.INDEX_CREATE_TIME).lte(esIndustryDeclareModel.getDeclarationYearEnd()));
        }
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .withPageable(pageable)
                .build();
        return esIndustryDeclareRepository.search(searchQuery);
    }

    @Override
    public void deleteAll() {
        esIndustryDeclareRepository.deleteAll();
    }

}
