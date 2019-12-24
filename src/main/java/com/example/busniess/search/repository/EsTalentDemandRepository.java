package com.example.busniess.search.repository;

import com.example.busniess.search.model.EsTalentDemandModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EsTalentDemandRepository extends ElasticsearchRepository<EsTalentDemandModel, Integer> {
    EsTalentDemandModel queryModelById(Integer id);

}
