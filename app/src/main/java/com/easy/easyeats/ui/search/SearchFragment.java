package com.easy.easyeats.ui.search;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.easy.easyeats.R;
import com.easy.easyeats.databinding.FragmentSearchBinding;
import com.easy.easyeats.model.Pin;
import com.easy.easyeats.repository.PinsRepository;
import com.easy.easyeats.repository.PinsViewModelFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    // Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private SearchViewModel viewModel;
    private FragmentSearchBinding binding;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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

        // TODO: why do we need view binding, and how to bing the view
        // We had to use findVIewByID to get all the views defined with id in xml layout files
        // If we have a big project with complicated UIs, it's annoying to do it for every single view.
        // View Binding allowing automatic binging UI layout resources.
        // Any views with an @+id tag will have a binding automatically.

        // Steps:
        // https://developer.android.com/topic/libraries/view-binding#fragments
        // Step 1: adding a private field, and using inflate() method to generate binding class
        //         Note: the name should corresponding to its xml file
        //               fragment_search.xml --> FragmentSearchBinding binding
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        // Step 2: Get a reference to the root view by calling the getRoot() method
        // Step 3: Return the root view from the onCreateView() to
        // make it the active view on the screen.
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PinsRepository repository = new PinsRepository();
        viewModel = new ViewModelProvider(this, new PinsViewModelFactory(repository)).get(SearchViewModel.class);
        // viewModel.setSearchInput("pizza");
        // Add a OnQueryTextListener to the search view to enable search with user input.
        binding.pinsSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!query.isEmpty()) {
                    viewModel.setSearchInput(query);
                }
                binding.pinsSearchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        // Added the setup for the RecyclerView
        SearchPinsAdapter pinsAdapter = new SearchPinsAdapter();
        // - Setting the layout as Staggered Grid for vertical orientation
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        // - Setting the layout as Staggered Grid for vertical orientation
        binding.pinsResultsRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        // - Setting Adapter to RecyclerView
        binding.pinsResultsRecyclerView.setAdapter(pinsAdapter);

        viewModel
                .getSearchedPins()
                .observe(
                        getViewLifecycleOwner(),
                        pinsResponse -> {
                            if (pinsResponse != null) {
                                Log.d("SearchFragment", pinsResponse.toString());
                                pinsAdapter.setPins(pinsResponse.results);
                            }
                        });

        // Built a anonymous implementation of ItemCallBack to Adapter
        pinsAdapter.setItemCallback(new SearchPinsAdapter.ItemCallback() {
            @Override
            public void onOpenDetails(Pin pin) {
                Log.d("onOpenDetails", pin.toString());
                SearchFragmentDirections.ActionMenuSearchToNavigationDetails direction = SearchFragmentDirections.actionMenuSearchToNavigationDetails(pin);
                NavHostFragment.findNavController(SearchFragment.this).navigate(direction);

            }
        });
    }

}