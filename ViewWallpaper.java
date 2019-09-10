package com.gh.sammie.livewallpaper;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.gh.sammie.livewallpaper.Common.Common;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;

public class ViewWallpaper extends AppCompatActivity {

    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton floatingActionButton;
     ImageView imageView;
     CoordinatorLayout coordinatorLayout;

     private Target target = new Target() {
         @Override
         public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

             WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
             try{
                 wallpaperManager.setBitmap(bitmap);
                 Snackbar.make(collapsingToolbarLayout,"Wallpaper was set",Snackbar.LENGTH_LONG);

             } catch (IOException e) {
                 e.printStackTrace();
             }
         }

         @Override
         public void onBitmapFailed(Drawable errorDrawable) {

         }

         @Override
         public void onPrepareLoad(Drawable placeHolderDrawable) {

         }
     };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_wallpaper);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowHomeEnabled(true);


        //init
        coordinatorLayout = findViewById(R.id.rootLayout);
        collapsingToolbarLayout = findViewById(R.id.collapsing);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpansedAppBar);

        collapsingToolbarLayout.setTitle(Common.CATEGORY_SELECTED);
        imageView= findViewById(R.id.imageThumb);

        Picasso.with(this)
                .load(Common.select_background.getImageLink())
                .into(imageView);

        floatingActionButton = findViewById(R.id.fabWallpaper);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Picasso.with(getBaseContext())
                        .load(Common.select_background.getImageLink())
                        .into(target);
            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish(); //colse activity when click bck button

        return super.onOptionsItemSelected(item);
    }
}
