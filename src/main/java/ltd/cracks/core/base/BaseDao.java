package ltd.cracks.core.base;

import java.io.Serializable;
import java.util.List;

/**
 * Created by macos on 2017/6/22.
 */
public interface BaseDao {

    // insert方法
    void insert(String str, Object object);

    // update方法
    void update(String str, Object object);

    // delete方法
    void delete(String str, String id);

    // findById方法
    Object findById(String str, String id);

    // findAll方法
    List findAll(String str);

    Object selectOne(String str,Object object);

    List selectList(String str,Object object);

    List selectListByPage(String str, Object object);
}
