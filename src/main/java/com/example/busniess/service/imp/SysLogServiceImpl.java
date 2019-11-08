package com.example.busniess.service.imp;

import com.example.busniess.dao.SysLogDao;
import com.example.busniess.entity.SysLogEntity;
import com.example.busniess.service.SysLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    SysLogDao sysLogDao;

    /**
    * 查询所有
 * @return
*/
    @Override
    public List<SysLogEntity> selectAll() {
        return sysLogDao.selectAll();
    }

    /**
    * 根据条件搜索
    * @param sysLogEntity
    * @return
    */
    @Override
    public List<SysLogEntity> search(SysLogEntity sysLogEntity) {
        return sysLogDao.search(sysLogEntity);
    }

    /**
    * 分页展示
    * @param sysLogEntity
    * @param pageNum
    * @param pageSize
    * @return
    */
    @Override
    public PageInfo showByPage(SysLogEntity sysLogEntity, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysLogEntity> list = sysLogDao.search(sysLogEntity);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
    * 根据id查找
    * @param id
    * @return
    */
    @Override
    public SysLogEntity selectById(Integer id) {
        return sysLogDao.selectById(id);
    }

    /**
    * 新增
    * @param sysLogEntity
    * @return
    */
    @Override
    public boolean save(SysLogEntity sysLogEntity) {
        return sysLogDao.save(sysLogEntity);
    }


    /**
    * 从数据库中删除
    * @param id
    * @return
    */
    @Override
    public boolean delectById(Integer id) {
        return sysLogDao.deleteById(id);
    }

    /**
    * 修改
    * @param sysLogEntity
    * @return
    */
    /*@Override
    public boolean update(SysLogEntity sysLogEntity) {
        return sysLogDao.updateById(sysLogEntity);
    }*/

}
