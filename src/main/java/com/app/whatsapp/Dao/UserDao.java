package com.app.whatsapp.Dao;

import com.app.whatsapp.Entity.Status;
import com.app.whatsapp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, String> {
    List<User> findAllByStatus(Status status);
}
