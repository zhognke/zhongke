package com.example.busniess.search.service;

import com.example.busniess.search.model.EsSearchModel;
import org.springframework.data.domain.Page;

public interface EsSearchService {
    public Page<EsSearchModel> search(String keyword, Integer pageNum, Integer pageSize);
}
