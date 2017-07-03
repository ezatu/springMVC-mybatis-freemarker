package ltd.cracks.service.front.account;

import ltd.cracks.core.base.BaseDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by macos on 2017/6/23.
 */
@Service
public class AccountServiceImpl extends BaseDaoImpl implements AccountService {

    @Autowired
    AccountDaoImpl accountDao;

    // insert方法
    @Override
    public void insert(Account account){
        accountDao.insert("app.account.insert",account);
    }

    // update方法
    @Override
    public void update(Account account){
        accountDao.update("app.account.update",account);
    }

    // delete方法
    @Override
    public void delete(String id){
        accountDao.insert("app.account.delete",id);
    }

    // findById方法
    @Override
    public Account findById(String id){
        return accountDao.findById(id);
    }

    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    public List<Account> selectList(Account account) {
        return accountDao.selectList(account);
    }

    @Override
    public Account selectOne(Account account) {
        return accountDao.selectOne(account);
    }

}
