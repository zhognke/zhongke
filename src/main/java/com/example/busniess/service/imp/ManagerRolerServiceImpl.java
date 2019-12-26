package com.example.busniess.service.imp;

import com.example.busniess.dao.ManagerRolerDao;
import com.example.busniess.entity.ManagerRoler;
import com.example.busniess.service.ManagerRolerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ManagerRolerServiceImpl implements ManagerRolerService {
    @Autowired
    ManagerRolerDao managerRolerDao;

    /**
     * 批量插入角色
     *
     * @param managerRolers
     * @return
     */
    @Override
    public Boolean insertManagerRoler(List<ManagerRoler> managerRolers) {

        return  managerRolerDao.insertManagerRoler(managerRolers);
    }

    /**
     * 更新角色关系
     * @param managerRolers
     * @return
     */

    @Override
    public boolean updateManagerRoler(List<ManagerRoler> managerRolers) {

        return managerRolerDao.updateManagerRoler(managerRolers);
    }

    /**
     * 删除角色关系
     * @param ids
     * @return
     */
    @Override
    public boolean delectManagerRoler(List<Integer> ids) {

        return managerRolerDao.delectManagerRoler(ids);
    }

    /**
     * 按条件检索
     * @param managerRoler
     * @return
     */
    @Override
    public List<ManagerRoler> selectManagerRolerByCondition(ManagerRoler managerRoler) {
        return managerRolerDao.selectManagerRoler(managerRoler);
    }
}
