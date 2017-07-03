package ltd.cracks.service.front.account;

import java.sql.Timestamp;

/**
 * Created by macos on 2017/6/23.
 */
public class Account {

    private String id;
    private String username;
    private String password;
    private Timestamp insertTime;
    private String phoneNumber;

    public Account(){
        super();
    }

    public Account(String id, String username, String password, Timestamp insertTime, String phoneNumber) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.insertTime = insertTime;
        this.phoneNumber = phoneNumber;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Timestamp insertTime) {
        this.insertTime = insertTime;
    }

}
