package com.example.busniess.service.imp;

import com.example.busniess.dao.BusinessCenterInformationDao;
import com.example.busniess.dao.OccupancyDao;
import com.example.busniess.dao.TalentDemandDao;
import com.example.busniess.entity.BusinessCenterInformationEntity;
import com.example.busniess.entity.TalentDemandEntity;
import com.example.busniess.service.TalentDemandService;
import com.example.busniess.utiles.EchartsEntity;
import com.example.busniess.utiles.RedisKey;
import com.example.busniess.utiles.RedisUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("talentDemandService")
public class TalentDemandServiceImpl implements TalentDemandService {
    @Autowired
    OccupancyDao occupancyDao;
    @Autowired
    TalentDemandDao talentDemandDao;
    @Autowired
    BusinessCenterInformationDao businessCenterInformationDao;

    @Autowired
    RedisUtil redisUtil;

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<TalentDemandEntity> selectAll() {
        return talentDemandDao.selectAll();
    }

    /**
     * 根据条件搜索
     *
     * @param talentDemandEntity
     * @return
     */
    @Override
    public List<TalentDemandEntity> search(TalentDemandEntity talentDemandEntity) {
        return talentDemandDao.search(talentDemandEntity);
    }

    /**
     * 分页展示
     *
     * @param talentDemandEntity
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo showByPage(TalentDemandEntity talentDemandEntity, Integer pageNum, Integer pageSize) {
        if (talentDemandEntity.getEngagedIndustry() != null) {
            talentDemandEntity.setEngagedIndustry(talentDemandEntity.getEngagedIndustry().replaceAll(",", "','"));
        }
        if (talentDemandEntity.getTechnologyScope() != null) {
            talentDemandEntity.setTechnologyScope(talentDemandEntity.getTechnologyScope().replaceAll(",", "','"));
        }
        if (talentDemandEntity.getDemandsType() != null) {
            talentDemandEntity.setDemandsType(talentDemandEntity.getDemandsType().replaceAll(",", "','"));
        }
        if (talentDemandEntity.getSalary() != null) {
            talentDemandEntity.setSalary(talentDemandEntity.getSalary().replaceAll(",", "','"));
        }
        if (talentDemandEntity.getDegree() != null) {
            talentDemandEntity.setDegree(talentDemandEntity.getDegree().replaceAll(",", "','"));
        }
        /*if (talentDemandEntity.getOrderField() == null) {
            talentDemandEntity.setOrderField("create_time");
            talentDemandEntity.setOrderType("desc");
        }*/
        String industryExperience = talentDemandEntity.getIndustryExperience();
        if (industryExperience != null && industryExperience != "") {
            String srr[] = industryExperience.split("-");
            talentDemandEntity.setIndustryExperienceBegin(srr[0]);
            if (srr.length > 1) {
                talentDemandEntity.setIndustryExperienceEnd(srr[1]);
            }
        }
        PageHelper.startPage(pageNum, pageSize);
        List<TalentDemandEntity> list = talentDemandDao.search(talentDemandEntity);
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
//    @Cacheable(value = "talent",key="'talent'+#id")
    public TalentDemandEntity selectById(Integer id) {
        return talentDemandDao.selectById(id);
    }

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    @Override
    public TalentDemandEntity selectById(Integer id, Integer size) {
        TalentDemandEntity entity = talentDemandDao.selectById(id);
        if (entity != null && entity.getUserName() != null) {
            BusinessCenterInformationEntity businessCenterInformationEntity = businessCenterInformationDao.selectOnByUname(entity.getUserName());
            entity.setBusinessCenter(businessCenterInformationEntity);
            entity.setOccupancyList(occupancyDao.getOccupanyForProfessional(entity.getUserName(), size));
            //记录浏览量到redis,然后定时更新到数据库
            String key = RedisKey.TALENT_VIEW_COUNT_CODE + entity.getId();
            //找到redis中该篇文章的点赞数，如果不存在则向redis中添加一条
            Map<Object, Object> viewCountItem = redisUtil.hmget(RedisKey.TALENT_VIEW_COUNT_KEY);
            Integer viewCount = entity.getViewCount();
            if (!viewCountItem.isEmpty()) {
                if (viewCountItem.containsKey(key)) {
                    viewCount = (Integer) viewCountItem.get(key);
                    entity.setViewCount(viewCount);
                }
            }
            redisUtil.hset(RedisKey.TALENT_VIEW_COUNT_KEY, key, ++viewCount);
        }
        return entity;
    }

    /**
     * 新增
     *
     * @param talentDemandEntity
     * @return
     */
    @Override
    public boolean add(TalentDemandEntity talentDemandEntity) {
        return talentDemandDao.add(talentDemandEntity);
    }

    /**
     * 逻辑删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean delectById(Integer id) {
        return talentDemandDao.deleteById(id);
    }

    /**
     * 从数据库中删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean realDeleteById(Integer id) {
        return talentDemandDao.realDeleteById(id);
    }

    /**
     * 修改
     *
     * @param talentDemandEntity
     * @return
     */
//    @CacheEvict(value="talent",key="'talent'+#talentDemandEntity.id")
    @Override
    public boolean update(TalentDemandEntity talentDemandEntity) {
        talentDemandEntity.setApprovalStatus(0);
        return talentDemandDao.updateById(talentDemandEntity);
    }

    /**
     * 修改状态
     *
     * @param id
     * @param status
     * @return
     */
    @Override
    public boolean updateStatus(Integer id, Integer status, String userName) {
        TalentDemandEntity talentDemandEntity = new TalentDemandEntity();
        talentDemandEntity.setId(id);
        talentDemandEntity.setStatus(status);
        return talentDemandDao.updateById(talentDemandEntity);
    }

    /**
     * 修改审批状态
     *
     * @param id
     * @param approvalStatus
     * @param approvalOpinion
     * @return
     */
    @Override
    public boolean updateApprovalStatus(Integer id, Integer approvalStatus, String approvalOpinion) {
        TalentDemandEntity talentDemandEntity = new TalentDemandEntity();
        talentDemandEntity.setId(id);
        talentDemandEntity.setApprovalStatus(approvalStatus);
        talentDemandEntity.setApprovalOpinion(approvalOpinion);
        return talentDemandDao.updateById(talentDemandEntity);
    }

    /**
     * 关闭需求
     *
     * @param id
     * @param closeReason
     * @return
     */
    @Override
    public boolean closeDemands(Integer id, String closeReason) {
        Integer status = 3;
        return talentDemandDao.closeById(id, status, closeReason);
    }

    /**
     * 关闭需求-管理员
     *
     * @param id
     * @param closeReason
     * @return
     */
    @Override
    public boolean closeDemandsForManager(Integer id, String closeReason) {
        Integer status = 2;
        return talentDemandDao.closeById(id, status, closeReason);
    }

    @Override
    public void updateArticleViewCount(Integer articleId, Integer viewCount) {
        talentDemandDao.updateArticleViewCount(articleId, viewCount);
    }

    @Override
    public boolean deleteBatch(String ids) {
        ids = ids.replaceAll(",", "','");
        return talentDemandDao.deleteBatch(ids);
    }

    @Override
    public Map<String, Object> demandsIndustryProp(Integer size) {
        List<TalentDemandEntity> list = talentDemandDao.demandsIndustryProp(size);
        Map<String, Object> map = new HashMap<>();
        if (list.isEmpty() || "[]".equals(list.toString())) {
            return null;
        } else {
            List<EchartsEntity> sdata = new ArrayList<>();
            String[] ldata = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                EchartsEntity echartsEntity = new EchartsEntity();
                ldata[i] = list.get(i).getEngagedIndustry();
                echartsEntity.setName(list.get(i).getEngagedIndustry());
                echartsEntity.setValue(Double.parseDouble(String.valueOf(list.get(i).getCounts())));
                sdata.add(echartsEntity);
            }
            map.put("sdata", sdata);
            map.put("ldata", ldata);
        }
        return map;
    }

    @Override
    public int getCounts() {
        return talentDemandDao.getCounts();
    }

    @Override
    public Map<String, Object> demandsRiseTrend(String type, Integer size) {
        String format;
        if ("month".equalsIgnoreCase(type)) {
            format = "%Y/%m";
            size = size == null ? 12 : size;
        } else if ("day".equalsIgnoreCase(type)) {
            format = "%Y/%m/%d";
            size = size == null ? 31 : size;
        } else {
            format = "%Y";
            size = size == null ? 10 : size;
        }
        List<TalentDemandEntity> list = talentDemandDao.demandsRiseTrendByDate(format, size);
        if (list.isEmpty() || "[]".equals(list.toString())) {
            return null;
        } else {
            Map<String, Object> map = new HashMap<>();
            Integer[] sdata = new Integer[list.size()];
            String[] xdata = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                xdata[i] = list.get(i).getCompanyName();
                sdata[i] = list.get(i).getCounts();
            }
            map.put("sdata", sdata);
            map.put("xdata", xdata);
            return map;
        }
    }

    @Override
    public boolean updateHot(Integer id, Integer isHot) {
        return talentDemandDao.updateHot(id, isHot);
    }

}
