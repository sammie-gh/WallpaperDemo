package com.gh.sammie.livewallpaper;

import android.app.Application;

/**
 * Created by Sammie on 2/19/2018.
 */

public class OfflineStorage extends Application {




    @Override
    public void onCreate() {
        super.onCreate();

//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
//
//        Picasso.Builder builder = new Picasso.Builder(this);
//        builder.downloader(new OkHttpDownloader(this, Integer.MAX_VALUE));
//        Picasso built = builder.build();
//        built.setIndicatorsEnabled(true);
//        Picasso.setSingletonInstance(built);
    }
}
