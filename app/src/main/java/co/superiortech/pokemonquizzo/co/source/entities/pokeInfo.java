package co.superiortech.pokemonquizzo.co.source.entities;

import java.io.Serializable;

import co.superiortech.pokemonquizzo.co.source.enums.Type;

/**
 * Created by josemunoz on 9/7/16.
 */
public class pokeInfo implements Serializable{

    private static final long serialVersionUID = -6325324727054903760L;

    private int pokedexNumber;
    private String name;
    private String alternative;
    private String image;
    private Type[] types;

    public int getPokedexNumber() {
        return pokedexNumber;
    }

    public void setPokedexNumber(int pokedexNumber) {
        this.pokedexNumber = pokedexNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlternative() {
        return alternative;
    }

    public void setAlternative(String alternative) {
        this.alternative = alternative;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Type[] getTypes() {
        return types;
    }

    public void setTypes(Type[] types) {
        this.types = types;
    }
}
