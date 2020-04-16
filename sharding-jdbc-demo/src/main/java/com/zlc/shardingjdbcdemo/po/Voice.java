package com.zlc.shardingjdbcdemo.po;

import java.util.Date;

public class Voice {
    private Long id;

    private Date createTime;

    private Integer centerNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCenterNum() {
        return centerNum;
    }

    public void setCenterNum(Integer centerNum) {
        this.centerNum = centerNum;
    }
}