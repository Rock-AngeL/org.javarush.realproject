package org.javarush.realproject.service;

import org.javarush.realproject.controller.PageStateInfo;
import org.javarush.realproject.controller.UserPage;
import org.javarush.realproject.model.User;

import java.util.List;

/**
 * Created by APopov on 16.11.2015.
 */

public interface UserService {
    public void create(User user);
    List<User> read();
    UserPage readPage(PageStateInfo pageStateInfo);
    void update(User user);
    Integer delete(long id);

    User read(long id);
}
