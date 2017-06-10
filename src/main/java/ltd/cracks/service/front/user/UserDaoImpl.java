package ltd.cracks.service.front.user;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by macos on 2017/6/10.
 */
@Service
public class UserDaoImpl implements UserDao {

    //需要向dao实现类中注入sqlSessionFactory
    private SqlSessionFactory sqlSessionFactory;
    //在这里用构造方法注入
    public UserDaoImpl(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void save(User user) {
        SqlSession session = sqlSessionFactory.openSession();
        session.insert("ltd.cracks.service.front.user.UserDao.save",user);
        session.commit();
        session.close();
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
