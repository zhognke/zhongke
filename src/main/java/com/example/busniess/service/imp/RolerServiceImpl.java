package com.example.busniess.service.imp;

import com.example.busniess.dao.RolerDao;
import com.example.busniess.dao.UserDao;
import com.example.busniess.entity.Roler;
import com.example.busniess.service.RolerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RolerServiceImpl implements RolerService {

    @Autowired
    RolerDao rolerDao;
    @Autowired
    UserDao userDao;

    /**
     * 增加角色
     * @param roler
     * @return
     */
    @Override
    public boolean insertRoler(Roler roler) {


        return  rolerDao.insertRoler(roler);
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @Override
    public boolean delectRoler(Integer id) {

        return rolerDao.delectRoler(id);
    }

    /**
     * 更新
     * @param roler
     * @return
     */
    @Override
    public boolean updateRoler(Roler roler) {


        return  rolerDao.upInsertRoler(roler);
    }

    /**
     * 查询
     * @return
     */
    @Override
    public List<Roler> selectRoler() {

        return  rolerDao.selectRoler();
    }


    /**
     * 设置角色
     */
    public  Boolean insertRoler(Integer rid,String userName){
        return userDao.authorization(rid,userName);
    }

}
