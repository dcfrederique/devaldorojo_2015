package com.frederique.devaldo.domain;

/**
 * Created by Fre on 16/06/2015.
 */
public class UserData {
    private String name;
    private String imageUrl;

    public UserData(String name, String imageUrl){
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
