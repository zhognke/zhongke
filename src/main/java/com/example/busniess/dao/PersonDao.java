package com.example.busniess.dao;

import com.example.busniess.entity.Person;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PersonDao {
    /**
     * 插入私人认证
     * @param person
     * @return
     */
    @Insert("INSERT INTO `person` (`uname`, `name`, `personcode`, `unit`, `territory`,`province`,`city`,`district`,`experience`, `telephone`, `post`, `statue`, `inserttime`) VALUES " +
            "(#{uName}, #{name}, #{personCode}, #{unit},#{territory},#{province}, #{city},#{district},#{experience},#{telephone},#{post}, 0, NOW())")
    public Boolean insertPerson(Person person);
    /**
     * 修改私人认证
     */
    @Update("UPDATE `person` SET  `unit`=#{unit}, `territory`=#{territory}, `province`=#{province},`city`=#{city},`district`=#{district},`experience`=#{experience}, `telephone`=#{telephone}, `post`=#{post},`updatetime`= NOW() WHERE (`id`=#{id})")
    public Boolean updatePerson(Person person);

    /**
     * 删除
     * @param id
     * @return
     */
    @Delete("DELETE FROM `person` WHERE id=#{id}")
    public boolean delectPerson(Integer id);

    /**
     * 查询所有的
     * @return
     */
    @Select("SELECT * from person")
    public List<Person> selectAllPerson();

    /**
     * 查看具体的
     * @param id
     * @return
     */
    @Select("SELECT * from person WHERE id=#{id}")
    public Person selectOnePerson(Integer id);

    /**
     * 查询自己的
     * @param uName
     * @return
     */
    @Select("select * from person where uname=#{uName}")
    public Person selectPerson(String uName);

    /**
     * 根据id查看具体的
     * @param id
     * @return
     */
    @Select("select * from person where id=#{id}")
    @Results({
            @Result(property = "reject", column = "id", one = @One(select = "com.example.busniess.dao.RejectDao.selectRejectpByPerson"))
    })
    public Person selectPersonByid(Integer id);



    /**
     * 修改审核状态
     */
    @Update("UPDATE person SET statue=#{statue} WHERE id=#{id}")
    public Boolean upStatue(@Param("statue") Integer statue,@Param("id") Integer id);
}
