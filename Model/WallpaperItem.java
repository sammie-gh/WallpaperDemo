package com.gh.sammie.livewallpaper.Model;

/**
 * Created by Sammie on 2/17/2018.
 */

public class WallpaperItem {
    public String imageLink;
    public String  categoryId;

    public WallpaperItem() {
        this.imageLink = imageLink;
        this.categoryId = categoryId;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
