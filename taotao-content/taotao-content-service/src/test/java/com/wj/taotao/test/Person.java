package com.wj.taotao.test;

import java.util.Date;

public class Person {

    private long id;
    private String name;
    private Date birthday;

    public Person(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Person(long id, String name, Date birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
