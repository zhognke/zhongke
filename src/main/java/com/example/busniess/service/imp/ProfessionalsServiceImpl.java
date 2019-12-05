package com.example.busniess.service.imp;

import com.example.busniess.dao.OccupancyDao;
import com.example.busniess.dao.ProfessionalsDao;
import com.example.busniess.entity.ProfessionalsEntity;
import com.example.busniess.service.ProfessionalsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("professionalsService")
public class ProfessionalsServiceImpl implements ProfessionalsService {

    @Autowired
    ProfessionalsDao professionalsDao;
    @Autowired
    OccupancyDao occupancyDao;

    /**
    * 查询所有
 * @return
*/
    @Override
    public List<ProfessionalsEntity> selectAll() {
        return professionalsDao.selectAll();
    }

    /**
    * 根据条件搜索
    * @param professionalsEntity
    * @return
    */
    @Override
    public List<ProfessionalsEntity> search(ProfessionalsEntity professionalsEntity) {
        return professionalsDao.search(professionalsEntity);
    }

    /**
    * 分页展示
    * @param professionalsEntity
    * @param pageNum
    * @param pageSize
    * @return
    */
    @Override
    public PageInfo showByPage(ProfessionalsEntity professionalsEntity, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        professionalsEntity.setApprovalStatus(1);
        if(professionalsEntity.getTechnologyScope()!=null){
            professionalsEntity.setTechnologyScope(professionalsEntity.getTechnologyScope().replaceAll(",","','"));
        }
        List<ProfessionalsEntity> list = professionalsDao.search(professionalsEntity);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
    /**
     * 根据id查找
     * @param id
     * @return
     */
    @Override
    public ProfessionalsEntity selectById(Integer id) {
        return professionalsDao.selectById(id);
    }

    /**
    * 根据id查找_新
    * @param id
    * @return
    */
    @Override
    public ProfessionalsEntity selectById(Integer id,Integer size) {
        ProfessionalsEntity entity = professionalsDao.selectById(id);
        if(entity!=null){
            entity.setOccupancyList(occupancyDao.getOccupanyForProfessional(entity.getUserName(),size));
        }
        return entity;
    }

    /**
    * 新增
    * @param professionalsEntity
    * @return
    */
    @Override
    public boolean add(ProfessionalsEntity professionalsEntity) {
        professionalsEntity.setStatus(0);
        professionalsEntity.setApprovalStatus(0);
        return professionalsDao.add(professionalsEntity);
    }

    /**
    * 逻辑删除
    * @param id
    * @return
    */
    @Override
    public boolean deleteById(Integer id) {
        return professionalsDao.deleteById(id);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Override
    public boolean deleteBatch(String ids) {
        ids = ids.replaceAll(",","','");
        return professionalsDao.deleteBatch(ids);
    }

    /**
    * 从数据库中删除
    * @param id
    * @return
    */
    @Override
    public boolean realDeleteById(Integer id) {
        return professionalsDao.realDeleteById(id);
    }

    /**
    * 修改
    * @param professionalsEntity
    * @return
    */
    @Override
    public boolean update(ProfessionalsEntity professionalsEntity) {
        return professionalsDao.updateById(professionalsEntity);
    }

    /**
    * 修改状态
    * @param id
    * @param status
    * @return
    */
    @Override
    public boolean updateStatus(Integer id, Integer status) {
        ProfessionalsEntity professionalsEntity = new ProfessionalsEntity();
        professionalsEntity.setId(id);
        professionalsEntity.setStatus(status);
        return professionalsDao.updateById(professionalsEntity);
    }

    /**
    * 修改审批状态
    * @param id
    * @param approvalStatus
    * @param approvalOpinion
    * @return
    */
    @Override
    public boolean updateApprovalStatus(Integer id, Integer approvalStatus, String approvalOpinion) {
        return professionalsDao.updateApprovalStatus(id,approvalStatus,approvalOpinion);
    }

    @Override
    public boolean closeById(Integer id, String closeReason) {
        Integer status =3;
        return professionalsDao.closeById(id,status,closeReason);
    }

    @Override
    public boolean closeByIdForManager(Integer id, String closeReason) {
        Integer status =2;
        return professionalsDao.closeById(id,status,closeReason);
    }

    @Override
    public PageInfo showHot(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProfessionalsEntity> list = professionalsDao.showHot();
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

}
