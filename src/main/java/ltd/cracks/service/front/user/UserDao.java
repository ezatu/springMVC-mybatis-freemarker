package ltd.cracks.service.front.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by macos on 2017/6/9.
 */
@Service
@Transactional
public interface UserDao {

    void save(User user);
    boolean update(User user);
    boolean delete(int id);
    User findById(int id);
    List<User> findAll();
}
