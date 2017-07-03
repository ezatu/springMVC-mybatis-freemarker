package ltd.cracks.service.front.product;

import ltd.cracks.core.base.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by macos on 2017/6/23.
 */
@Repository
public class ProductDaoImpl extends BaseDaoImpl implements ProductDao {
    @Override
    public void insert(Product product) {
        insert("front.product.insert", product);
    }

    @Override
    public void update(Product product) {
        update("front.product.update", product);
    }

    @Override
    public void delete(String id) {
        delete("front.product.delete",id);
    }

    @Override
    public Product findById(String id) {
        return (Product) findById("front.product.findById",id);
    }

    @Override
    public List<Product> findAll() {
        return findAll("front.product.findAll");
    }

    @Override
    public List<Product> findByOwnerId(int ownerId) {
        return selectList("front.product.findByOwnerId",ownerId);
    }
}
