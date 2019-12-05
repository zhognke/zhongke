package com.example.busniess.search.service.impl;

import com.example.busniess.search.common.IndexKey;
import com.example.busniess.search.model.EsSearchModel;
import com.example.busniess.search.service.EsSearchService;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

@Service("esSearchService")
public class EsSearchServiceImpl implements EsSearchService {

//    @Autowired
//    EsSearchRepository esSearchRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public Page<EsSearchModel> search(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        //多个字段匹配，只要满足一个即可返回结果
        if(!StringUtils.isEmpty(keyword)) {
            MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(keyword,
                    IndexKey.INDEX_TITLE,
                    IndexKey.INDEX_CONTENT,
                    IndexKey.INDEX_COMPANY_NAME
            );
            boolQueryBuilder.must(multiMatchQueryBuilder);
        }
//        if (!StringUtils.isEmpty(keyword))
//            searchQueryBuilder.withQuery(multiMatchQueryBuilder);
        boolQueryBuilder.must(QueryBuilders.termQuery("approvalStatus","1"));
        boolQueryBuilder.must(QueryBuilders.termQuery("status","0"));

        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withPageable(pageable)
                .withIndices("demands", "industrydeclare")
                .withTypes("demands", "industrydeclare")
                .withQuery(boolQueryBuilder);

        SearchQuery searchQuery = searchQueryBuilder.build();
        Page<EsSearchModel> modelPage = elasticsearchTemplate.queryForPage(searchQuery, EsSearchModel.class);
        return modelPage;
    }
}
