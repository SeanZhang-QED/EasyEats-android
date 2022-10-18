package com.easy.easyeats.ui.search;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.easy.easyeats.databinding.SearchPinsItemBinding;
import com.easy.easyeats.model.Pin;
import com.easy.easyeats.R;
import com.easy.easyeats.ui.like.LikedPinsAdapter;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;

public class SearchPinsAdapter extends RecyclerView.Adapter<SearchPinsAdapter.SearchPinsViewHolder> {
    // 1. Supporting data:
    //  - pass the list of data to the view, and create a setter function
    private List<Pin> pins = new ArrayList<>();

    public void setPins(List<Pin> pinsList) {
        pins.clear();
        pins.addAll(pinsList);
        notifyDataSetChanged(); // everytime the list is set, let the adapter refresh and re-render the data
    }

    // 2. SearchNewsViewHolder:
    // TODO: why do we need a view holder?
    // It is for holding the view references
    // - see the onBindViewHolder below
    //   Need to find the view, first. <-  holder.itemView.findViewBtId(R.id.search_news_view)
    //   DFS to find the view. -> expansive for sliding and binding
    // -> Only find once when build the viewHolder, not DFS find the view by id every time
    //   when user sliding the window and reuse the itemView
    public static class SearchPinsViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImageView;
        ImageView authorAvatar;
        TextView authorContent;
        TextView likesCount;

        public SearchPinsViewHolder(@NonNull View itemView) {
            super(itemView);
            SearchPinsItemBinding binding = SearchPinsItemBinding.bind(itemView);
            itemImageView = binding.searchItemImage;
            authorAvatar = binding.searchAuthorAvatar;
            authorContent = binding.searchAuthorContent;
            likesCount = binding.searchItemLikesNumber;
        }
    }


    // 3. Adapter overrides:
    @NonNull
    @Override
    // providing the generated item views
    public SearchPinsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_pins_item, parent, false);
        return new SearchPinsViewHolder(view);
    }

    @Override
    // bind data with view
    public void onBindViewHolder(@NonNull SearchPinsViewHolder holder, int position) {
        Pin pin = pins.get(position);
        Picasso.get().load(pin.urls.getRegular()).into(holder.itemImageView);
        Picasso.get().load(pin.user.profile_image.medium).into(holder.authorAvatar);
        holder.authorContent.setText(pin.user.name);
        holder.likesCount.setText(pin.likes);
        // Click to open detail view
        holder.itemImageView.setOnClickListener( v -> itemCallback.onOpenDetails(pin));
    }

    @Override
    // providing the current data collection size
    public int getItemCount() {
        return pins.size();
    }

    interface ItemCallback {
        void onOpenDetails(Pin pin);
    }

    private ItemCallback itemCallback;

    public void setItemCallback(ItemCallback itemCallback) {
        this.itemCallback = itemCallback;
    }
}
