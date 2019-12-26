package com.example.busniess.service.imp;

import com.example.busniess.dao.UserDao;
import com.example.busniess.dao.UserRroleDao;
import com.example.busniess.entity.User;
import com.example.busniess.entity.UserRole;
import com.example.busniess.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class UserRoleServiceImpl implements UserRoleService {


    @Autowired
    UserRroleDao userRroleDao;
    @Autowired
    UserDao userDao;
    /**
     * 新建角色关联关系
     * @param userRoles
     * @return
     */
    @Override
    @Transactional
    public boolean insertUserRoler(List<UserRole> userRoles) {

        //1.获取用户名
        String userName = userRoles.get(0).getUserName();
  List<UserRole>  list= userRroleDao.selectUserRoleByUserName(userName);

        //2.根据用户名删除对应关系
        if (list!=null||list.size()!=0){
            userRroleDao.delectUserRolerByUserName(userName);
        }
        //3.如果角色名或者

        String roleName=userRoles.get(0).getRole();
        if(roleName==null||roleName==""){
            return true;
        }
        roleName="";
        for(UserRole userRoler : userRoles ){
            roleName+=userRoler.getRole()+"|";
        }
        //获取角色名 rolename
        User user=new User();
        user.setUserRole(roleName);
        user.setUserName(userName);

       if(userDao.updateUserRole(user)) {
           return userRroleDao.insertRolerUser(userRoles);
       }

        return false;
    }

    /**
     * 修改关联关联关系
     * @param userRole
     * @return
     */
    @Override
    public boolean upUserRoler(List<UserRole> userRole) {


        return userRroleDao.upRoler(userRole);
    }



    /**
     * 删除角色
     * @param ids
     * @return
     */
    @Override
    public boolean delectUserroler(List<Integer> ids) {
//        userRroleDao.delectUserRoler(ids);
       return  false;
    }

    /**
     * 查看角色
     * @param userRole
     * @return
     */
    @Override
    public List<UserRole> selectUserRoler(UserRole userRole) {
        return userRroleDao.selectAllUserRole(userRole);
    }
}
