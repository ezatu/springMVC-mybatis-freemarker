package ltd.cracks.service.front.accinfo;

import ltd.cracks.core.base.BaseDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by macos on 2017/6/23.
 */
@Service
public class AccInfoServiceImpl extends BaseDaoImpl implements AccInfoService {

    @Autowired
    AccInfoDaoImpl accInfoDao;

    // insert方法
    @Override
    public void insert(AccInfo accInfo){
        accInfoDao.insert("app.accInfo.insert", accInfo);
    }

    // update方法
    @Override
    public void update(AccInfo accInfo){
        accInfoDao.update("app.accInfo.update", accInfo);
    }

    // delete方法
    @Override
    public void delete(String id){
        accInfoDao.insert("app.account.delete",id);
    }

    // findById方法
    @Override
    public AccInfo findById(String id){
        return accInfoDao.findById(id);
    }

    @Override
    public List<AccInfo> findAll() {
        return accInfoDao.findAll();
    }

    @Override
    public List<AccInfo> selectList(AccInfo accInfo) {
        return accInfoDao.selectList(accInfo);
    }

    @Override
    public AccInfo selectOne(AccInfo accInfo) {
        return accInfoDao.selectOne(accInfo);
    }

}
