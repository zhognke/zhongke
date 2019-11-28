package com.example.busniess.dao;

import com.example.busniess.entity.Notification;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface NotificationDao {

    /**
     * 添加通知
     */
    @Insert("INSERT INTO `notification` (`title`, `content`, `addtime`, `uname`, `inserttime`, `sort`) VALUES (#{title}, #{content}, #{addTime}, #{uName}, NOW(), #{sort})")
    public Boolean insertNotification(Notification notification);

    /**
     * 删除
     */
    @Delete("DELETE FROM `notification` WHERE (`id`=#{id})")
    public boolean delectNotification(Integer id);

    /**
     * 修改
     */
    @Update("UPDATE `notification` SET `title`=#{title}, `content`=#{content}, `addtime`=#{addTime}, `uptime`=NOW(), `sort`=#{sort} WHERE (`id`=#{id})")
    public Boolean upNotification(Notification notification);

    /**
     * 查看所有
     *
     * @return
     */
    @Select("SELECT * FROM notification ORDER BY inserttime DESC ")
    public List<Notification> selectAllNotification();

    /**
     * 查看一个
     * @param id
     * @return
     */
    @Select("SELECT * FROM notification WHERE id=#{id}")
    public Notification selectOneNotification(Integer id);

}
