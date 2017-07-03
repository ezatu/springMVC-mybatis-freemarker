package ltd.cracks.service.front.article;

import ltd.cracks.core.base.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by macos on 2017/7/1.
 */
@Repository
public class ArticleDaoImpl extends BaseDaoImpl implements ArticleDao {
    @Override
    public void insert(Article article) {
        insert("app.article.insert", article);
    }

    @Override
    public void update(Article article) {
        update("app.article.update", article);
    }

    @Override
    public void delete(int id) {
        delete("app.article.delete",String.valueOf(id));
    }

    @Override
    public Article findById(int id) {
        return (Article) findById("app.article.findById",String.valueOf(id));
    }

    @Override
    public List<Article> findAll() {
        return findAll("app.article.findAll");
    }

    @Override
    public Article selectOne(Article article) {
        return (Article) selectOne("app.article.selectOne",article);
    }

    @Override
    public List<Article> selectList(Article article) {
        return selectList("app.article.selectList",article);
    }

    @Override
    public List<Article> selectListByPage(Article article) {
        return selectListByPage("app.article.selectListByPage", article);
    }
}
