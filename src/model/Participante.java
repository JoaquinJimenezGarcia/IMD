package model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by garci on 14/06/2017.
 */
public class Participante implements Serializable{
    private static final long serialVersionUID = -6152922877399682638L;
    private int dorsal;
    private Sexo sexo;
    private String nombreCompleto;
    private String nacionalidad;
    private String club;
    private Categoria categoria;
    private int edad;
    private int fechaNacimiento;

    public Participante() {
        this.setDorsal(0);
        this.setSexo(Sexo.HOMBRE);
        this.setNombreCompleto("NS/NC");
        this.setNacionalidad("NSC");
        this.setClub("Indepentiente");
        this.setCategoria();
        this.setEdad(18);
    }

    public Participante(int dorsal, Sexo sexo, String nombreCompleto, String nacionalidad, String club, int fechaNacimiento) {
        this.setDorsal(dorsal);
        this.setSexo(sexo);
        this.setNombreCompleto(nombreCompleto);
        this.setNacionalidad(nacionalidad);
        this.setClub(club);
        this.setFechaNacimiento(fechaNacimiento);
        this.setCategoria();
    }

    public int getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(int fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        this.edad = calcularEdad();
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria() {
        //this.categoria = categoria;
        if (this.fechaNacimiento == 2000 || this.fechaNacimiento == 1999) {
            this.categoria = Categoria.JUNIOR;
        } else if (this.fechaNacimiento >= 1996 && this.fechaNacimiento <= 1998){
            this.categoria = Categoria.PROMESA;
        } else if (this.fechaNacimiento <= 1995 && this.edad < 35 ){
            this.categoria = Categoria.SENIOR;
        } else if (this.edad <= 45 && this.edad >= 35){
            this.categoria = Categoria.VETERANO1;
        } else if (this.edad <= 46 && this.edad >= 55) {
            this.categoria =  Categoria.VETERANO2;
        } else if (this.edad >= 56 && this.edad <= 65) {
            this.categoria =  Categoria.VETERANO3;
        } else {
            this.categoria =  Categoria.SUPER_SENIOR;
        }
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        if (club.equals("")){
            this.club = "Independiente";
        } else {
            this.club = club;
        }
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        Scanner input = new Scanner(System.in);

        if (nacionalidad.length()>3 || nacionalidad.equals("")){
            System.out.println("Nacionalidad incorrecta. Asegúrese que solo conste de tres caracteres: ");
            nacionalidad = input.nextLine();
            setNacionalidad(nacionalidad);
        } else {
            this.nacionalidad = nacionalidad;
        }
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int calcularEdad(){
       return 2017 - getFechaNacimiento();
    }

    /**
     * Comparador que comparará los participantes para poder ordenarlos por dorsal.
     */
    public static Comparator<Participante> comparadorPorDorsal = new Comparator<Participante>() {
        @Override
        public int compare(Participante p1, Participante p2) {
            return  p1.getDorsal() - (p2.getDorsal());
        }
    };

    @Override
    public String toString() {
        return this.dorsal + " - " + this.nombreCompleto;
    }

    public String toStringIndividual(){
        return
                "INFORMACIÓN SOBRE ATLETA \n" +
                "================================= \n" +
                "Nombre: " + this.getNombreCompleto() + "\n" +
                "Dorsal: " + this.getDorsal() + "\n" +
                "Club: " + this.getClub() + "\n" +
                "Nacionalidad: " + this.getNacionalidad().toUpperCase() + "\n" +
                "Sexo: " + this.getSexo() + "\n" +
                "Tiempo Oficial: " + this.getNombreCompleto() + "\n" +
                "Posición General: " + this.getNombreCompleto() + "\n" +
                "Posición Categoría: " + this.getNombreCompleto() + "\n";

    }

    @Override
    public boolean equals(Object obj) {
        // Self check
        if (this == obj){ return true; }

        // Null chek
        if (obj == null){ return false; }

        // Type check and cast
        if (this.getClass() != obj.getClass()){ return  false; }

        // Field comparaison
        Participante participante = (Participante) obj;
        return
                Objects.equals(this.dorsal, participante.dorsal);
    }
}
