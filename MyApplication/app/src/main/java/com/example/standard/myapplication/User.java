package com.example.standard.myapplication;

import org.parceler.Parcel;

@Parcel
public class User {
    String name;
    String age;
    String location;

    public User() {

    }

    public User(String name,String age, String location) {
        this.name = name;
        this.age = age;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
