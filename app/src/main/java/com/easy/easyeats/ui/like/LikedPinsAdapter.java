package com.easy.easyeats.ui.like;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.easy.easyeats.R;
import com.easy.easyeats.databinding.LikedPinsItemBinding;
import com.easy.easyeats.model.Pin;
import com.easy.easyeats.model.UserConverter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class LikedPinsAdapter extends RecyclerView.Adapter<LikedPinsAdapter.LikedPinsViewHolder> {
    private List<Pin> pins = new ArrayList<>();

    public void setPins(List<Pin> pinList) {
        pins.clear();
        pins.addAll(pinList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LikedPinsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.liked_pins_item, parent, false);
        return new LikedPinsViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull LikedPinsViewHolder holder, int position) {
        Pin pin = pins.get(position);
        holder.authorTextView.setText(pin.user.name);
        Picasso.get().load(pin.urls.getRegular()).into(holder.pinImageView);
        holder.favoriteIcon.setOnClickListener(v -> itemCallback.onRemoveFavorite(pin));
    }

    @Override
    public int getItemCount() {
        return pins.size();
    }

    public static class LikedPinsViewHolder extends RecyclerView.ViewHolder {

        TextView authorTextView;
        TextView bioTextView;
        ImageView favoriteIcon;
        ImageView pinImageView;

        public LikedPinsViewHolder(@NonNull View itemView) {
            super(itemView);

            LikedPinsItemBinding binding = LikedPinsItemBinding.bind(itemView);
            authorTextView = binding.likedItemAuthorContent;
//            bioTextView = binding.likedItemAuthorBio;
            favoriteIcon = binding.likedItemFavoriteImageView;
            pinImageView = binding.likedItemImageView;
        }
    }

    // an ItemCallBack interface to relay the events from inside Adapter to Fragment
    interface ItemCallback {
        // onRemoveFavorite is te be implemented to remove articles in the saved database.
        void onRemoveFavorite(Pin pin);
    }

    private ItemCallback itemCallback;

    public void setItemCallback(ItemCallback itemCallback) {
        this.itemCallback = itemCallback;
    }

}
