package com.example.busniess.dao;

import com.example.busniess.entity.MsendMail;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MsendMailDao {
    /**
     * 名字：新增邮箱服务器
     *
     * @param msendMail
     * @return
     */
    @Insert("INSERT INTO `msend_mail` (`server`, `port`, `mail`,`name`,`password`) VALUES (#{server}, #{port}, #{mail},#{name},#{password})")
    public Boolean insertUser(MsendMail msendMail);

    /**
     * 删除邮箱服务器
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
     * 修改邮箱服务器
     *
     * @return
     */
    public Boolean updateMail(MsendMail msendMail);

}
