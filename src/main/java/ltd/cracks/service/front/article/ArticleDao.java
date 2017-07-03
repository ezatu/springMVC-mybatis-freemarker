package ltd.cracks.service.front.article;



import java.util.List;

/**
 * Created by macos on 2017/6/23.
 */
public interface ArticleDao {
    // insert方法
    void insert(Article article);

    // update方法
    void update(Article article);

    // delete方法
    void delete(int id);

    // findById方法
    Article findById(int id);

    // findAll方法
    List<Article> findAll();

    Article selectOne(Article article);

    List<Article> selectList(Article article);

    List<Article> selectListByPage(Article article);

}
