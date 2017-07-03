package ltd.cracks.service.front.account;

import java.util.List;

/**
 * Created by macos on 2017/6/23.
 */
public interface AccountService {
    // insert方法
    void insert(Account account);

    // update方法
    void update(Account account);

    // delete方法
    void delete(String id);

    // findById方法
    Account findById(String id);

    // findAll方法
    List<Account> findAll();

    List<Account> selectList(Account account);

    Account selectOne(Account account);
}
