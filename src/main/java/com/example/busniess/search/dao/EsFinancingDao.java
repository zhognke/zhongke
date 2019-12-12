package com.example.busniess.search.dao;

import com.example.busniess.search.model.EsFinancingModel;

import java.util.List;

public interface EsFinancingDao {
    /**
     * 查看所有的
     *
     * @return
     */
    List<EsFinancingModel> selectAllFinancing(Integer id);


}
