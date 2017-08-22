package com.bjxiyang.zhinengshequ.myapplication.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/22 0022.
 */

public class XiTongXiaoXi {


    /**
     * code : 1000
     * msg : 获取消息成功
     * obj : [{"addTime":"2017-08-21 17:50:08","contentId":"5","msgContent":"sdakfsakldjfkalsjlkasjdklfjakldsjfladsjklfjasdlkjfasldjflkadsjflasdjlfjsladkjflsakdjflkasdjlkfjsakdljfasjflsdajlk","msgType":5,"userId":155},{"addTime":"2017-08-20 16:20:12","contentId":"7","msgContent":"asfsadfasdfsadfasddsfsdafass","msgType":4,"userId":155}]
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
         * addTime : 2017-08-21 17:50:08
         * contentId : 5
         * msgContent : sdakfsakldjfkalsjlkasjdklfjakldsjfladsjklfjasdlkjfasldjflkadsjflasdjlfjsladkjflsakdjflkasdjlkfjsakdljfasjflsdajlk
         * msgType : 5
         * userId : 155
         */

        private String addTime;
        private String contentId;
        private String msgContent;
        private int msgType;
        private int userId;

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getContentId() {
            return contentId;
        }

        public void setContentId(String contentId) {
            this.contentId = contentId;
        }

        public String getMsgContent() {
            return msgContent;
        }

        public void setMsgContent(String msgContent) {
            this.msgContent = msgContent;
        }

        public int getMsgType() {
            return msgType;
        }

        public void setMsgType(int msgType) {
            this.msgType = msgType;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
