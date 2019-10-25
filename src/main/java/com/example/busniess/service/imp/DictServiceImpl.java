package com.example.busniess.service.imp;

import com.example.busniess.dao.DictDao;
import com.example.busniess.entity.DictEntity;
import com.example.busniess.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("dictService")
public class DictServiceImpl implements DictService {

    @Autowired
    DictDao dictDao;

    /**
     * 新增字典
     * @param entity
     * @return
     */
    @Override
    public boolean add(DictEntity entity) {
        try {
            dictDao.add(entity);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    /**
     * 修改字典
     * @param entity
     * @return
     */
    @Override
    public boolean update(DictEntity entity) {
        return dictDao.update(entity);
    }

    @Override
    public List<DictEntity> getAll() {
        return dictDao.selectAll();
    }

    /**
     * 根据类型获取
     * @param type  类型
     * @return
     */
    @Override
    public List<DictEntity> getByType(String type) {
        return dictDao.getByType(type);
    }

    /**
     * 根据父id获取字典
     * @param parentId
     * @return
     */
    @Override
    public List<DictEntity> getByParentId(String parentId) {
        return dictDao.getByParentId(parentId);
    }


    /**
     * 根据id获取
     * @param id
     * @return
     */
    @Override
    public DictEntity getById(Integer id) {
        return dictDao.getById(id);
    }

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    @Override
    public boolean delById(Integer id) {
        DictEntity entity = new DictEntity();
        if(id!=null){
            entity.setId(id);
            entity.setDelFlag(1);
            return dictDao.update(entity);
        }else{
            return false;
        }
    }

    /**
     * 从数据库删除
     * @param id
     * @return
     */
    @Override
    public boolean realDeleteById(Integer id) {
        return dictDao.realDeleteById(id);
    }
}
