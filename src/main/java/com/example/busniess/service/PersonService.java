package com.example.busniess.service;

import com.example.busniess.entity.Person;
import com.example.busniess.entity.Reject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface PersonService {
    /**
     * 新增私人认证
     */

    public boolean addPerson(Person person);

    /**
     * 删除
     */
    public boolean delectPerson(Integer id);

    /**
     * 修改
     */
    public Boolean updatePerson(Person person);

    /**
     * 查看所有的
     */

    public PageInfo selectAllPerson(Integer pigeNumber,Integer pigeSize);

    /**
     * 查看自己的
     */
    public Person selectMyPerson(String uName);

    /**
     * 修改审核状态
     *
     * @return
     */
    public boolean upStatue(Integer id, Integer rid, String userName, Integer statue, Integer reId);

    /**
     * 驳回
     */
    public Boolean rejectPerson(Reject reject);

    /**
     * 查看具体的
     * @param id
     * @return
     */
    public  Person selectOnePerson(String userName,Integer statue);

}
