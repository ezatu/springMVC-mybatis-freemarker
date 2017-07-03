package ltd.cracks.service.front.product;


import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by macos on 2017/6/12.
 */
public interface ProductDao {
    void insert(Product product);
    void update(Product product);
    void delete(String id);
    Product findById(String id);
    List<Product> findAll();
    List<Product> findByOwnerId(int ownerId);
}
