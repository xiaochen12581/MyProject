package cn.chen.entity;

public class HotLar {
    private String id;
    private String memo;
    private String picture;
    private Integer hot;
    private Integer type;
    private Largefile largefile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Largefile getLargefile() {
        return largefile;
    }

    public void setLargefile(Largefile largefile) {
        this.largefile = largefile;
    }
}
