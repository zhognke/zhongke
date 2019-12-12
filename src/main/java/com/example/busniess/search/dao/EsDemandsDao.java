package com.example.busniess.search.dao;

import com.example.busniess.search.model.EsDemandsModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 搜索系统中的商品管理自定义Dao
 * Created by macro on 2018/6/19.
 */
public interface EsDemandsDao {
    List<EsDemandsModel> getAllEsDemandsList(@Param("id") Integer id);
}
