package com.example.busniess.search.service.impl;

import com.example.busniess.search.common.IndexKey;
import com.example.busniess.search.dao.EsNewsInformationDao;
import com.example.busniess.search.model.EsNewsInformationModel;
import com.example.busniess.search.repository.EsNewsInformationRepository;
import com.example.busniess.search.service.EsNewsInformationService;
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
import java.util.Iterator;
import java.util.List;


@Service("esNewsInformationService")
public class EsNewsInformationServiceImpl implements EsNewsInformationService {

    @Autowired
    private EsNewsInformationDao esNewsInformationDao;
    @Autowired
    private EsNewsInformationRepository esNewsInformationRepository;

    @Override
    public int importAll() {
        List<EsNewsInformationModel> EsDemandsList = esNewsInformationDao.selectAll();
        Iterable<EsNewsInformationModel> esNewsInformationIterable = esNewsInformationRepository.saveAll(EsDemandsList);
        Iterator<EsNewsInformationModel> iterator = esNewsInformationIterable.iterator();
        int result = 0;
        while (iterator.hasNext()) {
            result++;
            iterator.next();
        }
        return result;
    }

    @Override
    public void delete(Integer id) {
        esNewsInformationRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        esNewsInformationRepository.deleteAll();
    }

    @Override
    public EsNewsInformationModel create(Integer id) {
        EsNewsInformationModel esNewsInformationModel = esNewsInformationDao.selectById(id);
        return esNewsInformationRepository.save(esNewsInformationModel);
    }

    @Override
    public boolean create(EsNewsInformationModel EsNewsInformationModel) {
        return esNewsInformationRepository.save(EsNewsInformationModel)!=null;
    }

    @Override
    public void delete(List<Integer> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            List<EsNewsInformationModel> EsDemandsList = new ArrayList<>();
            for (Integer id : ids) {
                EsNewsInformationModel EsDemands = new EsNewsInformationModel();
                EsDemands.setId(id);
                EsDemandsList.add(EsDemands);
            }
            esNewsInformationRepository.deleteAll(EsDemandsList);
        }
    }

    @Override
    public EsNewsInformationModel selectById(Integer id) {
        return esNewsInformationRepository.queryModelById(id);
    }

    @Override
    public Page<EsNewsInformationModel> search(EsNewsInformationModel esNewsInformationModel, Integer pageNum, Integer pageSize) {

        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        //行业
        if(!StringUtils.isEmpty(esNewsInformationModel.getCategory())){
            boolQueryBuilder.must(QueryBuilders.matchQuery(IndexKey.INDEX_NEWS_CATEGORY,esNewsInformationModel.getCategory()));
        }

        //模糊匹配标题 详情 公司名称
        if(!StringUtils.isEmpty(esNewsInformationModel.getKeyword())){
            //多个字段匹配，只要满足一个即可返回结果
            MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(esNewsInformationModel.getKeyword(),
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

        return esNewsInformationRepository.search(searchQuery);
    }

}
