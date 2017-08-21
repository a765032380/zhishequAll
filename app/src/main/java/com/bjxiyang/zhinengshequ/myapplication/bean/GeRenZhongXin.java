package com.bjxiyang.zhinengshequ.myapplication.bean;

/**
 * Created by Administrator on 2017/8/21 0021.
 */

public class GeRenZhongXin {

    /**
     * code : 1000
     * msg : 获取个人信息成功
     * obj : {"qq":"未录入","address":"未录入","coupon":0,"messageInfo":0,"nickName":"","headPhotoUrl":"http://47.92.106.249:8088//img//1503130356535.jpg","c_memberId":148,"sex":"1","entranceCount":5,"idNumber":"","realName":"大傻","password":"bafb34c50b945f8a668e5a522aa839f2","mobilePhone":"18813045215","integral":0,"orderInfo":{"obligationCount":0,"refundment":0,"allcount":21,"mailing":0,"waitings":0},"weChat":"未录入","postings":4,"email":"未录入"}
     */

    private int code;
    private String msg;
    private ObjBean obj;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ObjBean getObj() {
        return obj;
    }

    public void setObj(ObjBean obj) {
        this.obj = obj;
    }

    public static class ObjBean {
        /**
         * qq : 未录入
         * address : 未录入
         * coupon : 0
         * messageInfo : 0
         * nickName :
         * headPhotoUrl : http://47.92.106.249:8088//img//1503130356535.jpg
         * c_memberId : 148
         * sex : 1
         * entranceCount : 5
         * idNumber :
         * realName : 大傻
         * password : bafb34c50b945f8a668e5a522aa839f2
         * mobilePhone : 18813045215
         * integral : 0
         * orderInfo : {"obligationCount":0,"refundment":0,"allcount":21,"mailing":0,"waitings":0}
         * weChat : 未录入
         * postings : 4
         * email : 未录入
         */

        private String qq;
        private String address;
        private int coupon;
        private int messageInfo;
        private String nickName;
        private String headPhotoUrl;
        private int c_memberId;
        private String sex;
        private int entranceCount;
        private String idNumber;
        private String realName;
        private String password;
        private String mobilePhone;
        private int integral;
        private OrderInfoBean orderInfo;
        private String weChat;
        private int postings;
        private String email;

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getCoupon() {
            return coupon;
        }

        public void setCoupon(int coupon) {
            this.coupon = coupon;
        }

        public int getMessageInfo() {
            return messageInfo;
        }

        public void setMessageInfo(int messageInfo) {
            this.messageInfo = messageInfo;
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

        public int getC_memberId() {
            return c_memberId;
        }

        public void setC_memberId(int c_memberId) {
            this.c_memberId = c_memberId;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public int getEntranceCount() {
            return entranceCount;
        }

        public void setEntranceCount(int entranceCount) {
            this.entranceCount = entranceCount;
        }

        public String getIdNumber() {
            return idNumber;
        }

        public void setIdNumber(String idNumber) {
            this.idNumber = idNumber;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
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

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public OrderInfoBean getOrderInfo() {
            return orderInfo;
        }

        public void setOrderInfo(OrderInfoBean orderInfo) {
            this.orderInfo = orderInfo;
        }

        public String getWeChat() {
            return weChat;
        }

        public void setWeChat(String weChat) {
            this.weChat = weChat;
        }

        public int getPostings() {
            return postings;
        }

        public void setPostings(int postings) {
            this.postings = postings;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public static class OrderInfoBean {
            /**
             * obligationCount : 0
             * refundment : 0
             * allcount : 21
             * mailing : 0
             * waitings : 0
             */

            private int obligationCount;
            private int refundment;
            private int allcount;
            private int mailing;
            private int waitings;

            public int getObligationCount() {
                return obligationCount;
            }

            public void setObligationCount(int obligationCount) {
                this.obligationCount = obligationCount;
            }

            public int getRefundment() {
                return refundment;
            }

            public void setRefundment(int refundment) {
                this.refundment = refundment;
            }

            public int getAllcount() {
                return allcount;
            }

            public void setAllcount(int allcount) {
                this.allcount = allcount;
            }

            public int getMailing() {
                return mailing;
            }

            public void setMailing(int mailing) {
                this.mailing = mailing;
            }

            public int getWaitings() {
                return waitings;
            }

            public void setWaitings(int waitings) {
                this.waitings = waitings;
            }
        }
    }
}
