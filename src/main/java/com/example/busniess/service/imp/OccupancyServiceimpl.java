package com.example.busniess.service.imp;

import com.example.busniess.dao.BusinessCenterDao;
import com.example.busniess.dao.ImageAddressDao;
import com.example.busniess.dao.OccupancyDao;
import com.example.busniess.dao.UserDao;
import com.example.busniess.entity.*;
import com.example.busniess.service.OccupancyService;
import com.example.busniess.utiles.EchartsEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OccupancyServiceimpl implements OccupancyService {
    @Autowired
    OccupancyDao occupancyDao;
    @Autowired
    ImageAddressDao imageAddressDao;
    @Autowired
    UserDao userDao;
    @Autowired
    BusinessCenterDao businessCenterDao;

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
            if (i != null && i.size() > 0) {
                for (ImageAddress img : i) {
                    img.setOId(occupancy.getId());
                }
                return imageAddressDao.insertImageAddress(i);
            } else {
                return true;
            }
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
    @Transactional
    public Boolean delectOccupancy(Integer id) {
        if (imageAddressDao.delectImageAddress(id)) {
            return occupancyDao.delectOccupancy(id);
        }
        return false;
    }


    /**
     * 更新审核状态
     *
     * @param statue
     * @param id
     * @return
     */
    public boolean updateStatue(Integer statue, Integer id, String reject) {

        return occupancyDao.updateStatue(statue, id, reject);
    }


    /**
     * 跟新发布状态
     */
    public Boolean upDateKstatue(Integer kStatue, Integer id) {

        return occupancyDao.updateKstatue(kStatue, id);
    }




    //结束
    @Override
    public boolean closeById(Integer id, String closeReason) {
        Integer status = 3;
        return occupancyDao.closeById(id, status, closeReason);
    }




    //驳回
    @Override
    public boolean closeByIdForManager(Integer id, String closeReason) {
        Integer status = 2;
        return occupancyDao.closeById(id, status, closeReason);
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

    public PageInfo selectMyOccupancy(Occupancy occupancy, Integer pageNum, Integer pagesize) {
        PageHelper.startPage(pageNum, pagesize);
        List<Occupancy> o = occupancyDao.selectMyOccupancy(occupancy);
        PageInfo pageInfo = new PageInfo(o);
        return pageInfo;
    }

    /**
     * 根据行业查询
     *
     * @param occupancy
     * @return
     */
    @Override
    public PageInfo selectOccupancyByIndustry(Occupancy occupancy, Integer pageNum, Integer pagesize) {
        PageHelper.startPage(pageNum, pagesize);
        List<Occupancy> o = occupancyDao.selectOccupancyByIndustry(occupancy);
        PageInfo pageInfo = new PageInfo(o);
        return pageInfo;
    }

    /**
     * 查看具体 具体的科技成果
     *
     * @param id
     * @return
     */
    public Occupancy seleOccupancyById(Integer id) {
        Occupancy occupancy = occupancyDao.selectOneById(id);
//        System.out.println(occupancy);
//        occupancy.setBusinessCenter(businessCenterDao.selectOneBusinessCenter(occupancy.getUserName()));
        return occupancy;
    }

    /**
     * 根据认证状态查询科技成果
     *
     * @param statue
     * @return
     */
    public List<Occupancy> seleOccupancyByStatue(Integer statue) {

        return occupancyDao.selectOnStatueOccupancy(statue);
    }

    /**
     * 更新科技成果信息
     *
     * @param occupancy
     * @return
     */
    @Override
    @Transactional
    public boolean updateOccupancy(Occupancy occupancy) {
        Integer oid = occupancy.getId();
        List<ImageAddress> list = occupancy.getImgAddress();
        if (list != null && list.size() > 0) {
            for (ImageAddress img : list) {
                img.setOId(oid);
            }
            imageAddressDao.delectImageAddress(oid);
            if (imageAddressDao.insertImageAddress(occupancy.getImgAddress())) {
                occupancyDao.updateStatue(0, oid, null);
                return occupancyDao.upDataOccupancy(occupancy);
            } else {
                return false;
            }
        } else {
            occupancyDao.updateStatue(0, oid, null);
            return occupancyDao.upDataOccupancy(occupancy);
        }
    }

    /**
     * 分页展示(大厅,包含检索功能)
     *
     * @param occupancy
     * @param pageNum
     * @param pagesize
     * @return
     */
    @Override
    public PageInfo showByPage(Occupancy occupancy, Integer pageNum, Integer pagesize) {
        PageHelper.startPage(pageNum, pagesize);
        List<Occupancy> o = occupancyDao.selectOccupancyByIndustry(occupancy);
        PageInfo pageInfo = new PageInfo(o);
        return pageInfo;
    }

    @Override
    public List<Occupancy> getHotIndustry(Integer size) {
        return occupancyDao.getHotIndustry(size);
    }

    @Override
    public PageInfo showByPageForCenter(Occupancy occupancy, Integer pageNum, Integer pagesize) {
        PageHelper.startPage(pageNum, pagesize);
        List<Occupancy> o = occupancyDao.showByPageForCenter(occupancy);
        PageInfo pageInfo = new PageInfo(o);
        return pageInfo;
    }

    @Override
    public Map<String, Object> getIndustryProp(Integer size) {
        List<Occupancy> list = occupancyDao.demandsIndustryProp(size);
        if (list.isEmpty() || "[]".equals(list.toString())) {
            return null;
        } else {
            Map<String, Object> map = new HashMap<>();
            List<EchartsEntity> sdata = new ArrayList<>();
            String[] ldata = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                EchartsEntity echartsEntity = new EchartsEntity();
                ldata[i] = list.get(i).getIndustry();
                echartsEntity.setName(list.get(i).getIndustry());
                echartsEntity.setValue(Double.parseDouble(String.valueOf(list.get(i).getStatue())));
                sdata.add(echartsEntity);
            }
            map.put("sdata", sdata);
            map.put("ldata", ldata);
            return map;
        }
    }

    @Override
    public int getCounts() {
        return occupancyDao.getCounts();
    }

    @Override
    public Map<String, Object> occupancyRiseTrend(String dateType, Integer size) {
        String format;
        if ("month".equalsIgnoreCase(dateType)) {
            format = "%Y/%m";
            size = size == null ? 12 : size;
        } else if ("day".equalsIgnoreCase(dateType)) {
            format = "%Y/%m/%d";
            size = size == null ? 31 : size;
        } else {
            format = "%Y";
            size = size == null ? 10 : size;
        }
        List<Occupancy> list = occupancyDao.occupancyRiseTrend(format, size);
        if (list.isEmpty() || "[]".equals(list.toString())) {
            return null;
        } else {
            Map<String, Object> map = new HashMap<>();
            Integer[] sdata = new Integer[list.size()];
            String[] xdata = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                xdata[i] = list.get(i).getCompanyName();
                sdata[i] = list.get(i).getId();
            }
            map.put("sdata", sdata);
            map.put("xdata", xdata);
            return map;
        }
    }

    /**
     * 折线图
     *
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
     *
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
