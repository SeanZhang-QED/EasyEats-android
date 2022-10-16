package com.easy.easyeats.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.easy.easyeats.R;
import com.easy.easyeats.databinding.SwipePinCardBinding;
import com.easy.easyeats.model.Pin;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CardSwipeAdapter extends RecyclerView.Adapter<CardSwipeAdapter.CardSwipeViewHolder>{
    // 1. Supporting data:
    private List<Pin> pins = new ArrayList<>();

    public void setPins(List<Pin> pinList) {
        pins.clear();
        pins.addAll(pinList);
        notifyDataSetChanged();
    }

    // 2. CardSwipeViewHolder:
    public static class CardSwipeViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView authorTextView;
        TextView altTextView;

        public CardSwipeViewHolder(@NonNull View itemView) {
            super(itemView);

            SwipePinCardBinding binding = SwipePinCardBinding.bind(itemView);
            imageView = binding.swipeCardImageView;
            authorTextView = binding.swipeCardAuthor;
            altTextView = binding.swipeCardAlt;
        }
    }

    // 3. Adapter overrides:
    @NonNull
    @Override
    public CardSwipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.swipe_pin_card, parent, false);
        return new CardSwipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardSwipeViewHolder holder, int position) {
        Pin pin = pins.get(position);
        holder.authorTextView.setText(pin.user.name);
        holder.altTextView.setText(pin.alt_description);
        if (pin.urls.getRegular() != null) {
            Picasso.get().load(pin.urls.getRegular()).into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return pins.size();
    }

}
