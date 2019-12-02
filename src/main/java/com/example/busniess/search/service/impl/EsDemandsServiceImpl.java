package com.example.busniess.search.service.impl;

import com.example.busniess.dao.EsDemandsDao;
import com.example.busniess.search.common.IndexKey;
import com.example.busniess.search.model.EsDemandsModel;
import com.example.busniess.search.repository.EsDemandsRepository;
import com.example.busniess.search.service.EsDemandsService;
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
    public Page<EsDemandsModel> search(String keyword, Integer pageNum, Integer pageSize) {
        //多个字段匹配，只要满足一个即可返回结果
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(keyword,
                IndexKey.POST_DEMANDOUTLINE,
                IndexKey.POST_DEMANDCONTENT,
                IndexKey.POST_COMPANYNAME,
                IndexKey.POST_INDUSTRY
        );

        Pageable pageable = PageRequest.of(pageNum, pageSize);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(multiMatchQueryBuilder)
                .withPageable(pageable)
                .build();
        return esDemandsRepository.search(searchQuery);
    }

}
