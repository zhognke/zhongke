package com.example.busniess.search.repository;

import com.example.busniess.search.model.EsDemandsModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EsDemandsRepository extends ElasticsearchRepository<EsDemandsModel, Integer> {
    EsDemandsModel queryEsDemandsById(Integer id);
}
