package ltd.cracks.service.mongo;



import org.bson.Document;

import java.util.ArrayList;

/**
 * Created by macos on 2017/6/5.
 */
public interface mongoService {
    ArrayList<Document> findDocuments(String colName);
}
