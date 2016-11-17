package cn.edu.xmu.nongge.ihavegoods.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by asus1 on 2016/10/27.
 */
public class Address {
    @JSONField(name = "id")
    private long id;
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "telephone")
    private String telephone;
    @JSONField(name = "address")
    private String address;
    @JSONField(name = "poi")
    private String poi;
    @JSONField(name = "employerid")
    private long employerid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPoi() {
        return poi;
    }

    public void setPoi(String poi) {
        this.poi = poi;
    }

    public long getEmployerid() {
        return employerid;
    }

    public void setEmployerid(long employerid) {
        this.employerid = employerid;
    }
}
