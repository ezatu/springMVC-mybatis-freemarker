package ltd.cracks.service.front.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by macos on 2017/6/12.
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    // 注入dao实例
    @Autowired
    private ProductDaoImpl productDao;

    // 日志实例
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public void insert(Product product) {
        productDao.insert(product);
    }

    @Override
    public void update(Product product) {
         productDao.update(product);
    }

    @Override
    public void delete(String id) {
         productDao.delete(id);
    }

    @Override
    public Product findById(String id) {
        return productDao.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public List<Product> findByOwnerId(int ownerId) {
        return productDao.findByOwnerId(ownerId);
    }

}
