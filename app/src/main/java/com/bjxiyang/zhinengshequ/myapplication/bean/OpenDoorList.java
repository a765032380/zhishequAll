package com.bjxiyang.zhinengshequ.myapplication.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/17 0017.
 */

public class OpenDoorList {


    /**
     * code : 1000
     * msg : 获取开门列表成功
     * obj : [{"unitName":"","headPhotoUrl":"http://47.92.106.249:8088//img//1503305799636.jpg","resultCode":0,"communityName":"鹏景阁大厦","nperName":"一期","floorName":"","openTime":"2017-08-23 09:04:36","userName":""},{"unitName":"","headPhotoUrl":"http://47.92.106.249:8088//img//1503305799636.jpg","resultCode":0,"communityName":"鹏景阁大厦","nperName":"一期","floorName":"","openTime":"2017-08-22 21:35:33","userName":""},{"unitName":"","headPhotoUrl":"http://47.92.106.249:8088//img//1503305799636.jpg","resultCode":0,"communityName":"鹏景阁大厦","nperName":"一期","floorName":"","openTime":"2017-08-22 21:35:26","userName":""}]
     */

    private String code;
    private String msg;
    private List<Obj> obj;

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

    public List<Obj> getObj() {
        return obj;
    }

    public void setObj(List<Obj> obj) {
        this.obj = obj;
    }

    public static class Obj {
        /**
         * unitName :
         * headPhotoUrl : http://47.92.106.249:8088//img//1503305799636.jpg
         * resultCode : 0
         * communityName : 鹏景阁大厦
         * nperName : 一期
         * floorName :
         * openTime : 2017-08-23 09:04:36
         * userName :
         */

        private String unitName;
        private String headPhotoUrl;
        private int resultCode;
        private String communityName;
        private String nperName;
        private String floorName;
        private String openTime;
        private String userName;

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public String getHeadPhotoUrl() {
            return headPhotoUrl;
        }

        public void setHeadPhotoUrl(String headPhotoUrl) {
            this.headPhotoUrl = headPhotoUrl;
        }

        public int getResultCode() {
            return resultCode;
        }

        public void setResultCode(int resultCode) {
            this.resultCode = resultCode;
        }

        public String getCommunityName() {
            return communityName;
        }

        public void setCommunityName(String communityName) {
            this.communityName = communityName;
        }

        public String getNperName() {
            return nperName;
        }

        public void setNperName(String nperName) {
            this.nperName = nperName;
        }

        public String getFloorName() {
            return floorName;
        }

        public void setFloorName(String floorName) {
            this.floorName = floorName;
        }

        public String getOpenTime() {
            return openTime;
        }

        public void setOpenTime(String openTime) {
            this.openTime = openTime;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
