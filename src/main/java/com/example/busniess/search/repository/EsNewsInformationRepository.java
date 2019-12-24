package com.example.busniess.search.repository;

import com.example.busniess.search.model.EsNewsInformationModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EsNewsInformationRepository extends ElasticsearchRepository<EsNewsInformationModel, Integer> {
    EsNewsInformationModel queryModelById(Integer id);

}
