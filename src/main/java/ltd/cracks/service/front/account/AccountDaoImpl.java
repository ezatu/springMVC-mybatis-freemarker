package ltd.cracks.service.front.account;

import ltd.cracks.core.base.BaseDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by macos on 2017/6/23.
 */
@Repository
public class AccountDaoImpl extends BaseDaoImpl implements AccountDao {

    // insert方法
    @Override
    public void insert(Account account){
        insert("app.account.insert",account);
    }

    // update方法
    @Override
    public void update(Account account){
        update("app.account.update",account);
    }

    // delete方法
    @Override
    public void delete(String id){
        delete("app.account.delete",id);
    }

    // findById方法
    @Override
    public Account findById(String id){
        return (Account) findById("app.account.findById",id);
    }

    // findAll方法
    @Override
    public List<Account> findAll(){
        return findAll("app.account.findAll");
    }

    @Override
    public Account selectOne(Account account) {
        return (Account) selectOne("app.account.selectOne",account);
    }

    @Override
    public List<Account> selectList(Account account) {
        return selectList("app.account.selectList",account);
    }

}
