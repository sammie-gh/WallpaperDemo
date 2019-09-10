package com.gh.sammie.livewallpaper.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gh.sammie.livewallpaper.Interface.ItemClickListener;
import com.gh.sammie.livewallpaper.R;

/**
 * Created by Sammie on 2/8/2018.
 */

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

    public TextView category_name;
    public ImageView background_image;



    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    ItemClickListener itemClickListener;

    public CategoryViewHolder(View itemView) {
        super(itemView);

        background_image = (ImageView)itemView.findViewById(R.id.image);
        category_name = itemView.findViewById(R.id.name);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.OnClick(view,getAdapterPosition());

    }
}
