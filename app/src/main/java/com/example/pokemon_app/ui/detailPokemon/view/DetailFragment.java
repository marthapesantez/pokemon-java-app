package com.example.pokemon_app.ui.detailPokemon.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.pokemon_app.R;
import com.example.pokemon_app.databinding.FragmentDetailBinding;
import com.example.pokemon_app.ui.detailPokemon.view.adapter.TypeRecyclerViewListAdapter;
import com.example.pokemon_app.ui.detailPokemon.viewModel.DetailViewModel;
import com.example.pokemon_app.ui.mainPokemon.view.MainFragment;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {

    private FragmentDetailBinding fragmentDetailBinding;
    private TypeRecyclerViewListAdapter typeRecyclerViewListAdapter;
    private DetailViewModel detailViewModel;
    private String namePoke;
    private String imagePoke;
    private boolean checking0, checking1;
    private final String STATE_NAME = "Current Name";
    private final String STATE_IMAGE = "Current Image";

    public DetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            namePoke = requireActivity().getIntent().getExtras().getString(MainFragment.EXTRA_NAME_PARAM);
            imagePoke = requireActivity().getIntent().getExtras().getString(MainFragment.EXTRA_IMAGE_PARAM);
        } else {
            namePoke = savedInstanceState.getString(STATE_NAME);
            imagePoke = savedInstanceState.getString(STATE_IMAGE);
        }
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentDetailBinding = FragmentDetailBinding.inflate(inflater, container, false);
        setUpRecyclerView();
        detailViewModel = new ViewModelProvider(requireActivity()).get(DetailViewModel.class);

        return fragmentDetailBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpDataGetFromMainFragment();
        setArrowButton();
        updateDataForUI();
        updateProgressBarForUI();
        updateToastForUI();
        handleOnBackPressed();

        if (savedInstanceState == null) {
            checking0 = true;
            checking1 = true;
            fetchPokemonInfo(namePoke);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull @NotNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_NAME, namePoke);
        outState.putString(STATE_IMAGE, imagePoke);
    }

    private void setUpRecyclerView() {
        fragmentDetailBinding.typeList.setHasFixedSize(true);
        typeRecyclerViewListAdapter = new TypeRecyclerViewListAdapter(requireActivity(), new TypeRecyclerViewListAdapter.TypeDiff());
        fragmentDetailBinding.typeList.setAdapter(typeRecyclerViewListAdapter);
    }

    private void setUpDataGetFromMainFragment() {
        fragmentDetailBinding.namePoke.setText(namePoke);
        Glide.with(this).load(imagePoke).placeholder(R.drawable.placeholder).into(fragmentDetailBinding.imagePoke);
    }

    private void setArrowButton() {
        fragmentDetailBinding.arrow.setOnClickListener(view -> {
            detailViewModel.resetValuesLiveData();
            requireActivity().finish();
        });
    }

    private void fetchPokemonInfo(String namePoke) {
        detailViewModel.fetchPokemonInfo(namePoke);
    }

    private void updateDataForUI() {
        detailViewModel.getPokemonInfoLiveData().observe(getViewLifecycleOwner(), pokemonInfo -> {
            if (pokemonInfo != null && namePoke.equals(pokemonInfo.name)) {
                typeRecyclerViewListAdapter.refreshTypeList(pokemonInfo.types);
                fragmentDetailBinding.height.setText(pokemonInfo.heightFormatted);
                fragmentDetailBinding.weight.setText(pokemonInfo.weightFormatted);
                fragmentDetailBinding.progressHp.setProgress(pokemonInfo.hpFormatted);
                fragmentDetailBinding.progressAtk.setProgress(pokemonInfo.atkFormatted);
                fragmentDetailBinding.progressDef.setProgress(pokemonInfo.defFormatted);
                fragmentDetailBinding.progressSpd.setProgress(pokemonInfo.spdFormatted);
                fragmentDetailBinding.progressExp.setProgress(pokemonInfo.expFormatted);
                fragmentDetailBinding.progressHp.setLabelText(pokemonInfo.hpString);
                fragmentDetailBinding.progressAtk.setLabelText(pokemonInfo.atkString);
                fragmentDetailBinding.progressDef.setLabelText(pokemonInfo.defString);
                fragmentDetailBinding.progressSpd.setLabelText(pokemonInfo.spdString);
                fragmentDetailBinding.progressExp.setLabelText(pokemonInfo.expString);
            }
        });
    }

    private void updateProgressBarForUI() {
        detailViewModel.getProgressBarLiveData().observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean != null) {
                if (checking0 && aBoolean) {
                    fragmentDetailBinding.progressBar.setVisibility(View.VISIBLE);
                } else {
                    fragmentDetailBinding.progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    private void updateToastForUI() {
        detailViewModel.getToastLiveData().observe(getViewLifecycleOwner(), string -> {
            if (checking1 && string != null) {
                if (string.isEmpty()) {
                    Toast.makeText(requireActivity(), getResources().getString(R.string.ToastForOfflineMode), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireActivity(), string, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void handleOnBackPressed() {
        requireActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                detailViewModel.resetValuesLiveData();
                requireActivity().finish();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        detailViewModel.getDisposableToUnsubscribe();
    }
}