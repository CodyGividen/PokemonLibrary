package com.example.codygividen.pokemonlibrary;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.codygividen.pokemonlibrary.MainActivity.POKEMON_NAME;
import static com.example.codygividen.pokemonlibrary.R.id.pokemon_imageview;

public class PokemonFragment extends Fragment {
    private String baseUrl = "http://pokeapi.co/api/v2/pokemon/";
    private Retrofit retrofit;
    private PokemonDatabaseApiCalls pokemonDatabaseApiCalls;
    @BindView(R.id.pokemon_name_textview)
    protected TextView pokemonNameTextView;
    @BindView(R.id.pokemon_effect_textview)
    protected TextView pokemonEffectTextView;
    @BindView(pokemon_imageview)
    protected ImageView pokemonImage;

    @Override
    public void onStart() {
        super.onStart();
        String pokemonName = getArguments().getString(POKEMON_NAME);
        buildRetrofit();
        makeApiCall(pokemonName);

    }
    private void makeApiCall(final String pokemonName) {
        pokemonDatabaseApiCalls.getPokemonInfo(pokemonName).enqueue(new Callback<PokemonDatabaseApiCalls.PokemonInfo>() {
            @Override
            public void onResponse(Call<PokemonDatabaseApiCalls.PokemonInfo> call, Response<PokemonDatabaseApiCalls.PokemonInfo> response) {
                if (response.isSuccessful()) {
                    pokemonNameTextView.setText(response.body().getPokemonName());
                    pokemonEffectTextView.setText(response.body().getPokemonEffect().get(0).getPokemonAbility().getAbilityName());

                    Glide.with(getActivity()).load(response.body().getPokemonImage().getDefaultImage()).into(pokemonImage);

                    Toast.makeText(getContext(), "Info set!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "Error, try again!!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PokemonDatabaseApiCalls.PokemonInfo> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void buildRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        pokemonDatabaseApiCalls = retrofit.create(PokemonDatabaseApiCalls.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pokemon_fragment,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    public static PokemonFragment newInstance() {

        Bundle args = new Bundle();

        PokemonFragment fragment = new PokemonFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
