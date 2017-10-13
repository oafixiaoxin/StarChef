package app.louiemok.uni.starchef.model;

/**
 * Created by Administrator on 2017/8/30 0030.
 */

public class User {
    private String uid;
    private String account;
    private String password;
    private String nickname;
    private int age;
    private int sex;
    private String avatar;
    private String qrcode;
    private int addressid;
    private String createtime;
    private String lastlogintime;
    private int isactive;
    private int point;
    private int star;
    private String phone;

    public String getUid() {
        return uid;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public int getAge() {
        return age;
    }

    public int getSex() {
        return sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getQrcode() {
        return qrcode;
    }

    public int getAddressid() {
        return addressid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public String getLastlogintime() {
        return lastlogintime;
    }

    public int getIsactive() {
        return isactive;
    }

    public int getPoint() {
        return point;
    }

    public int getStar() {
        return star;
    }

    public String getPhone() {
        return phone;
    }

    public User (Builder builder ) {
        this.uid = builder.uid;
        this.account = builder.account;
        this.password = builder.password;
        this.nickname = builder.nickname;
        this.age = builder.age;
        this.sex = builder.sex;
        this.avatar = builder.avatar;
        this.qrcode = builder.qrcode;
        this.addressid = builder.addressid;
        this.createtime = builder.createtime;
        this.lastlogintime = builder.lastlogintime;
        this.isactive = builder.isactive;
        this.point = builder.point;
        this.star = builder.star;
        this.phone = builder.phone;
    }

    public static class Builder {
        private String uid;
        private String account;
        private String password;
        private String nickname;
        private int age;
        private int sex;
        private String avatar;
        private String qrcode;
        private int addressid;
        private String createtime;
        private String lastlogintime;
        private int isactive;
        private int point;
        private int star;
        private String phone;

        public Builder uid ( String uid ) {
            this.uid = uid;
            return this;
        }

        public Builder account ( String account ) {
            this.account = account;
            return this;
        }

        public Builder password ( String password ) {
            this.password = password;
            return this;
        }

        public Builder nickname ( String nickname ) {
            this.nickname = nickname;
            return this;
        }

        public Builder age ( int age ) {
            this.age = age;
            return this;
        }

        public Builder sex ( int sex ) {
            this.sex = sex;
            return this;
        }

        public Builder avatar ( String avatar ) {
            this.avatar = avatar;
            return this;
        }

        public Builder qrcode ( String qrcode ) {
            this.qrcode = qrcode;
            return this;
        }

        public Builder addressid ( int addressid ) {
            this.addressid = addressid;
            return this;
        }

        public Builder createtime ( String createtime ) {
            this.createtime = createtime;
            return this;
        }

        public Builder lastlogintime ( String lastlogintime ) {
            this.lastlogintime = lastlogintime;
            return this;
        }

        public Builder isactive ( int isactive ) {
            this.isactive = isactive;
            return this;
        }

        public Builder point ( int point ) {
            this.point = point;
            return this;
        }

        public Builder star ( int star ) {
            this.star = star;
            return this;
        }

        public Builder phone ( String phone ) {
            this.phone = phone;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }
}
