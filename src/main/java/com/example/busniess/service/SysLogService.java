package com.example.busniess.service;

import com.github.pagehelper.PageInfo;
import com.example.busniess.entity.SysLogEntity;

import java.util.List;


/**
 * 系统日志
 *
 * @author lee
 * @email wawzj512541@gmail.com
 * @date 2019-10-28 11:29:08
 */
public interface SysLogService {
    /**
    * 查询所有
    * @return
    */
    public List<SysLogEntity> selectAll();

    /**
     * 根据条件查询
     * @param sysLogEntity
     * @return
     */
    public List<SysLogEntity> search(SysLogEntity sysLogEntity);

    /**
     * 分页查询显示
     * @return
     */
    public PageInfo showByPage(SysLogEntity sysLogEntity, Integer pageNum, Integer pageSize);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    public SysLogEntity selectById(Integer id);

    /**
     * 新增
     * @param sysLogEntity
     * @return
     */
    public boolean save(SysLogEntity sysLogEntity);

    /**
     * 根据id彻底删除
     * @param id
     * @return
     */
    public boolean delectById(Integer id);

    /**
     * 更新
     * @param sysLogEntity
     * @return
     */
    //public boolean update(SysLogEntity sysLogEntity);


}

