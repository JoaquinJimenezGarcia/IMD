package model;

/**
 * Created by garci on 14/06/2017.
 */
public enum Categoria {
    JUNIOR("Junior"),
    PROMESA("Promesa"),
    SENIOR("Senior"),
    VETERANO1("Veterano"),
    VETERANO2("Veterano2"),
    VETERANO3("Veterano3"),
    SUPER_SENIOR("Super Senior");

    private final String nombreCategoria;

    Categoria(String nombre){
        this.nombreCategoria = nombre;
    }

    public String getNombreCategoria(){
        return this.nombreCategoria;
    }
}
