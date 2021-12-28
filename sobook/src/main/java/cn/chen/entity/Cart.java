package cn.chen.entity;

import java.sql.Date;

public class Cart {
    private Integer id;
    private Integer uid;
    private Integer bookid;
    private Integer num;
    private Date tcreate;

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

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
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
}
