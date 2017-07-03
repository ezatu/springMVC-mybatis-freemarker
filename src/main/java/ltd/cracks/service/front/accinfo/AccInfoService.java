package ltd.cracks.service.front.accinfo;

import java.util.List;

/**
 * Created by macos on 2017/6/23.
 */
public interface AccInfoService {
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

    List<AccInfo> selectList(AccInfo accInfo);

    AccInfo selectOne(AccInfo accInfo);
}
