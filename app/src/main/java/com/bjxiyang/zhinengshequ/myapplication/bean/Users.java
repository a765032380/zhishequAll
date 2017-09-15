package com.bjxiyang.zhinengshequ.myapplication.bean;

/**
 * Created by Administrator on 2017/6/5 0005.
 */

public class Users {


    /**
     * code : 1000
     * msg : 登录成功
     * obj : {"realName":"","ownerStatus":1,"propertyStatus":1,"password":"b5833a8389a84ef39d12e7369a9e9bef","mobilePhone":"17610670228","nickName":"用户285416228","headPhotoUrl":"http://47.92.106.249:8088//img//1503899327766.jpg","integral":0,"loginKey":"f576cb05efc447e8aa18ac4805bd5fa6","c_memberId":271,"sex":1}
     */

    private String code;
    private String msg;
    private Obj obj;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Obj getObj() {
        return obj;
    }

    public void setObj(Obj obj) {
        this.obj = obj;
    }

    public static class Obj {
        /**
         * realName :
         * ownerStatus : 1
         * propertyStatus : 1
         * password : b5833a8389a84ef39d12e7369a9e9bef
         * mobilePhone : 17610670228
         * nickName : 用户285416228
         * headPhotoUrl : http://47.92.106.249:8088//img//1503899327766.jpg
         * integral : 0
         * loginKey : f576cb05efc447e8aa18ac4805bd5fa6
         * c_memberId : 271
         * sex : 1
         */

        private String realName;
        private int ownerStatus;
        private int propertyStatus;
        private String password;
        private String mobilePhone;
        private String nickName;
        private String headPhotoUrl;
        private int integral;
        private String loginKey;
        private int c_memberId;
        private int sex;

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public int getOwnerStatus() {
            return ownerStatus;
        }

        public void setOwnerStatus(int ownerStatus) {
            this.ownerStatus = ownerStatus;
        }

        public int getPropertyStatus() {
            return propertyStatus;
        }

        public void setPropertyStatus(int propertyStatus) {
            this.propertyStatus = propertyStatus;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getMobilePhone() {
            return mobilePhone;
        }

        public void setMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getHeadPhotoUrl() {
            return headPhotoUrl;
        }

        public void setHeadPhotoUrl(String headPhotoUrl) {
            this.headPhotoUrl = headPhotoUrl;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public String getLoginKey() {
            return loginKey;
        }

        public void setLoginKey(String loginKey) {
            this.loginKey = loginKey;
        }

        public int getC_memberId() {
            return c_memberId;
        }

        public void setC_memberId(int c_memberId) {
            this.c_memberId = c_memberId;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }
    }
}
