//package com.example.busniess.service;
//
//import com.example.busniess.dao.EsDemandsDao;
//import com.example.busniess.search.common.IndexKey;
//import com.example.busniess.search.model.EsDemands;
//import com.example.busniess.search.repository.EsDemandsRepository;
//import org.elasticsearch.index.query.MultiMatchQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
//import org.springframework.data.elasticsearch.core.query.SearchQuery;
//import org.springframework.stereotype.Service;
//import org.springframework.util.CollectionUtils;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//
//@Service("esDemandsService")
//public class EsDemandsServiceImpl implements EsDemandsService {
//    private static final Logger LOGGER = LoggerFactory.getLogger(EsDemandsServiceImpl.class);
//    @Autowired
//    private EsDemandsDao esDemandsDao;
//    @Autowired
//    private EsDemandsRepository productRepository;
//    @Override
//    public int importAll() {
//        List<EsDemands> EsDemandsList = esDemandsDao.getAllEsDemandsList(null);
//        Iterable<EsDemands> EsDemandsIterable = productRepository.saveAll(EsDemandsList);
//        Iterator<EsDemands> iterator = EsDemandsIterable.iterator();
//        int result = 0;
//        while (iterator.hasNext()) {
//            result++;
//            iterator.next();
//        }
//        return result;
//    }
//
//    @Override
//    public void delete(Integer id) {
//        productRepository.deleteById(id);
//    }
//
//    @Override
//    public EsDemands create(Integer id) {
//        EsDemands result = null;
//        List<EsDemands> EsDemandsList = esDemandsDao.getAllEsDemandsList(id);
//        if (EsDemandsList.size() > 0) {
//            EsDemands EsDemands = EsDemandsList.get(0);
//            result = productRepository.save(EsDemands);
//        }
//        return result;
//    }
//
//    @Override
//    public void delete(List<Integer> ids) {
//        if (!CollectionUtils.isEmpty(ids)) {
//            List<EsDemands> EsDemandsList = new ArrayList<>();
//            for (Integer id : ids) {
//                EsDemands EsDemands = new EsDemands();
//                EsDemands.setId(id);
//                EsDemandsList.add(EsDemands);
//            }
//            productRepository.deleteAll(EsDemandsList);
//        }
//    }
//
//    @Override
//    public Page<EsDemands> search(String keyword, Integer pageNum, Integer pageSize) {//多个字段匹配，只要满足一个即可返回结果
//        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(keyword,
//                IndexKey.POST_DEMANDOUTLINE,
//                IndexKey.POST_DEMANDCONTENT,
//                IndexKey.POST_COMPANYNAME,
//                IndexKey.POST_INDUSTRY
//        );
//
//        Pageable pageable = PageRequest.of(pageNum, pageSize);
//
//        SearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withQuery(multiMatchQueryBuilder)
//                .withPageable(pageable)
//                .build();
//        return productRepository.search(searchQuery);
//    }
//
//
//}
