package cn.chen.entity;

public class Orditem {
    private Integer itemid;         //  订单明细编号
    private Integer ordid;          //订单编号关联
    private Integer bookid;         //书本编号
    private Integer num;            //购买的数量
    private Double price;           //购买的价格

    public Integer getItemid() {
        return itemid;
    }

    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    public Integer getOrdid() {
        return ordid;
    }

    public void setOrdid(Integer ordid) {
        this.ordid = ordid;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
