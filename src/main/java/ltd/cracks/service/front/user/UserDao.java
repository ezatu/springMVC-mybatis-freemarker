package ltd.cracks.service.front.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by macos on 2017/6/9.
 */
public interface UserDao {

    void insert(User user);
    void update(User user);
    void delete(int id);
    User findById(int id);
    List<User> findAll();
}
