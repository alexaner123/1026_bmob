package com.moliying.a1026_bmob;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 16-10-26.
 */
public class Person extends BmobObject {
    private String name;
    private String address;

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
}
