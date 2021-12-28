package cn.chen.entity;

import java.sql.Date;

public class ItemBook {
    private int itemid;  //订单明细号  
    private int ordid;   //订单 id  
    private int bookid;  //书号  
    private int num; //数量  
    private double price; //价格  
    private String sn;   //序号  
    private String name;  //书名  
    private String author;  //作者  
    private String publisher;  //出版社  
    private Date tpublish;  //出版日期  
    private String picture; //图片 uuid

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public int getOrdid() {
        return ordid;
    }

    public void setOrdid(int ordid) {
        this.ordid = ordid;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getTpublish() {
        return tpublish;
    }

    public void setTpublish(Date tpublish) {
        this.tpublish = tpublish;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
