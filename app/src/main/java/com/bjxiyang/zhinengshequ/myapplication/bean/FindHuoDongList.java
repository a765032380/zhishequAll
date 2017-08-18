package com.bjxiyang.zhinengshequ.myapplication.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class FindHuoDongList {

    /**
     * code : 1000
     * msg : 获取活动成功
     * obj : [{"addTime":"2017-08-15 15:15:36","joinCount":1,"destination":"上海","userName":"哈哈哈","partyBeginTime":"2017-08-21 00:00:00","replyCount":1,"partyDescribe":"活动测试，第一次组织","costType":0,"endTime":"2017-08-20 00:00:00","partyId":1,"partyRequirement":"美女 帅哥 小青年","userUrl":"http://47.92.106.249:8088//img//1500976791791.jpg","imgList":[{"imgUrl":"25555.PNG"},{"imgUrl":"26666.PNG"}]}]
     */

    private int code;
    private String msg;
    private List<ObjBean> obj;

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

    public List<ObjBean> getObj() {
        return obj;
    }

    public void setObj(List<ObjBean> obj) {
        this.obj = obj;
    }

    public static class ObjBean {
        /**
         * addTime : 2017-08-15 15:15:36
         * joinCount : 1
         * destination : 上海
         * userName : 哈哈哈
         * partyBeginTime : 2017-08-21 00:00:00
         * replyCount : 1
         * partyDescribe : 活动测试，第一次组织
         * costType : 0
         * endTime : 2017-08-20 00:00:00
         * partyId : 1
         * partyRequirement : 美女 帅哥 小青年
         * userUrl : http://47.92.106.249:8088//img//1500976791791.jpg
         * imgList : [{"imgUrl":"25555.PNG"},{"imgUrl":"26666.PNG"}]
         */

        private String addTime;
        private int joinCount;
        private String destination;
        private String userName;
        private String partyBeginTime;
        private int replyCount;
        private String partyDescribe;
        private int costType;
        private String endTime;
        private int partyId;
        private String partyRequirement;
        private String userUrl;
        private List<ImgListBean> imgList;
        private int haveJoin;//0是还未加入,1是已加入
        private int isEnd;//0未结束,1已结束

        public int getIsEnd() {
            return isEnd;
        }

        public void setIsEnd(int isEnd) {
            this.isEnd = isEnd;
        }

        public int getHaveJoin() {
            return haveJoin;
        }

        public void setHaveJoin(int haveJoin) {
            this.haveJoin = haveJoin;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public int getJoinCount() {
            return joinCount;
        }

        public void setJoinCount(int joinCount) {
            this.joinCount = joinCount;
        }

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPartyBeginTime() {
            return partyBeginTime;
        }

        public void setPartyBeginTime(String partyBeginTime) {
            this.partyBeginTime = partyBeginTime;
        }

        public int getReplyCount() {
            return replyCount;
        }

        public void setReplyCount(int replyCount) {
            this.replyCount = replyCount;
        }

        public String getPartyDescribe() {
            return partyDescribe;
        }

        public void setPartyDescribe(String partyDescribe) {
            this.partyDescribe = partyDescribe;
        }

        public int getCostType() {
            return costType;
        }

        public void setCostType(int costType) {
            this.costType = costType;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getPartyId() {
            return partyId;
        }

        public void setPartyId(int partyId) {
            this.partyId = partyId;
        }

        public String getPartyRequirement() {
            return partyRequirement;
        }

        public void setPartyRequirement(String partyRequirement) {
            this.partyRequirement = partyRequirement;
        }

        public String getUserUrl() {
            return userUrl;
        }

        public void setUserUrl(String userUrl) {
            this.userUrl = userUrl;
        }

        public List<ImgListBean> getImgList() {
            return imgList;
        }

        public void setImgList(List<ImgListBean> imgList) {
            this.imgList = imgList;
        }

        public static class ImgListBean {
            /**
             * imgUrl : 25555.PNG
             */

            private String imgUrl;

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }
        }
    }
}
