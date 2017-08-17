package com.bjxiyang.zhinengshequ.myapplication.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class HuoDongDetails {

    /**
     * code : 1000
     * msg : 获取活动成功
     * obj : {"addTime":"2017-08-15 15:15:36","joinCount":2,"joinList":[{"joinTime":"2017-08-21 12:26:08","partyId":1,"userName":"","userId":51,"userUrl":""},{"joinTime":"2017-08-16 10:58:24","partyId":1,"userName":"","userId":148,"userUrl":""}],"destination":"上海","replyList":[{"fromUserName":"哈哈哈","replyTime":"2017-08-16 08:20:26","toUserUrl":"","fromUserId":50,"replyId":1,"commentId":0,"fromUserUrl":"http://47.92.106.249:8088//img//1500976791791.jpg","toUserName":"","replyContent":"太棒了，可以出去透透气了","partyId":1,"toUserId":0}],"partyBeginTime":"2017-08-21 00:00:00","replyCount":1,"partyDescribe":"活动测试，第一次组织","collectionPlace":"鹏景阁大厦 606","costType":0,"endTime":"2017-08-20 00:00:00","partyId":1,"partyRequirement":"美女 帅哥 小青年"}
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
         * addTime : 2017-08-15 15:15:36
         * joinCount : 2
         * joinList : [{"joinTime":"2017-08-21 12:26:08","partyId":1,"userName":"","userId":51,"userUrl":""},{"joinTime":"2017-08-16 10:58:24","partyId":1,"userName":"","userId":148,"userUrl":""}]
         * destination : 上海
         * replyList : [{"fromUserName":"哈哈哈","replyTime":"2017-08-16 08:20:26","toUserUrl":"","fromUserId":50,"replyId":1,"commentId":0,"fromUserUrl":"http://47.92.106.249:8088//img//1500976791791.jpg","toUserName":"","replyContent":"太棒了，可以出去透透气了","partyId":1,"toUserId":0}]
         * partyBeginTime : 2017-08-21 00:00:00
         * replyCount : 1
         * partyDescribe : 活动测试，第一次组织
         * collectionPlace : 鹏景阁大厦 606
         * costType : 0
         * endTime : 2017-08-20 00:00:00
         * partyId : 1
         * partyRequirement : 美女 帅哥 小青年
         */
        private String userUrl;
        private String userName;
        private String addTime;
        private int joinCount;
        private String destination;
        private String partyBeginTime;
        private int replyCount;
        private String partyDescribe;
        private String collectionPlace;
        private int costType;
        private String endTime;
        private int partyId;
        private String partyRequirement;
        private List<JoinListBean> joinList;
        private List<ReplyListBean> replyList;
        private String partyEndTime;
        private List<FindHuoDongList.ObjBean.ImgListBean> imgList;

        private int haveJoin;//0是还未加入,1是已加入

        public int getHaveJoin() {
            return haveJoin;
        }

        public void setHaveJoin(int haveJoin) {
            this.haveJoin = haveJoin;
        }

        public String getUserUrl() {
            return userUrl;
        }

        public void setUserUrl(String userUrl) {
            this.userUrl = userUrl;
        }

        public List<FindHuoDongList.ObjBean.ImgListBean> getImgList() {
            return imgList;
        }

        public void setImgList(List<FindHuoDongList.ObjBean.ImgListBean> imgList) {
            this.imgList = imgList;
        }

        public String getPartyEndTime() {
            return partyEndTime;
        }

        public void setPartyEndTime(String partyEndTime) {
            this.partyEndTime = partyEndTime;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
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

        public String getCollectionPlace() {
            return collectionPlace;
        }

        public void setCollectionPlace(String collectionPlace) {
            this.collectionPlace = collectionPlace;
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

        public List<JoinListBean> getJoinList() {
            return joinList;
        }

        public void setJoinList(List<JoinListBean> joinList) {
            this.joinList = joinList;
        }

        public List<ReplyListBean> getReplyList() {
            return replyList;
        }

        public void setReplyList(List<ReplyListBean> replyList) {
            this.replyList = replyList;
        }

        public static class JoinListBean {
            /**
             * joinTime : 2017-08-21 12:26:08
             * partyId : 1
             * userName :
             * userId : 51
             * userUrl :
             */

            private String joinTime;
            private int partyId;
            private String userName;
            private int userId;
            private String userUrl;

            public String getJoinTime() {
                return joinTime;
            }

            public void setJoinTime(String joinTime) {
                this.joinTime = joinTime;
            }

            public int getPartyId() {
                return partyId;
            }

            public void setPartyId(int partyId) {
                this.partyId = partyId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getUserUrl() {
                return userUrl;
            }

            public void setUserUrl(String userUrl) {
                this.userUrl = userUrl;
            }
        }

        public static class ReplyListBean {
            /**
             * fromUserName : 哈哈哈
             * replyTime : 2017-08-16 08:20:26
             * toUserUrl :
             * fromUserId : 50
             * replyId : 1
             * commentId : 0
             * fromUserUrl : http://47.92.106.249:8088//img//1500976791791.jpg
             * toUserName :
             * replyContent : 太棒了，可以出去透透气了
             * partyId : 1
             * toUserId : 0
             */

            private String fromUserName;
            private String replyTime;
            private String toUserUrl;
            private int fromUserId;
            private int replyId;
            private int commentId;
            private String fromUserUrl;
            private String toUserName;
            private String replyContent;
            private int partyId;
            private int toUserId;

            public String getFromUserName() {
                return fromUserName;
            }

            public void setFromUserName(String fromUserName) {
                this.fromUserName = fromUserName;
            }

            public String getReplyTime() {
                return replyTime;
            }

            public void setReplyTime(String replyTime) {
                this.replyTime = replyTime;
            }

            public String getToUserUrl() {
                return toUserUrl;
            }

            public void setToUserUrl(String toUserUrl) {
                this.toUserUrl = toUserUrl;
            }

            public int getFromUserId() {
                return fromUserId;
            }

            public void setFromUserId(int fromUserId) {
                this.fromUserId = fromUserId;
            }

            public int getReplyId() {
                return replyId;
            }

            public void setReplyId(int replyId) {
                this.replyId = replyId;
            }

            public int getCommentId() {
                return commentId;
            }

            public void setCommentId(int commentId) {
                this.commentId = commentId;
            }

            public String getFromUserUrl() {
                return fromUserUrl;
            }

            public void setFromUserUrl(String fromUserUrl) {
                this.fromUserUrl = fromUserUrl;
            }

            public String getToUserName() {
                return toUserName;
            }

            public void setToUserName(String toUserName) {
                this.toUserName = toUserName;
            }

            public String getReplyContent() {
                return replyContent;
            }

            public void setReplyContent(String replyContent) {
                this.replyContent = replyContent;
            }

            public int getPartyId() {
                return partyId;
            }

            public void setPartyId(int partyId) {
                this.partyId = partyId;
            }

            public int getToUserId() {
                return toUserId;
            }

            public void setToUserId(int toUserId) {
                this.toUserId = toUserId;
            }
        }
    }
}
