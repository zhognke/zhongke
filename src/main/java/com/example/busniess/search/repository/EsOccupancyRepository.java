package com.example.busniess.search.repository;

import com.example.busniess.search.model.EsOccupancyModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EsOccupancyRepository extends ElasticsearchRepository<EsOccupancyModel, Integer> {
    EsOccupancyModel queryEsOccupancyById(Integer id);

}
