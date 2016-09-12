package co.superiortech.pokemonquizzo.co.source.enums;

/**
 * Created by josemunoz on 9/8/16.
 */
public enum Regions {
    KANTO("kanto"),
    JOHTO("johto"),
    HOENN("hoenn"),
    SINNOH("sinnoh"),
    UNOVA("unova"),
    KALOS("kalos");

    private final String file;

    private Regions(String file){
        this.file = file;
    }

    public String getFile(){
        return "json/"+this.file+".json";
    }

    public String getImage(){
        return "images/"+this.file+".png";
    }

    public String getName(){
        return this.file;
    }
}
