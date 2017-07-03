package ltd.cracks.service.front.article;

import ltd.cracks.core.base.BaseDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by macos on 2017/7/1.
 */
@Service
public class ArticleServiceImpl extends BaseDaoImpl implements ArticleService {

    @Autowired
    private ArticleDaoImpl articleDao;

    @Override
    public void insert(Article article) {
        articleDao.insert(article);
    }

    @Override
    public void update(Article article) {
        articleDao.update(article);
    }

    @Override
    public void delete(int id) {
        articleDao.delete(id);
    }

    @Override
    public Article findById(int id) {
        return articleDao.findById(id);
    }

    @Override
    public List<Article> findAll() {
        return articleDao.findAll();
    }

    @Override
    public Article selectOne(Article article) {
        return articleDao.selectOne(article);
    }

    @Override
    public List<Article> selectList(Article article) {
        return articleDao.selectList(article);
    }

    @Override
    public List<Article> selectListByPage(Article article) {
        return articleDao.selectListByPage(article);
    }
}
