package ltd.cracks.service.front.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by macos on 2017/6/12.
 */
@Service
public class ProductService {
    // 注入dao实例
    @Autowired
    private ProductDao productDao;

    // 日志实例
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public void save(Product product) {
        productDao.save(product);
    };
    public boolean update(Product product) {
        return productDao.update(product);
    };
    public boolean delete(int id) {
        return productDao.delete(id);
    };
    public Product findById(int id) {
        return productDao.findById(id);
    };
    public List<Product> findAll() {
        return productDao.findAll();
    };
}
