package app.louiemok.uni.starchef.model;

/**
 * Created by Administrator on 2017/9/28 0028.
 */

public class Shop {

    private int id;
    private String shopid;
    private String name;
    private String type;
    private String address;
    private String contact;
    private String opentime;
    private String endtime;
    private String config;
    private int star;
    private String[] pic;
    private int status;
    private String verifytime;
    private String peopleincharge;
    private String chargecontact;
    private String password;

    public void setId(int id) {
        this.id = id;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public void setPic(String[] pic) {
        this.pic = pic;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setVerifytime(String verifytime) {
        this.verifytime = verifytime;
    }

    public void setPeopleincharge(String peopleincharge) {
        this.peopleincharge = peopleincharge;
    }

    public void setChargecontact(String chargecontact) {
        this.chargecontact = chargecontact;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getShopid() {
        return shopid;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getAddress() {
        return address;
    }

    public String getContact() {
        return contact;
    }

    public String getOpentime() {
        return opentime;
    }

    public String getEndtime() {
        return endtime;
    }

    public String getConfig() {
        return config;
    }

    public int getStar() {
        return star;
    }

    public String[] getPic() {
        return pic;
    }

    public int getStatus() {
        return status;
    }

    public String getVerifytime() {
        return verifytime;
    }

    public String getPeopleincharge() {
        return peopleincharge;
    }

    public String getChargecontact() {
        return chargecontact;
    }

    public String getPassword() {
        return password;
    }
}
