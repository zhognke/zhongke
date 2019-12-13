package com.example.busniess.search.dao;

import com.example.busniess.search.model.EsOccupancyModel;

import java.util.List;

public interface EsOccupancyDao {
    List<EsOccupancyModel> selectAll(Integer id);
}
