package co.superiortech.pokemonquizzo.co.source.enums;

/**
 * Created by josemunoz on 9/8/16.
 */
public enum Regions {
    KANTO("json/kanto.json"),
    JOHTO("json/johto.json"),
    HOENN("json/hoenn.json"),
    SINNOH("json/sinnoh.json"),
    UNOVA("json/unova.json"),
    KALOS("json/kalos.json");

    private final String file;

    private Regions(String file){
        this.file = file;
    }

    public String getFile(){
        return this.file;
    }
}
