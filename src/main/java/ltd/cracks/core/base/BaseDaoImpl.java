package ltd.cracks.core.base;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by macos on 2017/6/22.
 */
@Repository
public class BaseDaoImpl implements BaseDao {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public void insert(String str, Object object) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.insert(str,object);
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback(true);
        }
        session.commit();
        session.close();
    }

    @Override
    public void update(String str, Object object) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.update(str,object);
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback(true);
        }
        session.commit();
        session.close();
    }

    @Override
    public void delete(String str, int id) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.delete(str,id);
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback(true);
        }
        session.commit();
        session.close();
    }

    @Override
    public Object findById(String str, int id) {
        Object result = new Object();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            result = session.selectOne(str,id);
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback(true);
        }
        session.commit();
        session.close();
        return result;
    }

    @Override
    public List findAll(String str) {
        List result = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            result = session.selectList(str);
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback(true);
        }
        session.commit();
        session.close();
        return result;
    }
}
