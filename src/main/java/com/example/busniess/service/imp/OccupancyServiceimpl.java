package com.example.busniess.service.imp;

import com.example.busniess.dao.ImageAddressDao;
import com.example.busniess.dao.OccupancyDao;
import com.example.busniess.dao.UserDao;
import com.example.busniess.entity.Echarts;
import com.example.busniess.entity.ImageAddress;
import com.example.busniess.entity.Occupancy;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.OccupancyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.mail.imap.protocol.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class OccupancyServiceimpl implements OccupancyService {
    @Autowired
    OccupancyDao occupancyDao;
    @Autowired
    ImageAddressDao imageAddressDao;
    @Autowired
    UserDao userDao;

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
            List<ImageAddress> i = occupancy.getImgAddress();
            for (ImageAddress img : i) {
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
     * @param userName
     * @param roleId
     * @return
     */

    public Boolean upDateStatue(String userName, Integer roleId) {


        return userDao.authorization(roleId, userName);


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

    public PageInfo selectMyOccupancy(String userName, Integer pageNum, Integer pagesize) {
        PageHelper.startPage(pageNum, pagesize);
        List<Occupancy> o = occupancyDao.selectMyOccupancy(userName);
        PageInfo pageInfo = new PageInfo(o);
        return pageInfo;
    }

    @Override
    public PageInfo selectOccupancyByIndustry(Occupancy occupancy, Integer pageNum, Integer pagesize) {
        PageHelper.startPage(pageNum, pagesize);
        List<Occupancy> o = occupancyDao.selectOccupancyByIndustry(occupancy);
        PageInfo pageInfo = new PageInfo(o);
        return pageInfo;
    }

    /**
     * 返回折线图
     *
     * @return
     */

    /**
     * 折线图
     * @return
     */
    public Echarts returnBrokenImg() {
        Echarts echart = new Echarts();
//        int cout = occupancyDao.countIndustry();//总数
//        echart.setTotal(cout);
        List<Map> list = occupancyDao.selectBrokenImg();//折线图
        List a = new ArrayList();
        List b = new ArrayList();
        for (Map c : list) {
            a.add(c.get("COUNT(industry)"));//行业的数量
            b.add(c.get("DATE_FORMAT(creattime,'%Y年%m月')"));//时间

        }
        echart.setXdata(a);
        echart.setYdata(b);

        return echart;
    }

    /**
     * 饼状图
     * @return
     */
    public Echarts returnPieImg() {
        Echarts echart = new Echarts();
        int cout = occupancyDao.countIndustry();//总数
        echart.setTotal(cout);
        List<Map> list = occupancyDao.selectPieImg();//饼状图
        List a = new ArrayList();
        List b = new ArrayList();
        for (Map c : list) {
            a.add(c.get("COUNT(industry)"));//行业的数量
            b.add(c.get("industry"));//行业名

        }
        echart.setXdata(a);
        echart.setYdata(b);

        return echart;
    }



}
