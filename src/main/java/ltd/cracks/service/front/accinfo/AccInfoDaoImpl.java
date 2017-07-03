package ltd.cracks.service.front.accinfo;

import ltd.cracks.core.base.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by macos on 2017/6/23.
 */
@Repository
public class AccInfoDaoImpl extends BaseDaoImpl implements AccInfoDao {

    // insert方法
    @Override
    public void insert(AccInfo accInfo){
        insert("app.accInfo.insert", accInfo);
    }

    // update方法
    @Override
    public void update(AccInfo accInfo){
        update("app.accInfo.update", accInfo);
    }

    // delete方法
    @Override
    public void delete(String id){
        delete("app.accInfo.delete",id);
    }

    // findById方法
    @Override
    public AccInfo findById(String id){
        return (AccInfo) findById("app.accInfo.findById",id);
    }

    // findAll方法
    @Override
    public List<AccInfo> findAll(){
        return findAll("app.accInfo.findAll");
    }

    @Override
    public AccInfo selectOne(AccInfo accInfo) {
        return (AccInfo) selectOne("app.accInfo.selectOne", accInfo);
    }

    @Override
    public List<AccInfo> selectList(AccInfo accInfo) {
        return selectList("app.accInfo.selectList", accInfo);
    }

}
