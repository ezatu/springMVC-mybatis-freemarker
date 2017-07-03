package ltd.cracks.service.front.product;

import java.sql.Timestamp;

/**
 * Created by macos on 2017/6/12.
 */
public class Product {

    private int id;
    private String title;
    private int ownerId;
    private String message;
    private String other;
    private Timestamp insertTime;

    public Product() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getownerId() {
        return ownerId;
    }

    public void setownerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public Timestamp getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Timestamp insertTime) {
        this.insertTime = insertTime;
    }

}
