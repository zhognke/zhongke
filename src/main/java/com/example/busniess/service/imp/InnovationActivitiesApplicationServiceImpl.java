package com.example.busniess.service.imp;

import com.example.busniess.dao.InnovationActivitiesApplicationDao;
import com.example.busniess.entity.InnovationActivitiesApplicationEntity;
import com.example.busniess.service.InnovationActivitiesApplicationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("innovationActivitiesApplicationService")
public class InnovationActivitiesApplicationServiceImpl implements InnovationActivitiesApplicationService {

    @Autowired
    InnovationActivitiesApplicationDao innovationActivitiesApplicationDao;

    /**
     * 分页展示
     *
     * @param innovationActivitiesApplicationEntity
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo showByPage(InnovationActivitiesApplicationEntity innovationActivitiesApplicationEntity, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<InnovationActivitiesApplicationEntity> list = innovationActivitiesApplicationDao.search(innovationActivitiesApplicationEntity);
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
    public InnovationActivitiesApplicationEntity selectByID(Integer id) {
        return innovationActivitiesApplicationDao.selectByID(id);
    }

    /**
     * 新增
     *
     * @param innovationActivitiesApplicationEntity
     * @return
     */
    @Override
    public boolean addInnovationActivitiesApplication(InnovationActivitiesApplicationEntity innovationActivitiesApplicationEntity) {
        return innovationActivitiesApplicationDao.insert(innovationActivitiesApplicationEntity);
    }

    /**
     * 逻辑删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteByID(Integer id) {
        return innovationActivitiesApplicationDao.deleteByID(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return boolean
     */
    @Override
    public boolean deleteBatch(String ids) {
        ids = ids.replaceAll(",", "','");
        return innovationActivitiesApplicationDao.deleteBatch(ids);
    }

    /**
     * 从数据库中删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean realDeleteByID(Integer id) {
        return innovationActivitiesApplicationDao.realDeleteByID(id);
    }

    /**
     * 修改
     *
     * @param innovationActivitiesApplicationEntity
     * @return
     */
    @Override
    public boolean updateByID(InnovationActivitiesApplicationEntity innovationActivitiesApplicationEntity) {
        return innovationActivitiesApplicationDao.updateByID(innovationActivitiesApplicationEntity);
    }

    /**
     * 修改审批状态
     *
     * @param id              主键id
     * @return
     */
    @Override
    public boolean updateApprovalStatusPass(Integer id) {
        Integer approvalStatus = 1;
        String approvalOpinion ="";
        return innovationActivitiesApplicationDao.updateApprovalStatus(approvalStatus, approvalOpinion, id);
    }

    @Override
    public boolean updateApprovalStatusRejected(Integer id, String approvalOpinion) {
        Integer approvalStatus = 2;
        return innovationActivitiesApplicationDao.updateApprovalStatus(approvalStatus, approvalOpinion, id);
    }

}
