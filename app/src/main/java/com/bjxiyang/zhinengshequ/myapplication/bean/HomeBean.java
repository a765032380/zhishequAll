package com.bjxiyang.zhinengshequ.myapplication.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/8/4 0004.
 */

public class HomeBean {

    /**
     * code : 1000
     * msg : 获取首页信息成功
     * obj : {"bannerObj":[{"adType":1,"adUrl":"http://47.92.106.249:8087/upload/456.png","adInf":"http://www.bjxiyang.com","status":1},{"adType":1,"adUrl":"http://47.92.106.249:8087/upload/1111111111.jpg","adInf":"http://www.bjxiyang.com","status":1}],"noticeObj":[{"noticer":"物业","addTime":"2017-08-04 15:11:35","title":"迎接上级检查，打扫卫生","type":1,"noticeId":5,"content":"迎接上级检查，打扫卫生"}],"financeObj":[{"number":0,"gId":-1,"valueObj":[{"titleName":"首付比例","sequenceId":1,"titaleValue":"20.0%"},{"titleName":"首付利率","sequenceId":2,"titaleValue":"0.325"},{"titleName":"首付年限","sequenceId":3,"titaleValue":"30"},{"titleName":"二期比例","sequenceId":4,"titaleValue":"20.0%"},{"titleName":"二期利率","sequenceId":5,"titaleValue":"0.358"},{"titleName":"二期年限","sequenceId":6,"titaleValue":"30"}],"name":"二手房公积金贷款","financeUrl":"http://47.92.106.249:8087/www/ershoufang.html","type":1,"sId":"12"},{"number":1,"gId":-1,"valueObj":[{"titleName":"首付比例","sequenceId":1,"titaleValue":"30.0%"},{"titleName":"首付利率","sequenceId":2,"titaleValue":"0.369"},{"titleName":"首付年限","sequenceId":3,"titaleValue":"32"},{"titleName":"二期比例","sequenceId":4,"titaleValue":"20.0%"},{"titleName":"二期利率","sequenceId":5,"titaleValue":"0.369"},{"titleName":"二期年限","sequenceId":6,"titaleValue":"35"}],"name":"二手房公积金贷款","financeUrl":"http://47.92.106.249:8087/www/ershoufang.html","type":1,"sId":"13"}],"shopObj":[{"sellerId":32,"monthSell":1,"distance":450,"productObj":[{"des":"","price":39999900,"name":"Hhhh","ifDiscount":2,"discountPrice":888800,"logo":"http://47.92.106.249:8088/img/1501659499533.jpg"},{"des":"新鲜的西红柿","price":150,"name":"西红柿","ifDiscount":1,"discountPrice":120,"logo":"http://47.92.106.249:8088/img/1501640627233.jpg"}],"sellerName":"旗舰店","totalProduct":2,"transitTime":30,"ifPromotion":0,"discount":0,"sellerLogo":"http://47.92.106.249:8088/img/"}],"newestObj":[{"loanName":"李先生139****3692贷款200万"},{"loanName":"孙先生139****3696贷款100万"}]}
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
        private List<ShopObjBean> shopObj;
        private List<NewestObjBean> newestObj;

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

        public List<ShopObjBean> getShopObj() {
            return shopObj;
        }

        public void setShopObj(List<ShopObjBean> shopObj) {
            this.shopObj = shopObj;
        }

        public List<NewestObjBean> getNewestObj() {
            return newestObj;
        }

        public void setNewestObj(List<NewestObjBean> newestObj) {
            this.newestObj = newestObj;
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
             * gId : -1
             * valueObj : [{"titleName":"首付比例","sequenceId":1,"titaleValue":"20.0%"},{"titleName":"首付利率","sequenceId":2,"titaleValue":"0.325"},{"titleName":"首付年限","sequenceId":3,"titaleValue":"30"},{"titleName":"二期比例","sequenceId":4,"titaleValue":"20.0%"},{"titleName":"二期利率","sequenceId":5,"titaleValue":"0.358"},{"titleName":"二期年限","sequenceId":6,"titaleValue":"30"}]
             * name : 二手房公积金贷款
             * financeUrl : http://47.92.106.249:8087/www/ershoufang.html
             * type : 1
             * sId : 12
             */

            private int number;
            private int gId;
            private String name;
            private String financeUrl;
            private int type;
            private String sId;
            private List<ValueObjBean> valueObj;

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public int getGId() {
                return gId;
            }

            public void setGId(int gId) {
                this.gId = gId;
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

            public String getSId() {
                return sId;
            }

            public void setSId(String sId) {
                this.sId = sId;
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

        public static class ShopObjBean {
            /**
             * sellerId : 32
             * monthSell : 1
             * distance : 450
             * productObj : [{"des":"","price":39999900,"name":"Hhhh","ifDiscount":2,"discountPrice":888800,"logo":"http://47.92.106.249:8088/img/1501659499533.jpg"},{"des":"新鲜的西红柿","price":150,"name":"西红柿","ifDiscount":1,"discountPrice":120,"logo":"http://47.92.106.249:8088/img/1501640627233.jpg"}]
             * sellerName : 旗舰店
             * totalProduct : 2
             * transitTime : 30
             * ifPromotion : 0
             * discount : 0
             * sellerLogo : http://47.92.106.249:8088/img/
             */

            private int sellerId;
            private int monthSell;
            private int distance;
            private String sellerName;
            private int totalProduct;
            private int transitTime;
            private int ifPromotion;
            private int discount;
            private String sellerLogo;
            private List<ProductObjBean> productObj;

            public int getSellerId() {
                return sellerId;
            }

            public void setSellerId(int sellerId) {
                this.sellerId = sellerId;
            }

            public int getMonthSell() {
                return monthSell;
            }

            public void setMonthSell(int monthSell) {
                this.monthSell = monthSell;
            }

            public int getDistance() {
                return distance;
            }

            public void setDistance(int distance) {
                this.distance = distance;
            }

            public String getSellerName() {
                return sellerName;
            }

            public void setSellerName(String sellerName) {
                this.sellerName = sellerName;
            }

            public int getTotalProduct() {
                return totalProduct;
            }

            public void setTotalProduct(int totalProduct) {
                this.totalProduct = totalProduct;
            }

            public int getTransitTime() {
                return transitTime;
            }

            public void setTransitTime(int transitTime) {
                this.transitTime = transitTime;
            }

            public int getIfPromotion() {
                return ifPromotion;
            }

            public void setIfPromotion(int ifPromotion) {
                this.ifPromotion = ifPromotion;
            }

            public int getDiscount() {
                return discount;
            }

            public void setDiscount(int discount) {
                this.discount = discount;
            }

            public String getSellerLogo() {
                return sellerLogo;
            }

            public void setSellerLogo(String sellerLogo) {
                this.sellerLogo = sellerLogo;
            }

            public List<ProductObjBean> getProductObj() {
                return productObj;
            }

            public void setProductObj(List<ProductObjBean> productObj) {
                this.productObj = productObj;
            }

            public static class ProductObjBean implements Serializable {
                /**
                 * des :
                 * price : 39999900
                 * name : Hhhh
                 * ifDiscount : 2
                 * discountPrice : 888800
                 * logo : http://47.92.106.249:8088/img/1501659499533.jpg
                 */

                private String des;
                private int price;
                private String name;
                private int ifDiscount;
                private int discountPrice;
                private String logo;
                private int sellerId;
                private int id;
                private String sellerName;

                public int getSellerId() {
                    return sellerId;
                }

                public void setSellerId(int sellerId) {
                    this.sellerId = sellerId;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getSellerName() {
                    return sellerName;
                }

                public void setSellerName(String sellerName) {
                    this.sellerName = sellerName;
                }

                public String getDes() {
                    return des;
                }

                public void setDes(String des) {
                    this.des = des;
                }

                public int getPrice() {
                    return price;
                }

                public void setPrice(int price) {
                    this.price = price;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getIfDiscount() {
                    return ifDiscount;
                }

                public void setIfDiscount(int ifDiscount) {
                    this.ifDiscount = ifDiscount;
                }

                public int getDiscountPrice() {
                    return discountPrice;
                }

                public void setDiscountPrice(int discountPrice) {
                    this.discountPrice = discountPrice;
                }

                public String getLogo() {
                    return logo;
                }

                public void setLogo(String logo) {
                    this.logo = logo;
                }
            }
        }

        public static class NewestObjBean {
            /**
             * loanName : 李先生139****3692贷款200万
             */

            private String loanName;

            public String getLoanName() {
                return loanName;
            }

            public void setLoanName(String loanName) {
                this.loanName = loanName;
            }
        }
    }
}
