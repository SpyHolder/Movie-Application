package com.example.movieapplication.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.movieapplication.R;
import com.example.movieapplication.adapter.GenreOptionAdapter;
import com.google.android.material.navigation.NavigationView;

public class GenreOptionFragment extends Fragment {
    GridView gvGenre;
    GenreOptionAdapter adapter;
    GenreOptionInterface genreOptionInterface;
    String TitleGenre;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_genre_option, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] genre = {
                "Action","Drama","Adventure","Romance","Crime",
                "Sci-fi","Animation","Horror","History","Documentary","Mystery",
                "Family","School","War","Animation","Crime","Biography","Musical","Family","Sport"
        };
        gvGenre = view.findViewById(R.id.gvGenre);
        adapter = new GenreOptionAdapter(getContext(),genre);
        gvGenre.setAdapter(adapter);

        gvGenre.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fragment fragment = new GenreFragment();
                Bundle bundle = new Bundle();
                bundle.putString("id_movie",genre[i]);
                fragment.setArguments(bundle);
                TitleGenre = genre[i];
                FragmentTransaction SearchTransaction = getFragmentManager().beginTransaction();
                SearchTransaction.replace(R.id.navContainer,fragment);
                SearchTransaction.addToBackStack("OptionGenre");
                SearchTransaction.commit();
                getFragmentManager().popBackStack("OptionGenre",0);

                genreOptionInterface.setTitle(genre[i]);
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        NavigationView navigationView = getActivity().findViewById(R.id.nvBar);
        navigationView.setCheckedItem(R.id.nbGenre);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        genreOptionInterface = (GenreOptionInterface) context;
    }
}