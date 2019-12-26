package com.example.busniess.dao;

import com.example.busniess.entity.Roler;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import java.util.List;

public interface RolerDao {
    /**
     * 插入角色
     *
     * @param roler
     * @return
     */

    @Insert("INSERT INTO `roler` (`rolename`, `state`,`dscription`) VALUES (#{rolename}, 1,#{dscription})")
    public boolean insertRoler(Roler roler);

    /**
     * 修改角色
     */
    @Update("UPDATE `roler` SET `rolename`=#{rolename} ,`dscription`=#{dscription} WHERE (`id`=#{id})")
    public Boolean upInsertRoler(Roler roler);

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    @Delete("DELETE FROM roler WHERE id=#{id}")
    public Boolean delectRoler(Integer id);

    /**
     * 查询所有的角色名
     * @return
     */
    @Select("SELECT  * FROM roler")
    public List<Roler> selectRoler();

}
