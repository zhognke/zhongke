package com.example.busniess.search.service.impl;

import com.example.busniess.search.common.IndexKey;
import com.example.busniess.search.dao.EsInnovationActivitiesDao;
import com.example.busniess.search.model.EsInnovationActivitiesModel;
import com.example.busniess.search.repository.EsInnovationActivitiesRepository;
import com.example.busniess.search.service.EsInnovationActivitiesService;
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


@Service("esInnovationActivitiesService")
public class EsInnovationActivitiesServiceImpl implements EsInnovationActivitiesService {

    @Autowired
    private EsInnovationActivitiesDao esInnovationActivitiesDao;
    @Autowired
    private EsInnovationActivitiesRepository esInnovationActivitiesRepository;

    @Override
    public int importAll() {
        List<EsInnovationActivitiesModel> EsDemandsList = esInnovationActivitiesDao.selectAll();
        Iterable<EsInnovationActivitiesModel> EsDemandsIterable = esInnovationActivitiesRepository.saveAll(EsDemandsList);
        Iterator<EsInnovationActivitiesModel> iterator = EsDemandsIterable.iterator();
        int result = 0;
        while (iterator.hasNext()) {
            result++;
            iterator.next();
        }
        return result;
    }

    @Override
    public void delete(Integer id) {
        esInnovationActivitiesRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        esInnovationActivitiesRepository.deleteAll();
    }

    @Override
    public EsInnovationActivitiesModel create(Integer id) {
        EsInnovationActivitiesModel esInnovationActivitiesModel = esInnovationActivitiesDao.selectById(id);
        return esInnovationActivitiesRepository.save(esInnovationActivitiesModel);
    }

    @Override
    public boolean create(EsInnovationActivitiesModel EsInnovationActivitiesModel) {
        return esInnovationActivitiesRepository.save(EsInnovationActivitiesModel)!=null;
    }

    @Override
    public void delete(List<Integer> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            List<EsInnovationActivitiesModel> EsDemandsList = new ArrayList<>();
            for (Integer id : ids) {
                EsInnovationActivitiesModel EsDemands = new EsInnovationActivitiesModel();
                EsDemands.setId(id);
                EsDemandsList.add(EsDemands);
            }
            esInnovationActivitiesRepository.deleteAll(EsDemandsList);
        }
    }

    @Override
    public EsInnovationActivitiesModel selectById(Integer id) {
        return esInnovationActivitiesRepository.queryModelById(id);
    }

    @Override
    public Page<EsInnovationActivitiesModel> search(EsInnovationActivitiesModel esInnovationActivitiesModel, Integer pageNum, Integer pageSize) {

        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if(esInnovationActivitiesModel.getStatus()!=null){
            boolQueryBuilder.must(QueryBuilders.termQuery(IndexKey.INDEX_STATUS, Arrays.asList(esInnovationActivitiesModel.getStatus().split(","))));
        }
        if(esInnovationActivitiesModel.getStartTime()!=null){
            boolQueryBuilder.must(QueryBuilders.rangeQuery(IndexKey.INDEX_ACTIVITIES_START_TIME).gte(esInnovationActivitiesModel.getStartTime()));
        }
        if(esInnovationActivitiesModel.getEndTime()!=null){
            boolQueryBuilder.must(QueryBuilders.rangeQuery(IndexKey.INDEX_ACTIVITIES_END_TIME).lte(esInnovationActivitiesModel.getEndTime()));
        }
        boolQueryBuilder.must(QueryBuilders.termQuery(IndexKey.INDEX_DEL_FLAG,0));

        //模糊匹配标题 详情 公司名称
        if(!StringUtils.isEmpty(esInnovationActivitiesModel.getKeyword())){
            //多个字段匹配，只要满足一个即可返回结果
            MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(esInnovationActivitiesModel.getKeyword(),
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

        return esInnovationActivitiesRepository.search(searchQuery);
    }

}
