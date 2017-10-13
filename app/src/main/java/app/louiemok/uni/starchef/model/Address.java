package app.louiemok.uni.starchef.model;

/**
 * Created by Administrator on 2017/10/10 0010.
 */

public class Address {
    private int id;
    private String uid;
    private String name;
    private String contact;
    private String address;
    private int sex;
    private int mailcode;
    private int isuesd;
    private int isselected;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex==0?"男":"女";
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getMailcode() {
        return mailcode;
    }

    public void setMailcode(int mailcode) {
        this.mailcode = mailcode;
    }

    public int getIsuesd() {
        return isuesd;
    }

    public void setIsuesd(int isuesd) {
        this.isuesd = isuesd;
    }

    public int getIsselected() {
        return isselected;
    }

    public void setIsselected(int isselected) {
        this.isselected = isselected;
    }
}
