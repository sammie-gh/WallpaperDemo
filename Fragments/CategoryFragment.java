package com.gh.sammie.livewallpaper.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.gh.sammie.livewallpaper.Common.Common;
import com.gh.sammie.livewallpaper.Interface.ItemClickListener;
import com.gh.sammie.livewallpaper.ListWallpapers;
import com.gh.sammie.livewallpaper.Model.Category;
import com.gh.sammie.livewallpaper.R;
import com.gh.sammie.livewallpaper.ViewHolder.CategoryViewHolder;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;


public class CategoryFragment extends Fragment {

    RecyclerView recyclerView;
    //firebase
    FirebaseDatabase database;
    DatabaseReference categoryBackground;

    //fb UI Adapter
    FirebaseRecyclerOptions<Category> options;
    FirebaseRecyclerAdapter<Category,CategoryViewHolder> adapter;

    private static CategoryFragment INSTANCE =null;


    public CategoryFragment() {
       database = FirebaseDatabase.getInstance();
       categoryBackground = database.getReference(Common.STR_CATEGORY_BACKGROUND);

       options = new FirebaseRecyclerOptions.Builder<Category>()
               .setQuery(categoryBackground,Category.class)
               .build();

       adapter = new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(options) {
           @Override
           protected void onBindViewHolder(@NonNull final CategoryViewHolder holder, int position, @NonNull final Category model) {

               Picasso.with(getActivity())
                       .load(model.getImageLink())
                       .networkPolicy(NetworkPolicy.OFFLINE)
                       .into(holder.background_image, new Callback() {
                           @Override
                           public void onSuccess() {

                           }

                           @Override
                           public void onError() {
                               //try again
                               Picasso.with(getActivity())
                                       .load(model.getImageLink())
                                       .error(R.drawable.ic_terrain_black_24dp)
                                       .into(holder.background_image, new Callback() {
                                           @Override
                                           public void onSuccess() {

                                           }

                                           @Override
                                           public void onError() {

                                               Log.e("ERROR SAMM I" ,"wasn not able to fetch image");
                                           }
                                       });


                           }
                       });

               holder.category_name.setText(model.getName());
               holder.setItemClickListener(new ItemClickListener() {
                   @Override
                   public void OnClick(View view, int position) {

                       Common.CATEGORY_ID_SELECTED = adapter.getRef(position).getKey(); //get key of item
                       Common.CATEGORY_SELECTED = model.getName();
                       Intent intent  = new Intent(getActivity(), ListWallpapers.class);
                       startActivity(intent);

                   }
               });
           }

           @Override
           public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
               View itemView  = LayoutInflater.from(parent.getContext())
                       .inflate(R.layout.layout_category_item,parent,false);

               return new CategoryViewHolder(itemView);

           }
       };
    }

    public  static CategoryFragment getInstance()
    {
            if (INSTANCE == null)
                INSTANCE = new CategoryFragment();
            return INSTANCE;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_category, container, false);
         recyclerView = view.findViewById(R.id.recycler_category);
        recyclerView.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

        setCategory();
        
        return view;
        
    }

    private void setCategory() {


        adapter.startListening();

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();

        if (adapter!=null)
            adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();

        if (adapter != null)
            adapter.stopListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null)
            adapter.startListening();
    }


}
