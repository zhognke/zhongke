package com.example.busniess.dao;

import com.example.busniess.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserDao {
    /**
     * 名字：新增用户
     * @param user
     * @return
     */
    @Insert("INSERT INTO `user` (`username`, `password`, `phonenumber`) VALUES (#{userName}, #{password}, #{phoneNumber})")
    public  Boolean insertUser(User user);
    /**
     * 获取用户信息
     */
    @Select("SELECT * FROM `user` WHERE username=#{userName}")
    public User selectUser(String userName);

    /**
     * 删除用户
     * @return
     */
    @Delete("DELETE FROM `user` WHERE (`id`=#{id})")
    public  Boolean delectUser(Integer id );

    /**
     * 查看所有用户
     */
    @Select("SELECT * FROM `user`")
    public List<User> selectAllUser();

    /**
     * 修改用户信息
     * @return
     */
    @Update("UPDATE `user` SET `username`=#{userName}, `password`=#{password}, `phonenumber`=#{phoneNumber} WHERE (`id`=#{id})")
    public Boolean updateUser();

}
