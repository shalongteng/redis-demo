package com.xdclass.mobile.xdclassmobileredis.generator.model;

public class User {
    private Integer id;

    private String userName;

    private String image;

    public User(Integer id, String userName, String image) {
        this.id = id;
        this.userName = userName;
        this.image = image;
    }

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }
}