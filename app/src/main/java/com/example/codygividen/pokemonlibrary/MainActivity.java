package com.example.codygividen.pokemonlibrary;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final String POKEMON_NAME = "pokemon_name";
    //public static final String POKEMON_EFFECT = "pokemon_effect";
    private PokemonFragment pokemonFragment;

    @BindView(R.id.input_pokemon_name)
    protected TextInputEditText inputPokemonName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.capture_button)
    protected void captureButton(){

        if(inputPokemonName.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter a pokemans name!!", Toast.LENGTH_LONG).show();
        }else{
            pokemonFragment = PokemonFragment.newInstance();
            Bundle bundle = new Bundle();
            bundle.putString((POKEMON_NAME),inputPokemonName.getText().toString());
            pokemonFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, pokemonFragment).commit();

        }
    }

    @Override
    public void onBackPressed() {
        if(pokemonFragment.isVisible()){
            getSupportFragmentManager().beginTransaction().remove(pokemonFragment).commit();
        }else{
            super.onBackPressed();
        }

    }
}
