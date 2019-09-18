package com.example.busniess.dao;

import com.example.busniess.entity.MsendMail;
import com.example.busniess.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MsendMailDao {
    /**
     * 名字：新增用户
     *
     * @param msendMail
     * @return
     */
    @Insert("INSERT INTO `msend_mail` (`server`, `port`, `mail`,`name`,`password`) VALUES (#{server}, #{port}, #{mail},#{name},#{password})")
    public Boolean insertUser(MsendMail msendMail);

    /**
     * 删除用户
     *
     * @return
     */
    @Delete("DELETE FROM `msend_mail` WHERE (`id`=#{id})")
    public Boolean delectUser(Integer id);

    /**
     * 查看所有
     */
    @Select("SELECT * FROM `msend_mail`")
    public List<MsendMail> selectAll();

    /**
     * 修改用户信息
     *
     * @return
     */
    public Boolean updateMail(MsendMail msendMail);

}
