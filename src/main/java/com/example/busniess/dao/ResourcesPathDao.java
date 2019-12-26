package com.example.busniess.dao;

import com.example.busniess.entity.ManagerRoler;
import com.example.busniess.entity.ResourcesPath;

import java.util.List;

public interface ResourcesPathDao {

    /**
     * 批量插入
     */
    public Boolean insertResourcesPath(List<ResourcesPath> ResourcesPaths);

    /**
     * 批量删除资源
     */
    public Boolean delectResourcesPath(List<Integer> ids);

    /**
     * 批量修改
     */
    public Boolean updateResourcesPath(List<ResourcesPath> resourcesPaths);

    /**
     * 按条件查看
     */
    public List<ResourcesPath> selectResourcesPath(ResourcesPath resourcesPath);

}
