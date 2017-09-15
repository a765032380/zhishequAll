package com.bjxiyang.zhinengshequ.myapplication.bean;

import com.bjxiyang.zhinengshequ.myapplication.bean.bianlidian.ShangPinList;

/**
 * bean 类
 * Created by yetwish on 2015-05-11
 */

public class Bean extends ShangPinList.Result.Products {

    private int iconId;
    private String title;
    private String content;
    private String comments;
    private int count;
    private String image;
    private int pice;
    private int youhuiPice;
    private int spid;
    private int userId;
    private int sellerId;
    private String logo;
    private String name;
    private String des;
    private int productTypeId;
    private int price;
    private int ifDiscount;

    public int getSpid() {
        return spid;
    }

    public void setSpid(int spid) {
        this.spid = spid;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public int getStockNum() {
        return stockNum;
    }

    public void setStockNum(int stockNum) {
        this.stockNum = stockNum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    private int discountPrice;
    private int stockNum;
    private int status;
    private String sellerName;

    public Bean(int iconId, String title, String content, String comments, int count, String image, int pice, int youhuiPice, int spid, int userId, int sellerId, String logo, String name, String des, int productTypeId, int price, int ifDiscount, int discountPrice, int stockNum, int status, String sellerName) {
        this.iconId = iconId;
        this.title = title;
        this.content = content;
        this.comments = comments;
        this.count = count;
        this.image = image;
        this.pice = pice;
        this.youhuiPice = youhuiPice;
        this.spid = spid;
        this.userId = userId;
        this.sellerId = sellerId;
        this.logo = logo;
        this.name = name;
        this.des = des;
        this.productTypeId = productTypeId;
        this.price = price;
        this.ifDiscount = ifDiscount;
        this.discountPrice = discountPrice;
        this.stockNum = stockNum;
        this.status = status;
        this.sellerName = sellerName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPice() {
        return pice;
    }

    public void setPice(int pice) {
        this.pice = pice;
    }

    public int getYouhuiPice() {
        return youhuiPice;
    }

    public void setYouhuiPice(int youhuiPice) {
        this.youhuiPice = youhuiPice;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
