package ltd.cracks.service.front.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by macos on 2017/6/12.
 */

public interface UserService {
    void insert(User user);
    void update(User user);
    void delete(int id);
    User findById(int id);
    List<User> findAll();
}
