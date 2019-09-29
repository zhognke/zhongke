package com.example.busniess.dao;

import com.example.busniess.entity.BusinessCenter;
import com.example.busniess.entity.Reject;
import org.apache.ibatis.annotations.*;

public interface RejectDao {
    /**
     * 添加驳回
     */

    @Insert("INSERT INTO `reject` (`bid`, `content`, `rejecttime`) VALUES (#{bId}, #{content}, NOW())")
    public Boolean addReject(Reject reject);

    /**
     * 删除驳回
     */
    @Delete("DELETE FROM reject WHERE id=#{id}")
    public Boolean removeReject(Integer id);

    /**
     * 根据企业中心id查询驳回理由
     *
     * @return
     */
    @Select("SELECT * from reject WHERE bid=#{bId}")
    public Reject selectReject(Insert bId);


    /**
     *查询企业认证信息以及驳回原因
     */
    @Select("SELECT * from businesscenter WHERE uname=#{userName}")
    @Results({
            @Result(property = "reject", column = "id", one = @One(select = "com.example.busniess.dao.RejectDao.selectReject"))
    })
    public BusinessCenter selectBussinessAndReject(String userName);


}
