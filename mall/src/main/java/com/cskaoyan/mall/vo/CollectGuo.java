package com.cskaoyan.mall.vo;

/*国旭*/
public class CollectGuo {
    private Integer id;

    private Integer userId;

    private Integer valueId;

    private Byte type;

    private String addTime;

    private String updateTime;

    private Boolean deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getValueId() {
        return valueId;
    }

    public void setValueId(Integer valueId) {
        this.valueId = valueId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "CollectGuo{" +
                "id=" + id +
                ", userId=" + userId +
                ", valueId=" + valueId +
                ", type=" + type +
                ", addTime='" + addTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
