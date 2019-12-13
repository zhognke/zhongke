package com.example.busniess.search.repository;

import com.example.busniess.search.model.EsFinancingModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EsFinancingRepository extends ElasticsearchRepository<EsFinancingModel, Integer> {
    EsFinancingModel queryEsFinancingById(Integer id);

}
