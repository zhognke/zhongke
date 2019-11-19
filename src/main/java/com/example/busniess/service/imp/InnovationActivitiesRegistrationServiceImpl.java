package com.example.busniess.service.imp;

import com.example.busniess.dao.InnovationActivitiesRegistrationDao;
import com.example.busniess.entity.InnovationActivitiesRegistrationEntity;
import com.example.busniess.service.InnovationActivitiesRegistrationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("innovationActivitiesRegistrationService" )
public class InnovationActivitiesRegistrationServiceImpl implements InnovationActivitiesRegistrationService {

    @Autowired
    InnovationActivitiesRegistrationDao innovationActivitiesRegistrationDao;

    /**
     * 分页展示
     * @param innovationActivitiesRegistrationEntity
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo showByPage(InnovationActivitiesRegistrationEntity innovationActivitiesRegistrationEntity, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<InnovationActivitiesRegistrationEntity> list = innovationActivitiesRegistrationDao.search(innovationActivitiesRegistrationEntity);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
    * 根据id查找
    * @param id
    * @return
    */
    @Override
    public InnovationActivitiesRegistrationEntity selectByID(Integer id) {
        return innovationActivitiesRegistrationDao.selectByID(id);
    }

    /**
     * 新增
     * @param innovationActivitiesRegistrationEntity
     * @return
     */
    @Override
    public boolean addInnovationActivitiesRegistration(InnovationActivitiesRegistrationEntity innovationActivitiesRegistrationEntity) {
        return innovationActivitiesRegistrationDao.insert(innovationActivitiesRegistrationEntity);
    }

    /**
    * 逻辑删除
    * @param id
    * @return
    */
    @Override
    public boolean deleteByID(Integer id) {
        return innovationActivitiesRegistrationDao.deleteByID(id);
    }

    /**
    * 批量删除
    * @param ids
    * @return boolean
    */
    @Override
    public boolean deleteBatch(String ids) {
        ids = ids.replaceAll(",", "','" );
        return innovationActivitiesRegistrationDao.deleteBatch(ids);
    }

    /**
    * 从数据库中删除
    * @param id
    * @return
    */
    @Override
    public boolean realDeleteByID(Integer id) {
        return innovationActivitiesRegistrationDao.realDeleteByID(id);
    }

    /**
     * 修改
     * @param innovationActivitiesRegistrationEntity
     * @return
     */
    @Override
    public boolean updateByID(InnovationActivitiesRegistrationEntity innovationActivitiesRegistrationEntity) {
        return innovationActivitiesRegistrationDao.updateByID(innovationActivitiesRegistrationEntity);
    }

    /**
     * 查看用户是否报名
     * @param username
     * @param innovationId
     * @return
     */
    @Override
    public boolean isRegistration(String username, Integer innovationId) {
        InnovationActivitiesRegistrationEntity entity = innovationActivitiesRegistrationDao.isRegistration(username,innovationId);
        if (entity!=null&&entity.getRealname()!=null)
            return true;
        else
            return false;
    }
}
