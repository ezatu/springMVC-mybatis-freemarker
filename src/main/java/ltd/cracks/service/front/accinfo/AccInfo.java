package ltd.cracks.service.front.accinfo;

import java.sql.Timestamp;

/**
 * Created by macos on 2017/6/23.
 */
public class AccInfo {

    private String id;
    private int sex;
    private String brithday;
    private String icon;
    private String sign;
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getBrithday() {
        return brithday;
    }

    public void setBrithday(String brithday) {
        this.brithday = brithday;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AccInfo(){
        super();
    }

    public AccInfo(String id, int sex, String brithday, String icon, String sign, String email) {
        super();
        this.id = id;
        this.sex = sex;
        this.brithday = brithday;
        this.icon = icon;
        this.sign = sign;
        this.email = email;
    }



}
