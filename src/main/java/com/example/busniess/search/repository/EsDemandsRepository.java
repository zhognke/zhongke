package com.example.busniess.search.repository;

import com.example.busniess.search.model.EsDemandsModel;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EsDemandsRepository extends ElasticsearchRepository<EsDemandsModel, Integer> {
    EsDemandsModel queryEsDemandsById(Integer id);

    @Query("{\"match_phrase\":{\"title\":\"?0\"}}")
    List<EsDemandsModel> findByCompanyNameCustom(String companyName);
}
