package ltd.cracks.service.mongo;

import com.mongodb.*;
import ltd.cracks.util.coverLocalTime;
import org.bson.BSONObject;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.Basic;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by macos on 2017/6/5.
 */

@Service
public class mongoServiceImpl implements mongoService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public ArrayList<Document> findDocuments(String colName) {
        ArrayList<Document> result = new ArrayList<Document>();
        System.out.print(mongoTemplate);
        BasicDBObject object = BasicDBObject.parse(new Document().toJson());
        object.append("test",10);
        object.append("insertTime", new Timestamp(coverLocalTime.coverLocalTime(System.currentTimeMillis())));
        DBCollection collection = mongoTemplate.getCollection("test");
        collection.insert(object);
        DBCursor cursor = collection.find();
        Iterator<DBObject> iterator = cursor.iterator();
        while (iterator.hasNext()) {
            DBObject dbObject = iterator.next();
            result.add(Document.parse(dbObject.toString()));
        }
        return result;
    }
}
