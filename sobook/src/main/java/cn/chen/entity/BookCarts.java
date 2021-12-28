package cn.chen.entity;

import java.sql.Date;

public class BookCarts {
    private String name;
    private String picture;
    private Integer bookid;
    private String addr;
    private String phone;
    private Integer id;
    private Integer num;
    private Date tcreate;
    private Double price;
    private Integer amount;

    @Override
    public String toString() {
        return "BookCarts{" +
                "name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", bookid=" + bookid +
                ", addr='" + addr + '\'' +
                ", phone='" + phone + '\'' +
                ", id=" + id +
                ", num=" + num +
                ", tcreate=" + tcreate +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getTcreate() {
        return tcreate;
    }

    public void setTcreate(Date tcreate) {
        this.tcreate = tcreate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
