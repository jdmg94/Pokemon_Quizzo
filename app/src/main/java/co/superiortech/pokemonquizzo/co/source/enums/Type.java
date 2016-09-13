package co.superiortech.pokemonquizzo.co.source.enums;

/**
 * Created by josemunoz on 9/12/16.
 */
public enum Type {

    fire("#ff1744"),
    water("#2196f3"),
    grass("#4caf50"),
    poison("#ba68c8"),
    normal("#f9a825"),
    electric("#ffd740"),
    fighting("#e57373"),
    ground("#a1887f"),
    bug("#9ccc65"),
    psychic("#9575cd"),
    rock("#757575"),
    steel("#d7ccc8"),
    flying("#80deea"),
    fairy("#ea80fc"),
    ice("#18ffff"),
    ghost("#9c27b0"),
    dragon("#ff8a80");

    final private String value;

    private Type(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
