    package com.gh.sammie.livewallpaper;

    import android.content.Intent;
    import android.os.Bundle;
    import android.support.annotation.NonNull;
    import android.support.v7.app.AppCompatActivity;
    import android.support.v7.widget.GridLayoutManager;
    import android.support.v7.widget.RecyclerView;
    import android.support.v7.widget.Toolbar;
    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.MenuItem;
    import android.view.View;
    import android.view.ViewGroup;

    import com.firebase.ui.database.FirebaseRecyclerAdapter;
    import com.firebase.ui.database.FirebaseRecyclerOptions;
    import com.gh.sammie.livewallpaper.Common.Common;
    import com.gh.sammie.livewallpaper.Interface.ItemClickListener;
    import com.gh.sammie.livewallpaper.Model.WallpaperItem;
    import com.gh.sammie.livewallpaper.ViewHolder.ListWallpaperViewHolder;
    import com.google.firebase.database.FirebaseDatabase;
    import com.google.firebase.database.Query;
    import com.squareup.picasso.Callback;
    import com.squareup.picasso.NetworkPolicy;
    import com.squareup.picasso.Picasso;

    public class ListWallpapers extends AppCompatActivity {
        Query query;
        FirebaseRecyclerOptions<WallpaperItem>options;
        FirebaseRecyclerAdapter<WallpaperItem,ListWallpaperViewHolder>adapter;

        RecyclerView recyclerView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_list_wallpapers);

            //toolbar
            Toolbar toolbar = findViewById(R.id.toolbar);
            toolbar.setTitle(Common.CATEGORY_SELECTED);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            recyclerView = findViewById(R.id.recycler_list_wallpaper);
            recyclerView.setHasFixedSize(true);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);

            recyclerView.setLayoutManager(gridLayoutManager);

            loadWallpaperList();

        }

        private void loadWallpaperList() {
            query = FirebaseDatabase.getInstance().getReference(Common.STR_WALLPAPER)
                    .orderByChild("categoryId").equalTo(Common.CATEGORY_ID_SELECTED);

            options = new FirebaseRecyclerOptions.Builder<WallpaperItem>()
                    .setQuery(query,WallpaperItem.class)
                    .build();


            adapter = new FirebaseRecyclerAdapter<WallpaperItem, ListWallpaperViewHolder>(options) {
                @Override
                protected void onBindViewHolder(@NonNull final ListWallpaperViewHolder holder, int position, @NonNull final WallpaperItem model) {

                    Picasso.with(getBaseContext())
                            .load(model.getImageLink())
                            .networkPolicy(NetworkPolicy.OFFLINE)
                            .into(holder.wallpaper, new Callback() {
                                @Override
                                public void onSuccess() {

                                }

                                @Override
                                public void onError() {
                                    Picasso.with(getBaseContext())
                                    .load(model.getImageLink())
                                            .error(R.drawable.ic_terrain_black_24dp)
                                            .into(holder.wallpaper, new Callback() {
                                                @Override
                                                public void onSuccess() {

                                                }

                                                @Override
                                                public void onError() {
                                                    Log.e("ERROR_EDMTDEV" , "Could not fetch image");


                                                }
                                            });

                                }
                            });

                    holder.setItemClickListener(new ItemClickListener() {
                        @Override
                        public void OnClick(View view, int position) {
                            Intent intent = new Intent(ListWallpapers.this,ViewWallpaper.class);
                            Common.select_background=model;
                            startActivity(intent);

                        }
                    });
                }

                @Override
                public ListWallpaperViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                    View itemView = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.layout_wallpaper_item,parent,false);


                    int height = parent.getMeasuredHeight()/2;
                    itemView.setMinimumHeight(height);

                    return new ListWallpaperViewHolder(itemView);
                }
            };

        adapter.startListening();
        recyclerView.setAdapter(adapter);
        }

        @Override
        protected void onStart() {
            super.onStart();
            if (adapter != null)
                adapter.startListening();
        }

        @Override
        protected void onPostResume() {
            super.onPostResume();
            if (adapter != null)
                adapter.startListening();

        }

        @Override
        protected void onStop() {

            if (adapter != null)
                adapter.stopListening();

            super.onStop();
        }


        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            if (item.getItemId() == android.R.id.home)
                finish(); //colse activity when click bck button

            return super.onOptionsItemSelected(item);
        }

    }
