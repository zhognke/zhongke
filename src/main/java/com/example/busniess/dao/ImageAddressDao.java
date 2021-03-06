package com.example.busniess.dao;

import com.example.busniess.entity.ImageAddress;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ImageAddressDao {

    /**
     * 插入图片地址
     * @param imageAddress
     * @return
     * INSERT INTO `imageaddress` (`oid`, `imgaddress`) VALUES ('1', '22121')
     */
    public  boolean insertImageAddress( List<ImageAddress> imageAddress);


    /**
     * 删除自己图片
     */
    @Delete("DELETE FROM `imageaddress` WHERE (`oid`=#{oId})")
    public Boolean delectImageAddress(Integer oId);

    /**
     * 删除单张图片
     * @param id
     * @return
     */
    @Delete("DELETE FROM `imageaddress` WHERE (`id`=#{id})")
    public Boolean delectOneImageAddress(Integer id);

    /**
     * 批量修改图片
     */
//    @Update("UPDATE `imageaddress` SET `img`=#{img} WHERE (`id`=#{id})")
   public Boolean upDateImageAddress(List<ImageAddress> imageAddresses);

    /**
     *查看图片
     * @param id
     * @return
     */
    @Select("SELECT * FROM imageaddress WHERE oid=#{id}")
    public List<ImageAddress> selectimgAddress(Integer id);



}
