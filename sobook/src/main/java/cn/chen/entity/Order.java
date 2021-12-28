package cn.chen.entity;

import java.sql.Date;

public class Order {
    private Integer ordid;      // 订单编号 id
    private Integer uid;        // 用户 id
    private Double amount;      //付款金额
    private Integer ispay;      // 0 未付款， 1 已经付款
    private Date tpay;          // 付款时间
    private String addr;        //发货地址
    private String phone;       // 收货人电话
    private Date tcreate;       //创建订单时间
    private String shipno;      //快递单号

    public Integer getOrdid() {
        return ordid;
    }

    public void setOrdid(Integer ordid) {
        this.ordid = ordid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getIspay() {
        return ispay;
    }

    public void setIspay(Integer ispay) {
        this.ispay = ispay;
    }

    public Date getTpay() {
        return tpay;
    }

    public void setTpay(Date tpay) {
        this.tpay = tpay;
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

    public Date getTcreate() {
        return tcreate;
    }

    public void setTcreate(Date tcreate) {
        this.tcreate = tcreate;
    }

    public String getShipno() {
        return shipno;
    }

    public void setShipno(String shipno) {
        this.shipno = shipno;
    }

    @Override
    public String toString() {
        return "Order{" +
                "ordid=" + ordid +
                ", uid=" + uid +
                ", amount=" + amount +
                ", ispay=" + ispay +
                ", tpay=" + tpay +
                ", addr='" + addr + '\'' +
                ", phone='" + phone + '\'' +
                ", tcreate=" + tcreate +
                ", shipno='" + shipno + '\'' +
                '}';
    }
}
