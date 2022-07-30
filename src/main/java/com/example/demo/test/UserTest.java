package com.example.demo.test;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("test/")
public class UserTest {
  @Autowired
  UserDao userDao;

  @RequestMapping("ok")
    public String query(){
      User user = userDao.selectOneById(3);
      return user.toString();
  }
}
