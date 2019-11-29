package com.example.busniess.service.imp;

import com.example.busniess.dao.PersonDao;
import com.example.busniess.dao.RejectDao;
import com.example.busniess.dao.UserDao;
import com.example.busniess.entity.Person;
import com.example.busniess.entity.Reject;
import com.example.busniess.service.PersonService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonDao personDao;
    @Autowired
    RejectDao rejectDao;
    @Autowired
    UserDao userDao;
    /**
     * 添加
     * @param person
     * @return
     */
    @Override
    public boolean addPerson(Person person) {

        return  personDao.insertPerson(person);
    }
    /**
     * 删除
     * @return
     */
    @Override
    public boolean delectPerson(Integer id) {
        return  personDao.delectPerson(id);
    }

    /**
     * 修改信息
     * @param person
     * @return
     */
    @Override
    public Boolean updatePerson(Person person) {

        return  personDao.updatePerson(person);
    }

    /**
     * 查看所有
     * @return
     */
    @Override
    public PageInfo selectAllPerson(Person person,Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);

        return  new PageInfo(personDao.selectAllPerson(person));
    }

    /**
     * 查看自己的
     * @param uName
     * @return
     */
    @Override
    public Person selectMyPerson(String uName) {
        return personDao.selectPerson(uName);
    }



    /**
     * 审核通过
     * @param id 用户认证id
     * @param rid 角色id
     * @param userName 用户名
     * @param statue 状态
     * @param reId 驳回理由
     * @return
     */
    @Override
    public boolean upStatue( Integer id, Integer rid, String userName,Integer statue,Integer reId) {

        if(statue==2){
            rejectDao.removeReject(reId);
        }

        if (personDao.upStatue(1 ,id)) {
            return userDao.authorization(rid,userName);
        }else {
            return false;
        }
    }

    /**
     * 审核驳回
     * @param reject
     * @return
     */
    @Override
    public Boolean rejectPerson(Reject reject) {
        //1.更新状态默认驳回
        if(personDao.upStatue(2,reject.getBId())) {
            return    rejectDao.addReject(reject);
        }else {
            return false;
        }
    }

    /**
     * 查看自己的
     * @param
     * @return
     */
    @Override
    public Person selectOnePerson(String userName,Integer statue) {
        return rejectDao.selectPersonAndReject(userName,statue);
    }

    /**
     * 查看详情1
     * @param id
     * @return
     */
    @Override
    public Person selectOnePersonById(Integer id) {

        return  personDao.selectPersonByid(id);
    }

    /**
     * 查看详情
     * @param id
     * @return
     */
    @Override
    public Person selectOnePersonByid(Integer id) {
        return  personDao.selectOnePerson(id);
    }
}
