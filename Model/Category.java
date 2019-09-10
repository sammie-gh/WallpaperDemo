package com.gh.sammie.livewallpaper.Model;

/**
 * Created by Sammie on 2/8/2018.
 */

public class Category {

    public String name;
    public String imageLink;

    public Category() {

    }

    public Category(String name, String imageLink) {
        this.name = name;
        this.imageLink = imageLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}

