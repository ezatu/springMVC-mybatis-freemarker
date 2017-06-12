package ltd.cracks.service.front.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by macos on 2017/6/12.
 */
@Service
public class UserService {
    // 注入dao实例
    @Autowired
    private UserDao userDao;

    // 日志实例
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public void insert(User user) {
        logger.debug("start insert to mysql method!!!!");
        userDao.save(user);
    }

    public User findById(int id) {
        return userDao.findById(id);
    }

    public Boolean update(User user) {
        return userDao.update(user);
    }

    public Boolean delete(int id) {
        return userDao.delete(id);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }
}
