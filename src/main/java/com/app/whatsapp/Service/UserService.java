package com.app.whatsapp.Service;

import com.app.whatsapp.Dao.UserDao;
import com.app.whatsapp.Entity.Status;
import com.app.whatsapp.Entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User SaveUser(User user){
        user.setStatus(Status.ONLINE);
        return userDao.save(user);
    }

    public User disconnect(User user){
       User storedUser =  userDao.findById(user.getId()).orElse(null);
       if(storedUser != null){
           storedUser.setStatus(Status.OFFLINE);
          return userDao.save(storedUser);
       }
       return null;
    }

    public List<User> findAllConnectedUser(){
        return userDao.findAllByStatus(Status.ONLINE);
    }
}
