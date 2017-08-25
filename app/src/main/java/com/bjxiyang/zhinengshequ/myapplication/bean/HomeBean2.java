package com.bjxiyang.zhinengshequ.myapplication.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/14 0014.
 */

public class HomeBean2{

    /**
     * code : 1000
     * msg : 获取商家信息成功
     * obj : [{"sellerId":29,"distance":1.2241159E7,"monthSell":0,"productObj":[{"des":"买不了吃亏，买不了上当","price":16600,"name":"口红套装","ifDiscount":0,"discountPrice":0,"logo":"http://47.92.106.249:8088/img/1501062711636.jpg"},{"des":"狗子不要钱，免费送","price":10,"name":"狗子","ifDiscount":0,"discountPrice":0,"logo":"http://47.92.106.249:8088/img/1501061454065.jpg"},{"des":"新品上市，配方新升级，添加天然酵素，低粘、易溶、无残留，洗去深层不可见污渍。  ","price":11600,"name":"奥妙深层洁净洗衣液3KG+3KG 源自天然酵素","ifDiscount":1,"discountPrice":6600,"logo":"http://47.92.106.249:8088/img/1501060792803.jpg"}],"sellerName":"徐越","totalProduct":29,"transitTime":30,"ifPromotion":0,"discount":0,"sellerLogo":"http://47.92.106.249:8088/img/"}]
     */

    private int code;
    private String msg;
    private List<HomeBean.ObjBean.ShopObjBean> obj;

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

    public List<HomeBean.ObjBean.ShopObjBean> getObj() {
        return obj;
    }

    public void setObj(List<HomeBean.ObjBean.ShopObjBean> obj) {
        this.obj = obj;
    }

    public static class ObjBean  extends HomeBean.ObjBean.ShopObjBean{
        /**
         * sellerId : 29
         * distance : 1.2241159E7
         * monthSell : 0
         * productObj : [{"des":"买不了吃亏，买不了上当","price":16600,"name":"口红套装","ifDiscount":0,"discountPrice":0,"logo":"http://47.92.106.249:8088/img/1501062711636.jpg"},{"des":"狗子不要钱，免费送","price":10,"name":"狗子","ifDiscount":0,"discountPrice":0,"logo":"http://47.92.106.249:8088/img/1501061454065.jpg"},{"des":"新品上市，配方新升级，添加天然酵素，低粘、易溶、无残留，洗去深层不可见污渍。  ","price":11600,"name":"奥妙深层洁净洗衣液3KG+3KG 源自天然酵素","ifDiscount":1,"discountPrice":6600,"logo":"http://47.92.106.249:8088/img/1501060792803.jpg"}]
         * sellerName : 徐越
         * totalProduct : 29
         * transitTime : 30
         * ifPromotion : 0
         * discount : 0
         * sellerLogo : http://47.92.106.249:8088/img/
         */

        private int sellerId;
        private int distance;
        private int monthSell;
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

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public int getMonthSell() {
            return monthSell;
        }

        public void setMonthSell(int monthSell) {
            this.monthSell = monthSell;
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

//        public List<ProductObjBean> getProductObj() {
//            return productObj;
//        }
//
//        public void setProductObj(List<ProductObjBean> productObj) {
//            this.productObj = productObj;
//        }

        public static class ProductObjBean extends HomeBean.ObjBean.ShopObjBean.ProductObjBean {
            /**
             * des : 买不了吃亏，买不了上当
             * price : 16600
             * name : 口红套装
             * ifDiscount : 0
             * discountPrice : 0
             * logo : http://47.92.106.249:8088/img/1501062711636.jpg
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
}
