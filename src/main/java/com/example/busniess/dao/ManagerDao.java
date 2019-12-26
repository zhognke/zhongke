package com.example.busniess.dao;

import com.example.busniess.entity.Manager;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


public interface ManagerDao {

    /**
     * 增加用户
     */


    @Insert("INSERT INTO `manager` (`manage`, `creattime`, `statue`, `role`, `identifier`,`password`) VALUES (#{manage}, NOW(), 1, '1', 1,#{password})")
    public Boolean insertManager(Manager manager);

    /**
     * 删除用户
     */

    @Delete("DELETE FROM manager WHERE id=#{id}")
    public boolean delectManager(Integer id);

    /**
     * 修改
     */
    @Update("UPDATE `manager` SET `manage`=#{manage}, `password`=#{password}, `uptime`=NOW(), `statue`=#{statue}, `role`=#{role}, `identifier`=#{identifier} WHERE (`id`=#{id})")
    public Boolean upDateManager(Manager manager);


    /**
     * 查看所有用户
     *
     * @return
     */


    public List<Manager> selectManager(Manager manager);


    /**
     * 根据名字搜索
     */
    @Select("SELECT * from manager WHERE `manage`=#{manage}")
    public Manager selectManagerByManage(String manage);
}
