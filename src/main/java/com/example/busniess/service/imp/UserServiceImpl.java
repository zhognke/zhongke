package com.example.busniess.service.imp;

import com.example.busniess.dao.UserDao;
import com.example.busniess.entity.User;
import com.example.busniess.exception.MyException;
import com.example.busniess.resultpackage.CodeMsg;
import com.example.busniess.service.UserService;
import com.example.busniess.utiles.Md5Utiles;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    /**
     * 按名字查询用户
     */

    public User findUserByName(String userName) {
        User user = userDao.selectUser(userName);

        return user;
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    public Map findAllUser(Integer startPage) {
        Map map = new HashMap();
        PageHelper.startPage(startPage, 10);
        List<User> users = userDao.selectAllUser();
        PageInfo pageInfo = new PageInfo(users);
        map.put("data", pageInfo);
        map.put("total", pageInfo.getTotal());
        map.put("pages", pageInfo.getPages());
        map.put("nowpage", pageInfo.getPageNum());
        return map;
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    public Boolean removeUser(Integer id) {
        return userDao.delectUser(id);
    }

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    public Boolean addUser(User user) throws MyException {
        //1.查一下一下数据库账号是否已经注册了确保唯一性
        String name = user.getUserName();//账户号
        String email=user.getEmail();//邮箱
        User user1 = userDao.selectUserByName(name);
        if (user1 != null) {
            throw new MyException(CodeMsg.USER_ALREADY_EXSIS);
        }
        User user2 = userDao.selectUserByEmail(email);

        String password = user.getPassword();//密码
        if (user2 != null) {
            throw new MyException(CodeMsg.EMAIL_Have_EXIST);
        }
        user.setPassword(Md5Utiles.returnMd5("md5", password, name, 1024));

        return userDao.insertUser(user);
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    public Boolean modifiUser(User user) {
        String password = user.getPassword();
        if (password != null && password != "") {

            password = Md5Utiles.returnMd5("md5", user.getPassword(), user.getUserName(), 1024);
            user.setPassword(password);
        }
        return userDao.updateUser(user);
    }

    /**
     * 修改密码
     */
    public Boolean retSetPassword(String userName, String password,String newPassword) throws MyException {
        User user = userDao.selectUser(userName);//根据用户名查询用户
        if(user==null){
            throw new MyException(CodeMsg.USER_NOT_EXSIS);
        }

        String ps = Md5Utiles.returnMd5("md5", password, userName, 1024);
        if (!user.getPassword().equals(ps)) {
           throw new MyException(CodeMsg.WRONG_PASSWORD);//密码和原密码不一致
        }
        String newPs = Md5Utiles.returnMd5("md5",newPassword, userName, 1024);
        user.setPassword( newPs);
       // userDao.updatPassword(user);//更改密码

        return true;
    }

    /**
     * 根据用户名查询自己的角色
     *
     * @param userName
     * @return
     */
    public Set<String> findMyRole(String  userName) {
        return userDao.findRole(userName);
    }

    /**
     * 找到所有的角色
     */
    public Set<String> findAllRole() {
        return userDao.findAllRole();
    }

}