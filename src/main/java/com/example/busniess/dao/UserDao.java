package com.example.busniess.dao;

import com.example.busniess.entity.BusinessCenter;
import com.example.busniess.entity.Person;
import com.example.busniess.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Set;

public interface UserDao {
    /**
     * 名字：新增用户
     *
     * @param user
     * @return
     */
    @Insert("INSERT INTO `user` (`username`, `password`, `phonenumber`,`statue`,`email`,`lastdate`,`ip`,`persion`,`insertTime`) VALUES (#{userName}, #{password}, #{phoneNumber},1,#{email},#{lastdate},#{ip},#{persion},NOW())")
    public Boolean insertUser(User user);

    /**
     * 获取根据用户信息
     */
    @Select("SELECT * FROM `user` WHERE username=#{userName} OR email=#{userName}")
    public User selectUser(String userName);

    /**
     * 根据名字查询用户
     */
    @Select("SELECT * FROM `user` WHERE username=#{userName} and statue=1")
    public User selectUserByName(String userName);

    @Select("SELECT * FROM `user` WHERE username=#{userName} and statue!=3")
    public User selectAllUser(String userName);

    /**
     * 根据邮箱查询用户
     */
    @Select("SELECT * FROM `user` WHERE email=#{email}")
    public User selectUserByEmail(String email);

    /**
     * 删除用户
     *
     * @return
     */
    @Delete("DELETE FROM `user` WHERE (`id`=#{id})")
    public Boolean delectUser(Integer id);

    /**
     * 查看所有用户
     */
    @Select("SELECT * FROM `user`")
    public List<User> selectAllUsers();

    /**
     * 修改用户信息
     *
     * @return
     */
    public Boolean updateUser(User user);

    /**
     * 根据邮箱和用户名修改密码
     */
    @Update("UPDATE `user` SET  `password`=#{password} WHERE `username`=#{userName} OR `email`=#{userName}")
    public Boolean updatPassword(User user);

    /**
     * 根据名字更改用角色
     */
    @Update("UPDATE `user` SET  `userRole`=#{userRole} WHERE `username`=#{userName}" )
    public  boolean updateUserRole(User user);





    /**
     * 查询当前用户所有的角色
     *
     * @param userName  用户名
     * @return
     */

    @Select("SELECT r.rolename FROM `user` u INNER JOIN `user_role` ur ON u.username=ur.username INNER JOIN `roler`  r ON ur.rid=r.id AND  r.state=1  AND u.username=#{userName}")
    public Set<String> findRole(String userName);


    /**
     * 查询所有的角色
     */
    @Select("SELECT `rolename` FROM `roler` WHERE state=1")
    public Set<String> findAllRole();






    /**
     * 给用户授权
     *  @param rid//角色id
     * @param userName//用户名
     * @return
     */
    @Insert("INSERT INTO `user_role` (`rid`, `username`) VALUES (#{rid}, #{userName})")
    public Boolean   authorization(@Param("rid") Integer rid ,@Param("userName")String userName);

    /**
     * 按条件搜索按包含企业信息
     * @param businessCenter
     * @return
     */
    public  List<User> selectUserByConditionB(BusinessCenter businessCenter);

    /**
     * 按条件搜索包含个人信息p
     * @param person
     * @return
     */

    public  List<User> selectUserByConditionP(Person person);

    /**
     * 根据条件显示用户
     * 后台用 的方法
     * @param user
     * @return
     */

    public  List<User> selectUserByAdmin(User user);




}
