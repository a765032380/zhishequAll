package com.bjxiyang.zhinengshequ.myapplication.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/4 0004.
 */

public class HomeBean {

    /**
     * code : 1000
     * msg : 获取首页信息成功
     * obj : {"bannerObj":[{"adType":1,"adUrl":"http://47.92.106.249:8087/upload/456.png","adInf":"http://www.bjxiyang.com","status":1},{"adType":1,"adUrl":"http://47.92.106.249:8087/upload/1111111111.jpg","adInf":"http://www.bjxiyang.com","status":1}],"noticeObj":[{"noticer":"物业","addTime":"2017-08-04 15:11:35","title":"迎接上级检查，打扫卫生","type":1,"noticeId":5,"content":"迎接上级检查，打扫卫生"},{"noticer":"你好","addTime":"2017-07-01 18:09:33","title":"小猫丢了","type":0,"noticeId":4,"content":"我家小可爱丢了"},{"noticer":"李尚生","addTime":"2017-07-01 17:36:38","title":"狗丢了","type":1,"noticeId":3,"content":"李尚升丢了，看见的请呼叫狗子\r\n"}],"financeObj":[{"number":0,"valueObj":[{"titleName":"首付比例","sequenceId":1,"titaleValue":"20.0%"},{"titleName":"首付利率","sequenceId":2,"titaleValue":"0.325"},{"titleName":"首付年限","sequenceId":3,"titaleValue":"30"},{"titleName":"二期比例","sequenceId":4,"titaleValue":"20.0%"},{"titleName":"二期利率","sequenceId":5,"titaleValue":"0.358"},{"titleName":"二期年限","sequenceId":6,"titaleValue":"30"}],"name":"二手房公积金贷款","financeUrl":"http://47.92.106.249:8087/www/ershoufang.html","type":1}],"newestObj":[{"loanName":"李先生 13925263692 贷款 200万"},{"loanName":"孙先生 13925263696 贷款 100万"}],"specialObj":[{"price":19800,"discount_price":234,"name":"白开水","logo":"1501739926237.jpg","if_discount":1},{"price":150,"discount_price":120,"name":"西红柿","logo":"1501640627233.jpg","if_discount":1},{"price":11600,"discount_price":1234,"name":"李-奥妙深层洁净洗衣液3KG+3KG 源自天然酵素","logo":"1501061556395.jpg","if_discount":1},{"price":13500,"discount_price":7900,"name":"舒适达（Sensodyne）多效护理牙膏组合套装120gx3+劲速护理20gx3（新老赠品随机发货）","logo":"1501061765071.jpg","if_discount":1},{"price":4500,"discount_price":10,"name":"李-新奇士美国夏橙12个160g以上/个 橙子 新鲜水果 橙子 新 新鲜水果","logo":"1501061780619.jpg","if_discount":1},{"price":20900,"discount_price":12900,"name":"李-一叶子樱花玫瑰补水礼盒","logo":"1501062002092.jpg","if_discount":1}]}
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
        private List<BannerObjBean> bannerObj;
        private List<NoticeObjBean> noticeObj;
        private List<FinanceObjBean> financeObj;
        private List<NewestObjBean> newestObj;
        private List<SpecialObjBean> specialObj;

        public List<BannerObjBean> getBannerObj() {
            return bannerObj;
        }

        public void setBannerObj(List<BannerObjBean> bannerObj) {
            this.bannerObj = bannerObj;
        }

        public List<NoticeObjBean> getNoticeObj() {
            return noticeObj;
        }

        public void setNoticeObj(List<NoticeObjBean> noticeObj) {
            this.noticeObj = noticeObj;
        }

        public List<FinanceObjBean> getFinanceObj() {
            return financeObj;
        }

        public void setFinanceObj(List<FinanceObjBean> financeObj) {
            this.financeObj = financeObj;
        }

        public List<NewestObjBean> getNewestObj() {
            return newestObj;
        }

        public void setNewestObj(List<NewestObjBean> newestObj) {
            this.newestObj = newestObj;
        }

        public List<SpecialObjBean> getSpecialObj() {
            return specialObj;
        }

        public void setSpecialObj(List<SpecialObjBean> specialObj) {
            this.specialObj = specialObj;
        }

        public static class BannerObjBean {
            /**
             * adType : 1
             * adUrl : http://47.92.106.249:8087/upload/456.png
             * adInf : http://www.bjxiyang.com
             * status : 1
             */

            private int adType;
            private String adUrl;
            private String adInf;
            private int status;

            public int getAdType() {
                return adType;
            }

            public void setAdType(int adType) {
                this.adType = adType;
            }

            public String getAdUrl() {
                return adUrl;
            }

            public void setAdUrl(String adUrl) {
                this.adUrl = adUrl;
            }

            public String getAdInf() {
                return adInf;
            }

            public void setAdInf(String adInf) {
                this.adInf = adInf;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }

        public static class NoticeObjBean {
            /**
             * noticer : 物业
             * addTime : 2017-08-04 15:11:35
             * title : 迎接上级检查，打扫卫生
             * type : 1
             * noticeId : 5
             * content : 迎接上级检查，打扫卫生
             */

            private String noticer;
            private String addTime;
            private String title;
            private int type;
            private int noticeId;
            private String content;

            public String getNoticer() {
                return noticer;
            }

            public void setNoticer(String noticer) {
                this.noticer = noticer;
            }

            public String getAddTime() {
                return addTime;
            }

            public void setAddTime(String addTime) {
                this.addTime = addTime;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getNoticeId() {
                return noticeId;
            }

            public void setNoticeId(int noticeId) {
                this.noticeId = noticeId;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }

        public static class FinanceObjBean {
            /**
             * number : 0
             * valueObj : [{"titleName":"首付比例","sequenceId":1,"titaleValue":"20.0%"},{"titleName":"首付利率","sequenceId":2,"titaleValue":"0.325"},{"titleName":"首付年限","sequenceId":3,"titaleValue":"30"},{"titleName":"二期比例","sequenceId":4,"titaleValue":"20.0%"},{"titleName":"二期利率","sequenceId":5,"titaleValue":"0.358"},{"titleName":"二期年限","sequenceId":6,"titaleValue":"30"}]
             * name : 二手房公积金贷款
             * financeUrl : http://47.92.106.249:8087/www/ershoufang.html
             * type : 1
             */

            private int number;
            private String name;
            private String financeUrl;
            private int type;
            private List<ValueObjBean> valueObj;

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getFinanceUrl() {
                return financeUrl;
            }

            public void setFinanceUrl(String financeUrl) {
                this.financeUrl = financeUrl;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public List<ValueObjBean> getValueObj() {
                return valueObj;
            }

            public void setValueObj(List<ValueObjBean> valueObj) {
                this.valueObj = valueObj;
            }

            public static class ValueObjBean {
                /**
                 * titleName : 首付比例
                 * sequenceId : 1
                 * titaleValue : 20.0%
                 */

                private String titleName;
                private int sequenceId;
                private String titaleValue;

                public String getTitleName() {
                    return titleName;
                }

                public void setTitleName(String titleName) {
                    this.titleName = titleName;
                }

                public int getSequenceId() {
                    return sequenceId;
                }

                public void setSequenceId(int sequenceId) {
                    this.sequenceId = sequenceId;
                }

                public String getTitaleValue() {
                    return titaleValue;
                }

                public void setTitaleValue(String titaleValue) {
                    this.titaleValue = titaleValue;
                }
            }
        }

        public static class NewestObjBean {
            /**
             * loanName : 李先生 13925263692 贷款 200万
             */

            private String loanName;

            public String getLoanName() {
                return loanName;
            }

            public void setLoanName(String loanName) {
                this.loanName = loanName;
            }
        }

        public static class SpecialObjBean {
            /**
             * price : 19800
             * discount_price : 234
             * name : 白开水
             * logo : 1501739926237.jpg
             * if_discount : 1
             */

            private int price;
            private int discount_price;
            private String name;
            private String logo;
            private int if_discount;

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getDiscount_price() {
                return discount_price;
            }

            public void setDiscount_price(int discount_price) {
                this.discount_price = discount_price;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public int getIf_discount() {
                return if_discount;
            }

            public void setIf_discount(int if_discount) {
                this.if_discount = if_discount;
            }
        }
    }
}
