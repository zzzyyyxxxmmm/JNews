package com.wjk32.jnews.entity;

import java.io.Serializable;

/**
 * Created by wjk32 on 12/29/2017.
 */

public class Source implements Serializable{
    private String name;
    private String id;

    public Source(String name, String id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Source{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}