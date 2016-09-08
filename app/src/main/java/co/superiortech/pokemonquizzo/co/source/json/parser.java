package co.superiortech.pokemonquizzo.co.source.json;

import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import co.superiortech.pokemonquizzo.co.source.entities.pokeInfo;
import co.superiortech.pokemonquizzo.co.source.enums.Regions;

/**
 * Created by josemunoz on 9/8/16.
 */
public class parser {

    public static ArrayList<pokeInfo> getPokedex(Regions region, AssetManager am) {
        ArrayList<pokeInfo> pokedex = null;
        Gson gson = new Gson();
        try {
            pokedex = gson.fromJson(new InputStreamReader(am.open(region.getFile())), new TypeToken<ArrayList<pokeInfo>>() {
            }.getType());
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return pokedex;
    }

}
