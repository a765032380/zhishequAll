package com.bjxiyang.zhinengshequ.myapplication.bean.bianlidian;


import java.util.List;

/**
 * Created by Administrator on 2017/7/6 0006.
 */

public class YouHuiQuan {


    /**
     * code : 0
     * time : 1502452157839
     * msg :
     * result : [{"endDate":"","bondUrl":"http://www.bjxiyang.com","expireType":0,"discount":85,"couponNo":"200201707094","minConsume":1000,"receiveTime":"2017-08-10 10:41:19","sellerId":0,"name":"十一活动","discountType":0,"id":183,"startDate":"","status":1},{"endDate":"","bondUrl":"http://www.bjxiyang.com","expireType":0,"discount":85,"couponNo":"200201707094","minConsume":1000,"receiveTime":"2017-08-08 11:44:10","sellerId":0,"name":"十一活动","discountType":0,"id":165,"startDate":"","status":1},{"endDate":"","bondUrl":"http://www.bjxiyang.com","expireType":0,"discount":85,"couponNo":"200201707094","minConsume":1000,"receiveTime":"2017-08-07 17:09:08","sellerId":0,"name":"十一活动","discountType":0,"id":162,"startDate":"","status":1},{"endDate":"","bondUrl":"http://www.bjxiyang.com","expireType":0,"discount":85,"couponNo":"200201707094","minConsume":1000,"receiveTime":"2017-08-03 15:06:49","sellerId":0,"name":"十一活动","discountType":0,"id":149,"startDate":"","status":1},{"endDate":"","bondUrl":"http://www.bjxiyang.com","expireType":0,"discount":85,"couponNo":"200201707094","minConsume":1000,"receiveTime":"2017-08-02 10:25:50","sellerId":0,"name":"十一活动","discountType":0,"id":138,"startDate":"","status":1},{"endDate":"","bondUrl":"http://www.bjxiyang.com","expireType":0,"discount":85,"couponNo":"200201707094","minConsume":1000,"receiveTime":"2017-07-29 21:26:45","sellerId":0,"name":"十一活动","discountType":0,"id":133,"startDate":"","status":0},{"endDate":"","bondUrl":"http://www.bjxiyang.com","expireType":0,"discount":85,"couponNo":"200201707094","minConsume":1000,"receiveTime":"2017-07-28 10:16:54","sellerId":0,"name":"十一活动","discountType":0,"id":124,"startDate":"","status":1},{"endDate":"","bondUrl":"http://www.bjxiyang.com","expireType":0,"discount":85,"couponNo":"200201707094","minConsume":1000,"receiveTime":"2017-07-27 13:35:14","sellerId":0,"name":"十一活动","discountType":0,"id":118,"startDate":"","status":1},{"endDate":"","bondUrl":"http://www.bjxiyang.com","expireType":0,"discount":85,"couponNo":"200201707094","minConsume":1000,"receiveTime":"2017-07-20 16:05:48","sellerId":0,"name":"十一活动","discountType":0,"id":88,"startDate":"","status":1},{"endDate":"2017-07-25 00:00:00","bondUrl":"http://www.bjxiyang.com","expireType":2,"discount":1,"couponNo":"200201707186","minConsume":10000,"receiveTime":"2017-07-20 16:05:33","sellerId":0,"name":"测试优惠","discountType":0,"id":87,"startDate":"2017-07-20 00:00:00","status":0},{"endDate":"2017-07-22 00:00:00","bondUrl":"http://www.bjxiyang.com","expireType":1,"discount":1,"couponNo":"200201707198","minConsume":5000,"receiveTime":"2017-07-19 15:48:17","sellerId":0,"name":"中秋","discountType":0,"id":77,"startDate":"2017-07-19 00:00:00","status":0},{"endDate":"2017-07-22 00:00:00","bondUrl":"http://www.bjxiyang.com","expireType":1,"discount":1,"couponNo":"200201707198","minConsume":5000,"receiveTime":"2017-07-19 15:47:48","sellerId":0,"name":"中秋","discountType":0,"id":76,"startDate":"2017-07-19 00:00:00","status":0},{"endDate":"2017-07-22 00:00:00","bondUrl":"http://www.bjxiyang.com","expireType":1,"discount":1,"couponNo":"200201707198","minConsume":5000,"receiveTime":"2017-07-19 15:43:07","sellerId":0,"name":"中秋","discountType":0,"id":74,"startDate":"2017-07-19 00:00:00","status":0},{"endDate":"2017-07-22 00:00:00","bondUrl":"http://www.bjxiyang.com","expireType":1,"discount":1,"couponNo":"200201707198","minConsume":5000,"receiveTime":"2017-07-19 15:33:15","sellerId":0,"name":"中秋","discountType":0,"id":73,"startDate":"2017-07-19 00:00:00","status":0}]
     */

    private int code;
    private long time;
    private String msg;
    private List<ResultBean> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * endDate :
         * bondUrl : http://www.bjxiyang.com
         * expireType : 0
         * discount : 85
         * couponNo : 200201707094
         * minConsume : 1000
         * receiveTime : 2017-08-10 10:41:19
         * sellerId : 0
         * name : 十一活动
         * discountType : 0
         * id : 183
         * startDate :
         * status : 1
         */

        private String endDate;
        private String bondUrl;
        private int expireType;
        private int discount;
        private String couponNo;
        private int minConsume;
        private String receiveTime;
        private int sellerId;
        private String name;
        private int discountType;
        private int id;
        private String startDate;
        private int status;

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getBondUrl() {
            return bondUrl;
        }

        public void setBondUrl(String bondUrl) {
            this.bondUrl = bondUrl;
        }

        public int getExpireType() {
            return expireType;
        }

        public void setExpireType(int expireType) {
            this.expireType = expireType;
        }

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }

        public String getCouponNo() {
            return couponNo;
        }

        public void setCouponNo(String couponNo) {
            this.couponNo = couponNo;
        }

        public int getMinConsume() {
            return minConsume;
        }

        public void setMinConsume(int minConsume) {
            this.minConsume = minConsume;
        }

        public String getReceiveTime() {
            return receiveTime;
        }

        public void setReceiveTime(String receiveTime) {
            this.receiveTime = receiveTime;
        }

        public int getSellerId() {
            return sellerId;
        }

        public void setSellerId(int sellerId) {
            this.sellerId = sellerId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getDiscountType() {
            return discountType;
        }

        public void setDiscountType(int discountType) {
            this.discountType = discountType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
