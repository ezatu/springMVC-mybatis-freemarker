package ltd.cracks.core.base;

import java.io.Serializable;
import java.util.List;

/**
 * Created by macos on 2017/6/22.
 */
public interface BaseDao extends Serializable {
    void insert(String str, Object object);
    void update(String str, Object object);
    void delete(String str, int id);
    Object findById(String str, int id);
    List findAll(String str);
}
