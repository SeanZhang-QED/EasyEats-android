package com.easy.easyeats.ui.like;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easy.easyeats.R;
import com.easy.easyeats.databinding.FragmentLikeBinding;
import com.easy.easyeats.model.Pin;
import com.easy.easyeats.repository.PinsRepository;
import com.easy.easyeats.repository.PinsViewModelFactory;
import com.easy.easyeats.ui.search.SearchPinsAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LikeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LikeFragment extends Fragment {

    // Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentLikeBinding binding;
    private LikedViewModel viewModel;

    public LikeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LikeFragment.
     */
    // Rename and change types and number of parameters
    public static LikeFragment newInstance(String param1, String param2) {
        LikeFragment fragment = new LikeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLikeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LikedPinsAdapter adapter = new LikedPinsAdapter();
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        binding.pinsLikedRecyclerView.setAdapter(adapter);
        binding.pinsLikedRecyclerView.setLayoutManager(staggeredGridLayoutManager);

        PinsRepository repository = new PinsRepository();
        viewModel = new ViewModelProvider(this, new PinsViewModelFactory(repository)).get(LikedViewModel.class);
        viewModel.getALlLikedPins()
                .observe(
                        getViewLifecycleOwner(),
                        likedPins -> {
                            if(likedPins != null) {
                                Log.d("LikedFragment", likedPins.toString());
                                adapter.setPins(likedPins);
                            }
                        });

        // Provide an anonymous implementation of ItemCallback to the likedPinsAdapter
        adapter.setItemCallback(new LikedPinsAdapter.ItemCallback() {
            @Override
            public void onRemoveFavorite(Pin pin) {
                viewModel.deleteLikedPin(pin);
            }
        });
    }

}