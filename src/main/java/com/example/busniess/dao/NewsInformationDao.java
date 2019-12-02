package com.example.busniess.dao;

import com.example.busniess.entity.NewsInformation;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface NewsInformationDao {
    /**
     * 增加新闻资讯
     * @param newsInformation
     */
    @Insert("INSERT INTO `newsinformation` (`uname`, `" +
            "category`, `title`, `cover`, `detail`, `submittime`,`resource`,`publishDate`) VALUES " +
            "(#{uName}, #{category}, #{title}, #{cover}, #{detail}, NOW(),#{resource},#{publishDate})")
    public Boolean insertNewsInformation(NewsInformation newsInformation);

    /**
     * 删除新闻资讯
     */
    @Delete("DELETE FROM newsinformation WHERE id=#{id}")
    public Boolean delectNewsInformation(Integer id);

    /**
     * 修改新闻资讯
     */
    @Update("UPDATE `newsinformation` SET " +
            "`category`=#{category}, `title`=#{title}, `cover`=#{cover}," +
            " `detail`=#{detail}, `uptime`=NOW(),`resource`=#{resource},`publishDate`=#{publishDate} WHERE (`id`=#{id})")
    public Boolean udateNewsInformation(NewsInformation newsInformation);

    /**
     * 查看所有新闻资讯
     */
    @Select("SELECT * FROM newsinformation ORDER BY `resource` DESC")

    public List<NewsInformation> selectNewsInformation();

    /**
     * 根据资讯类别查看新闻资讯
     *
     * @param category
     * @return
     */
    @Select("SELECT * FROM newsinformation WHERE `category`=#{category} ORDER BY `resource` DESC")

    public List<NewsInformation> selectNewsInformationByCategory(String category);

    /**
     * 查看单个新闻资讯
     * @param id
     * @return
     */
    @Select("SELECT * FROM newsinformation WHERE id=#{id}")
    public NewsInformation selectOneNewsInformation(Integer id);

}
