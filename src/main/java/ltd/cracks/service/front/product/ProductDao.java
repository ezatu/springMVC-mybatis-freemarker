package ltd.cracks.service.front.product;


import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by macos on 2017/6/12.
 */
@Repository
public interface ProductDao {
    void save(Product user);
    boolean update(Product user);
    boolean delete(int id);
    Product findById(int id);
    List<Product> findAll();
}
