package com.bjxiyang.zhinengshequ.myapplication.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/19 0019.
 */

public class HaoYouList {

    /**
     * code : 1000
     * msg : 获取好友列表成功
     * obj : [{"friendRemark":"","friendPhone":"13366164637","nickName":"","headPhotoUrl":"http://47.92.106.249:8088//img//1503048431432.jpg"}]
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
         * friendRemark :
         * friendPhone : 13366164637
         * nickName :
         * headPhotoUrl : http://47.92.106.249:8088//img//1503048431432.jpg
         */

        private String friendRemark;
        private String friendPhone;
        private String nickName;
        private String headPhotoUrl;

        public String getFriendRemark() {
            return friendRemark;
        }

        public void setFriendRemark(String friendRemark) {
            this.friendRemark = friendRemark;
        }

        public String getFriendPhone() {
            return friendPhone;
        }

        public void setFriendPhone(String friendPhone) {
            this.friendPhone = friendPhone;
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
    }
}
