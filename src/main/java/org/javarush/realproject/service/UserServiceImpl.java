package org.javarush.realproject.service;

import org.javarush.realproject.controller.PageStateInfo;
import org.javarush.realproject.controller.UserPage;
import org.javarush.realproject.dao.UserDAO;
import org.javarush.realproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by APopov on 16.11.2015.
 */

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    public void create(User user) {userDAO.create(user);
    }

    public List<User> read() {
        return userDAO.read();
    }

    public User read(long id) {
        return userDAO.read(id);
    }

    public UserPage readPage(PageStateInfo pageStateInfo) {
        return userDAO.readPage(pageStateInfo);
    }

    public void update(User user) {
        userDAO.update(user);
    }

    public Integer delete(long id) {
        return userDAO.delete(id);
    }
}
