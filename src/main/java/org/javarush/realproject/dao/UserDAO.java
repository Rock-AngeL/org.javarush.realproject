package org.javarush.realproject.dao;

/**
 * Created by APopov on 16.11.2015.
 */
import org.javarush.realproject.controller.PageStateInfo;
import org.javarush.realproject.controller.UserPage;
import org.javarush.realproject.model.User;
import java.util.List;


public interface UserDAO {
    public void create(User user);
    List read();
    User read(long id);
    UserPage readPage(PageStateInfo pageStateInfo);
    void update(User user);
    Integer delete(long id);

}
