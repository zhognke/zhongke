package com.example.busniess.dao;

import com.example.busniess.entity.IndustrialDeclarationDetailEntity;

/**
 * 
 * 
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-09-28 10:31:37
 */
public interface IndustrialDeclarationDetailDao {

    public boolean add(IndustrialDeclarationDetailEntity entity);

    public boolean realDeleteByDeclarationId(Integer declarationId);

    public boolean update(IndustrialDeclarationDetailEntity entity);

    public IndustrialDeclarationDetailEntity getByDeclarationId(Integer declarationId);

}
