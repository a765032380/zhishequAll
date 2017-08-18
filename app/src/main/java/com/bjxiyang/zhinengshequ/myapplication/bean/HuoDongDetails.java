package com.bjxiyang.zhinengshequ.myapplication.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class HuoDongDetails {

    /**
     * code : 1000
     * msg : 获取活动成功
     * obj : {"addTime":"2017-08-18 15:21","joinCount":1,"joinList":[{"joinTime":"2017-08-18 16:08","partyId":7,"userName":"","userId":148,"userUrl":""}],"destination":"成都","replyList":[{"fromUserName":"","replyTime":"2017-08-18 16:36","toUserUrl":"","fromUserId":99,"replyId":5,"commentId":0,"fromUserUrl":"","toUserName":"","replyContent":"评论","partyId":7,"toUserId":0},{"fromUserName":"","replyTime":"2017-08-18 16:47","toUserUrl":"","fromUserId":99,"replyId":6,"parentItem":{"fromUserName":"","replyTime":"2017-08-18 16:36","toUserUrl":"","fromUserId":99,"replyId":5,"commentId":0,"fromUserUrl":"","toUserName":"","replyContent":"评论","partyId":7,"toUserId":0},"commentId":5,"fromUserUrl":"","toUserName":"","replyContent":"回复评论","partyId":7,"toUserId":99},{"fromUserName":"","replyTime":"2017-08-18 16:48","toUserUrl":"","fromUserId":99,"replyId":7,"parentItem":{"fromUserName":"","replyTime":"2017-08-18 16:36","toUserUrl":"","fromUserId":99,"replyId":5,"commentId":0,"fromUserUrl":"","toUserName":"","replyContent":"评论","partyId":7,"toUserId":0},"commentId":5,"fromUserUrl":"","toUserName":"","replyContent":"回复评论","partyId":7,"toUserId":99},{"fromUserName":"","replyTime":"2017-08-18 16:49","toUserUrl":"","fromUserId":148,"replyId":8,"commentId":0,"fromUserUrl":"","toUserName":"","replyContent":"测试","partyId":7,"toUserId":0},{"fromUserName":"","replyTime":"2017-08-18 16:50","toUserUrl":"","fromUserId":99,"replyId":9,"parentItem":{"fromUserName":"","replyTime":"2017-08-18 16:49","toUserUrl":"","fromUserId":148,"replyId":8,"commentId":0,"fromUserUrl":"","toUserName":"","replyContent":"测试","partyId":7,"toUserId":0},"commentId":8,"fromUserUrl":"","toUserName":"","replyContent":"回复","partyId":7,"toUserId":148}],"userName":"","isEnd":0,"partyBeginTime":"2017-08-19 03:20","partyEndTime":"2017-08-19 03:20","replyCount":5,"partyDescribe":"玩一会","collectionPlace":"北京","costType":0,"haveJoin":1,"endTime":"2017-08-19 03:21","partyId":7,"partyRequirement":"友好","userUrl":"","imgList":[{"imgUrl":"http://47.92.106.249:8088//img///1503040908177.jpg"},{"imgUrl":"http://47.92.106.249:8088//img///1503040908185.jpg"}]}
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
         * addTime : 2017-08-18 15:21
         * joinCount : 1
         * joinList : [{"joinTime":"2017-08-18 16:08","partyId":7,"userName":"","userId":148,"userUrl":""}]
         * destination : 成都
         * replyList : [{"fromUserName":"","replyTime":"2017-08-18 16:36","toUserUrl":"","fromUserId":99,"replyId":5,"commentId":0,"fromUserUrl":"","toUserName":"","replyContent":"评论","partyId":7,"toUserId":0},{"fromUserName":"","replyTime":"2017-08-18 16:47","toUserUrl":"","fromUserId":99,"replyId":6,"parentItem":{"fromUserName":"","replyTime":"2017-08-18 16:36","toUserUrl":"","fromUserId":99,"replyId":5,"commentId":0,"fromUserUrl":"","toUserName":"","replyContent":"评论","partyId":7,"toUserId":0},"commentId":5,"fromUserUrl":"","toUserName":"","replyContent":"回复评论","partyId":7,"toUserId":99},{"fromUserName":"","replyTime":"2017-08-18 16:48","toUserUrl":"","fromUserId":99,"replyId":7,"parentItem":{"fromUserName":"","replyTime":"2017-08-18 16:36","toUserUrl":"","fromUserId":99,"replyId":5,"commentId":0,"fromUserUrl":"","toUserName":"","replyContent":"评论","partyId":7,"toUserId":0},"commentId":5,"fromUserUrl":"","toUserName":"","replyContent":"回复评论","partyId":7,"toUserId":99},{"fromUserName":"","replyTime":"2017-08-18 16:49","toUserUrl":"","fromUserId":148,"replyId":8,"commentId":0,"fromUserUrl":"","toUserName":"","replyContent":"测试","partyId":7,"toUserId":0},{"fromUserName":"","replyTime":"2017-08-18 16:50","toUserUrl":"","fromUserId":99,"replyId":9,"parentItem":{"fromUserName":"","replyTime":"2017-08-18 16:49","toUserUrl":"","fromUserId":148,"replyId":8,"commentId":0,"fromUserUrl":"","toUserName":"","replyContent":"测试","partyId":7,"toUserId":0},"commentId":8,"fromUserUrl":"","toUserName":"","replyContent":"回复","partyId":7,"toUserId":148}]
         * userName :
         * isEnd : 0
         * partyBeginTime : 2017-08-19 03:20
         * partyEndTime : 2017-08-19 03:20
         * replyCount : 5
         * partyDescribe : 玩一会
         * collectionPlace : 北京
         * costType : 0
         * haveJoin : 1
         * endTime : 2017-08-19 03:21
         * partyId : 7
         * partyRequirement : 友好
         * userUrl :
         * imgList : [{"imgUrl":"http://47.92.106.249:8088//img///1503040908177.jpg"},{"imgUrl":"http://47.92.106.249:8088//img///1503040908185.jpg"}]
         */

        private String addTime;
        private int joinCount;
        private String destination;
        private String userName;
        private int isEnd;
        private String partyBeginTime;
        private String partyEndTime;
        private int replyCount;
        private String partyDescribe;
        private String collectionPlace;
        private int costType;
        private int haveJoin;
        private String endTime;
        private int partyId;
        private String partyRequirement;
        private String userUrl;
        private List<JoinListBean> joinList;
        private List<ReplyListBean> replyList;
        private List<ImgListBean> imgList;

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

        public int getIsEnd() {
            return isEnd;
        }

        public void setIsEnd(int isEnd) {
            this.isEnd = isEnd;
        }

        public String getPartyBeginTime() {
            return partyBeginTime;
        }

        public void setPartyBeginTime(String partyBeginTime) {
            this.partyBeginTime = partyBeginTime;
        }

        public String getPartyEndTime() {
            return partyEndTime;
        }

        public void setPartyEndTime(String partyEndTime) {
            this.partyEndTime = partyEndTime;
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

        public int getHaveJoin() {
            return haveJoin;
        }

        public void setHaveJoin(int haveJoin) {
            this.haveJoin = haveJoin;
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

        public List<ImgListBean> getImgList() {
            return imgList;
        }

        public void setImgList(List<ImgListBean> imgList) {
            this.imgList = imgList;
        }

        public static class JoinListBean {
            /**
             * joinTime : 2017-08-18 16:08
             * partyId : 7
             * userName :
             * userId : 148
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
             * fromUserName :
             * replyTime : 2017-08-18 16:36
             * toUserUrl :
             * fromUserId : 99
             * replyId : 5
             * commentId : 0
             * fromUserUrl :
             * toUserName :
             * replyContent : 评论
             * partyId : 7
             * toUserId : 0
             * parentItem : {"fromUserName":"","replyTime":"2017-08-18 16:36","toUserUrl":"","fromUserId":99,"replyId":5,"commentId":0,"fromUserUrl":"","toUserName":"","replyContent":"评论","partyId":7,"toUserId":0}
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
            private ParentItemBean parentItem;

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

            public ParentItemBean getParentItem() {
                return parentItem;
            }

            public void setParentItem(ParentItemBean parentItem) {
                this.parentItem = parentItem;
            }

            public static class ParentItemBean {
                /**
                 * fromUserName :
                 * replyTime : 2017-08-18 16:36
                 * toUserUrl :
                 * fromUserId : 99
                 * replyId : 5
                 * commentId : 0
                 * fromUserUrl :
                 * toUserName :
                 * replyContent : 评论
                 * partyId : 7
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

        public static class ImgListBean {
            /**
             * imgUrl : http://47.92.106.249:8088//img///1503040908177.jpg
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
