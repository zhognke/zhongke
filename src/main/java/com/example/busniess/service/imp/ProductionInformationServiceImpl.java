package com.example.busniess.service.imp;

import com.example.busniess.dao.ProductionInformationDao;
import com.example.busniess.entity.ProductionInformationEntity;
import com.example.busniess.service.ProductionInformationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productionInformationService")
public class ProductionInformationServiceImpl implements ProductionInformationService {

    @Autowired
    ProductionInformationDao productionInformationDao;

    /**
     * 分页展示
     *
     * @param productionInformationEntity
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo showByPage(ProductionInformationEntity productionInformationEntity, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProductionInformationEntity> list = productionInformationDao.search(productionInformationEntity);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    @Override
    public ProductionInformationEntity selectById(Integer id) {
        return productionInformationDao.selectById(id);
    }

    /**
     * 新增
     *
     * @param productionInformationEntity
     * @return
     */
    @Override
    public boolean add(ProductionInformationEntity productionInformationEntity) {
        return productionInformationDao.add(productionInformationEntity);
    }

    /**
     * 逻辑删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(Integer id) {
        return productionInformationDao.deleteByID(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @Override
    public boolean deleteBatch(String ids) {
        ids = ids.replaceAll(",", "','");
        return productionInformationDao.deleteBatch(ids);
    }

    /**
     * 修改
     *
     * @param productionInformationEntity
     * @return
     */
    @Override
    public boolean update(ProductionInformationEntity productionInformationEntity) {
        return productionInformationDao.updateById(productionInformationEntity);
    }

    @Override
    public ProductionInformationEntity selectByDate(ProductionInformationEntity productionInformationEntity) {
        return productionInformationDao.selectByDate(productionInformationEntity);
    }


}
