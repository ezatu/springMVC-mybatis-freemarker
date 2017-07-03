package ltd.cracks.service.front.accinfo;

import java.util.List;

/**
 * Created by macos on 2017/6/23.
 */
public interface AccInfoDao {
    // insert方法
    void insert(AccInfo accInfo);

    // update方法
    void update(AccInfo accInfo);

    // delete方法
    void delete(String id);

    // findById方法
    AccInfo findById(String id);

    // findAll方法
    List<AccInfo> findAll();

    AccInfo selectOne(AccInfo accInfo);

    List<AccInfo> selectList(AccInfo accInfo);

}
