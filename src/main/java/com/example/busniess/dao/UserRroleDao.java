package com.example.busniess.dao;

import com.example.busniess.entity.UserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserRroleDao {
    /**
     * 增加角色和用户的关联关系
     */

    @Insert("NSERT INTO `user_role` (`rid`, `username`, `role`) VALUES (#{rid}, #{userName}, #{role})")
    public Boolean insertRolerUser(UserRole userRole);

    /**
     * 删除
     */
    @Delete("DELETE FROM `user_role` WHERE (`id`=#{id})")
    public boolean delectRoler(Integer id);

    /**
     * 修改
     */
    @Update("UPDATE `user_role` SET `rid`=#{rid},`username`=#{userName}, `role`=#{role} WHERE (`id`=#{id})")
    public boolean upRoler(UserRole userRrole);

    /**
     * 根据搜索条件搜索角色关联关系
     *
     */

    public List<UserRole> selectAllUserRole(UserRole userRole);
}
