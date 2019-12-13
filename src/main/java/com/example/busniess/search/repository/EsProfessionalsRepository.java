package com.example.busniess.search.repository;

import com.example.busniess.search.model.EsProfessionalsModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EsProfessionalsRepository extends ElasticsearchRepository<EsProfessionalsModel, Integer> {
    EsProfessionalsModel queryEsProfessionalsById(Integer id);

}
