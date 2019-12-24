package com.example.busniess.search.repository;

import com.example.busniess.search.model.EsInnovationActivitiesModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EsInnovationActivitiesRepository extends ElasticsearchRepository<EsInnovationActivitiesModel, Integer> {
    EsInnovationActivitiesModel queryModelById(Integer id);

}
