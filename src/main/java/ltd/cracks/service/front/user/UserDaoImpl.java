package ltd.cracks.service.front.user;

import com.sun.xml.internal.rngom.parse.host.Base;
import ltd.cracks.core.base.BaseDao;
import ltd.cracks.core.base.BaseDaoImpl;
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
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    @Override
    public void insert(User user) {
        insert("front.user.insert",user);
    }

    @Override
    public void update(User user) {
        update("front.user.update",user);
    }

    @Override
    public void delete(String id) {
        delete("front.user.delete",id);
    }

    @Override
    public User findById(String id) {
        return (User) findById("front.user.findById",id);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) findAll("front.user.findAll");
    }
}
