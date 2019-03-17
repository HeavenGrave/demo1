package com.aiyuedu.demo1.dao;

import com.aiyuedu.demo1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User,Long>{
    public User findByUsernameAndPassword(String username, String password);
    public List<User> findByUsername(String username);

}
