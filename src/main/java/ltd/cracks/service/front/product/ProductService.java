package ltd.cracks.service.front.product;

import java.util.List;

/**
 * Created by macos on 2017/6/23.
 */
public interface ProductService {
    void insert(Product product);
    void update(Product product);
    void delete(String id);
    Product findById(String id);
    List<Product> findAll();
    List<Product> findByOwnerId(int ownerId);
}
