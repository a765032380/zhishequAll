package com.bjxiyang.zhinengshequ.myapplication.bean;

/**
 * Created by Lenovo on 2017/8/12.
 */

public class AddFangWu {

    /**
     * code : 1000
     * msg : 添加授权房屋成功
     * Obj : {"bondUrl":"http://www.bjxiyang.com","validityDate":"","type":1,"bondName":"测试","bondLimit":"13%优惠"}
     */

    private String code;
    private String msg;
    private ObjBean Obj;

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

    public ObjBean getObj() {
        return Obj;
    }

    public void setObj(ObjBean Obj) {
        this.Obj = Obj;
    }

    public static class ObjBean {
        /**
         * bondUrl : http://www.bjxiyang.com
         * validityDate :
         * type : 1
         * bondName : 测试
         * bondLimit : 13%优惠
         */

        private String bondUrl;
        private String validityDate;
        private int type;
        private String bondName;
        private String bondLimit;

        public String getBondUrl() {
            return bondUrl;
        }

        public void setBondUrl(String bondUrl) {
            this.bondUrl = bondUrl;
        }

        public String getValidityDate() {
            return validityDate;
        }

        public void setValidityDate(String validityDate) {
            this.validityDate = validityDate;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getBondName() {
            return bondName;
        }

        public void setBondName(String bondName) {
            this.bondName = bondName;
        }

        public String getBondLimit() {
            return bondLimit;
        }

        public void setBondLimit(String bondLimit) {
            this.bondLimit = bondLimit;
        }
    }
}
