package com.aiyuedu.demo1.service;

import com.aiyuedu.demo1.dao.UserDao;
import com.aiyuedu.demo1.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public User FindNameAndPsw(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);
    }
    public void save(User user1) {
        userDao.save(user1);
    }
    public List<User> findByName(String username) {
        return userDao.findByUsername(username);
    }
}
