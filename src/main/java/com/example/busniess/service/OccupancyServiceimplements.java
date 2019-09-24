package com.example.busniess.service;

import com.example.busniess.dao.ImageAddressDao;
import com.example.busniess.dao.OccupancyDao;
import com.example.busniess.entity.ImageAddress;
import com.example.busniess.entity.Occupancy;
import com.example.busniess.resultpackage.ReturnResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OccupancyServiceimplements implements OccupancyService {
    @Autowired
    OccupancyDao occupancyDao;
    @Autowired
    ImageAddressDao imageAddressDao;

    /**
     * 新建科技成果入住
     *
     * @param occupancy
     * @return
     */
    @Transactional
    public Boolean addOccupancy(Occupancy occupancy) {
        //1.先插入主表
        //2.插入附表
        if (occupancyDao.insertOccupancy(occupancy)) {
          List<ImageAddress> i=  occupancy.getImgAddress();
          for (ImageAddress img :i){
              img.setOId(occupancy.getId());

          }

            return imageAddressDao.insertImageAddress(i);
        } else {
            return false;
        }

    }

    /**
     * 删除
     *
     * @param id
     * @return
     */

    public Boolean delectOccupancy(Integer id) {
        if (imageAddressDao.delectImageAddress(id)) {
            return occupancyDao.delectOccupancy(id);
        }
        return false;
    }

    /**
     * 更新审核状态
     *
     * @param statue //审核的状态
     * @param id     //id号
     * @return
     */
    public Boolean upDateStatue(Integer statue, Integer id) {

        return occupancyDao.updateStatue(statue, id);
    }

    /**
     * 跟新发布状态
     */
    public Boolean upDateKstatue(Integer kStatue, Integer id) {

        return occupancyDao.updateKstatue(kStatue, id);
    }

    /**
     * 查看所有审核的并状态正常的
     */
    public PageInfo selectOnShowOccupancy(Integer pageNum, Integer pagesize) {
        PageHelper.startPage(pageNum, pagesize);
        List<Occupancy> o = occupancyDao.selectOnShow();
        PageInfo pageInfo = new PageInfo(o);
        return pageInfo;
    }


    /**
     * 查看自己发布的
     */

    public  PageInfo selectMyOccupancy(String userName,Integer pageNum, Integer pagesize){
        PageHelper.startPage(pageNum, pagesize);
        List<Occupancy> o = occupancyDao.selectMyOccupancy(userName);
        PageInfo pageInfo = new PageInfo(o);
        return pageInfo;
    }




}
