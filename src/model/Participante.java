package model;

import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by garci on 14/06/2017.
 */
public class Participante {
    private int dorsal;
    private Sexo sexo;
    private String nombreCompleto;
    private String nacionalidad;
    private String club;
    private Categoria categoria;
    private int edad;

    public Participante() {
        this.setDorsal(0);
        this.setSexo(Sexo.HOMBRE);
        this.setNombreCompleto("NS/NC");
        this.setNacionalidad("NSC");
        this.setClub("Indepentiente");
        this.setCategoria(Categoria.JUNIOR);
        this.setEdad(0);
    }

    public Participante(int dorsal, Sexo sexo, String nombreCompleto, String nacionalidad, String club, Categoria categoria, int edad) {
        this.setDorsal(dorsal);
        this.setSexo(sexo);
        this.setNombreCompleto(nombreCompleto);
        this.setNacionalidad(nacionalidad);
        this.setClub(club);
        this.setCategoria(categoria);
        this.setEdad(edad);
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

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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

        if (nacionalidad.length()>2 || nacionalidad.equals("")){
            System.out.println("Nacionalidad incorrecta. Asegúrese que solo conste de dos caracteres");
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
        return "Participante{" +
                "dorsal=" + dorsal +
                ", sexo='" + sexo + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", club='" + club + '\'' +
                ", categoria='" + categoria + '\'' +
                ", edad=" + edad +
                '}';
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
