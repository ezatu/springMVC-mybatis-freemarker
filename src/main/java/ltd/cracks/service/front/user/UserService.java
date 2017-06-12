package ltd.cracks.service.front.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by macos on 2017/6/12.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public void insert(User user) {
        userDao.save(user);
    }
}
