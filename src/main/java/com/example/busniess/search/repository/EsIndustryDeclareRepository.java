package com.example.busniess.search.repository;

import com.example.busniess.search.model.EsIndustryDeclareModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EsIndustryDeclareRepository extends ElasticsearchRepository<EsIndustryDeclareModel, Integer> {
    EsIndustryDeclareModel queryEsIndustryDeclareById(Integer id);
}
