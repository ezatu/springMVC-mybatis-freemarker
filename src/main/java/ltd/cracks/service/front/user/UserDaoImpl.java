package ltd.cracks.service.front.user;

import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by macos on 2017/6/22.
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public int insert(User user) {
        int result = 0;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            result = session.insert("front.user.insert",user);
        } catch (Exception e) {
            session.rollback(true);
            result = 0;
        }
        session.commit();
        session.close();
        return result;
    }

    @Override
    public int update(User user) {
        int result = 0;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            result = session.insert("front.user.update",user);
        } catch (Exception e) {
            session.rollback(true);
            result = 0;
        }
        session.commit();
        session.close();
        return result;
    }

    @Override
    public int delete(int id) {
        int result = 0;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            result = session.insert("front.user.delete",id);
        } catch (Exception e) {
            session.rollback(true);
            result = 0;
        }
        session.commit();
        session.close();
        return result;
    }

    @Override
    public User findById(int id) {
        SqlSession session = sqlSessionFactory.openSession();
        User user = session.selectOne("front.user.findById",id);
        session.commit();
        session.close();
        return user;
    }

    @Override
    public List<User> findAll() {
        SqlSession session = sqlSessionFactory.openSession();
        List<User> list = session.selectList("front.user.findAll");
        session.commit();
        session.close();
        return list;
    }
}
