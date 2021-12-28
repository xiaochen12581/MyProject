package cn.chen.entity;

import java.sql.Date;

public class BookCart {
    private Integer id;
    private Integer uid;
    private Integer num;
    private Date tcreate;
    private Book book;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
