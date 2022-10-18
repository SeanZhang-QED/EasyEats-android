package com.easy.easyeats.ui.details;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easy.easyeats.R;
import com.easy.easyeats.databinding.FragmentDetailsBinding;
import com.easy.easyeats.model.Pin;
import com.easy.easyeats.model.Tag;
import com.squareup.picasso.Picasso;

public class DetailsFragment extends Fragment {
    private FragmentDetailsBinding binding;

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Pin pin = DetailsFragmentArgs.fromBundle(getArguments()).getPin();
        binding.detailsAuthorContent.setText(pin.user.name);
        binding.detailsAuthorBio.setText(pin.user.bio);
        binding.detailsItemLikesNumber.setText(pin.likes);

        StringBuilder tagsString = new StringBuilder();
        if(pin.tags != null) {
            for(Tag tag: pin.tags) {
                tagsString.append(tag.toString());
            }
        }
        binding.detailsItemTags.setText(tagsString.toString());
        Picasso.get().load(pin.user.profile_image.medium).into(binding.detailsAuthorAvatar);
        Picasso.get().load(pin.urls.regular).into(binding.detailsItemImage);
    }
}