package javabook.service.impl;

import javabook.dao.UserDao;
import javabook.dao.impl.UserDaoImpl;
import javabook.pojo.User;
import javabook.service.UserService;

/**
 * @author dankejun
 * @create 2020/11/10 21:00
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if (userDao.queryUserByUsername(username) == null) {
            //说明没有查到，表示可用
            return false;
        }
        return true;
    }
}
