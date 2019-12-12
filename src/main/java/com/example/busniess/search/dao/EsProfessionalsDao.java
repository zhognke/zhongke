package com.example.busniess.search.dao;

import com.example.busniess.search.model.EsProfessionalsModel;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 专家信息表
 * 
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-12-12 11:42:14
 */
public interface EsProfessionalsDao {
    /**
    * 查询所有
    * @return
    */
	public List<EsProfessionalsModel> selectAll(Integer id);
}
