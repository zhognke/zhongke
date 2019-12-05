package com.example.busniess.search.service.impl;

import com.example.busniess.dao.EsIndustryDeclareDao;
import com.example.busniess.search.common.IndexKey;
import com.example.busniess.search.model.EsIndustryDeclareModel;
import com.example.busniess.search.repository.EsIndustryDeclareRepository;
import com.example.busniess.search.service.EsIndustryDeclareService;
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
import java.util.Iterator;
import java.util.List;


@Service("esIndustryDeclareService")
public class EsIndustryDeclareServiceImpl implements EsIndustryDeclareService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EsIndustryDeclareServiceImpl.class);

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
    public Page<EsIndustryDeclareModel> search(String keyword, Integer pageNum, Integer pageSize) {
        //多个字段匹配，只要满足一个即可返回结果
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(keyword,
                IndexKey.INDEX_TITLE,
                IndexKey.INDEX_CONTENT,
                IndexKey.INDEX_COMPANY_NAME
        );

        Pageable pageable = PageRequest.of(pageNum, pageSize);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(multiMatchQueryBuilder)
//                .withQuery(QueryBuilders.queryStringQuery(keyword))
                .withPageable(pageable)
                .build();
        return esIndustryDeclareRepository.search(searchQuery);
    }

    @Override
    public void deleteAll() {
        esIndustryDeclareRepository.deleteAll();
    }

}
