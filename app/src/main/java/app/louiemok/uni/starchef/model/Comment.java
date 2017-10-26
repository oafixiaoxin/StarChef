package app.louiemok.uni.starchef.model;

/**
 * Created by Administrator on 2017/10/17 0017.
 */

public class Comment {
    private int id;
    private String uid;
    private String type;
    private String time;
    private String content;
    private int parentid;
    private int scancount;
    private String pic;
    private double taste;
    private double enviroment;
    private double service;
    private String targetid;
    private double costaver;
    private int like;
    private String nickname;
    private String avatar;
    private int star;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public int getScancount() {
        return scancount;
    }

    public void setScancount(int scancount) {
        this.scancount = scancount;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public double getTaste() {
        return taste;
    }

    public void setTaste(double taste) {
        this.taste = taste;
    }

    public double getEnviroment() {
        return enviroment;
    }

    public void setEnviroment(double enviroment) {
        this.enviroment = enviroment;
    }

    public double getService() {
        return service;
    }

    public void setService(double service) {
        this.service = service;
    }

    public String getTargetid() {
        return targetid;
    }

    public void setTargetid(String targetid) {
        this.targetid = targetid;
    }

    public double getCostaver() {
        return costaver;
    }

    public void setCostaver(double costaver) {
        this.costaver = costaver;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
