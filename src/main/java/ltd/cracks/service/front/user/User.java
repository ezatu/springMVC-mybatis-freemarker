package ltd.cracks.service.front.user;

import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by macos on 2017/6/9.
 */
public class User {

    private int id;
    private String age;
    private String userName;
    private Timestamp insertTime;
    public User(){
        super();
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Timestamp getInsertTime() {
        return insertTime;
    }
    public void setInsertTime(Timestamp insertTime) {
        this.insertTime = insertTime;
    }
    public User(int id, String age, String userName, Timestamp insertTime) {
        super();
        this.id = id;
        this.age = age;
        this.userName = userName;
        this.insertTime = insertTime;
    }

}
