package model;

/**
 * Created by garci on 14/06/2017.
 */
public enum Sexo {
    HOMBRE('H'),
    MUJER('M');

    private final char letraSexo;

    Sexo(char letra){
        this.letraSexo = letra;
    }

    public char getLetraSexo(){
        return this.letraSexo;
    }
}
