package ltd.cracks.service.mongo;

import com.mongodb.DBCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

/**
 * Created by macos on 2017/6/5.
 */

@Service
public class mongoServiceImpl implements mongoService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public ArrayList<Document> findDocuments(String colName) {
        System.out.print(mongoTemplate);

        DBCollection dbCollection = mongoTemplate.createCollection("test");
        return null;
    }
}
