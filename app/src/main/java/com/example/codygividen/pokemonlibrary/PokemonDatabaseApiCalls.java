package com.example.codygividen.pokemonlibrary;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonDatabaseApiCalls {
    @GET("{name}")
    Call<PokemonInfo> getPokemonInfo(@Path("name") String pokemonNameText);


    class PokemonInfo{
        @SerializedName("name")
        private String pokemonName;
        @SerializedName("abilities")
        private List<Abilities> pokemonEffect;

        @SerializedName("sprites")
        private PokemonImages pokemonImage;




        public PokemonImages getPokemonImage() {
            return pokemonImage;
        }

        public String getPokemonName() {
            return pokemonName;
        }

        public List<Abilities> getPokemonEffect() {
            return pokemonEffect;
        }

        class Abilities {

            @SerializedName("ability")
            private Ability pokemonAbility;

            public Ability getPokemonAbility() {
                return pokemonAbility;
            }

            class Ability {

                @SerializedName("name")
                private String abilityName;

                public String getAbilityName() {
                    return abilityName;
                }
            }
        }

        class PokemonImages {

            @SerializedName("front_default")
            private String defaultImage;

            public String getDefaultImage() {
                return defaultImage;
            }
        }
    }
}
