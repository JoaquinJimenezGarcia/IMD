package model;

import java.io.Serializable;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by garci on 14/06/2017.
 */
public class Participante implements Serializable{
    private static final long serialVersionUID = -6152922877399682638L;
    private SimpleDateFormat formatoTiempo = new SimpleDateFormat("HH:mm:ss");
    Date tiempoCorrectoConFecha;
    private String tiempo;
    private int dorsal;
    private Sexo sexo;
    private String nombreCompleto;
    private String nacionalidad;
    private String club;
    private Categoria categoria;
    private int edad;
    private int fechaNacimiento;
    private Time posicionGeneral;
    private int posicionNumeroGeneral;
    private Time posicionCategoria;
    private int posicionNumeroCategoria;

    public Participante() {
        this.setDorsal(0);
        this.setSexo(Sexo.HOMBRE);
        this.setNombreCompleto("NS/NC");
        this.setNacionalidad("NSC");
        this.setClub("Indepentiente");
        this.setCategoria();
        this.setEdad(18);
        this.setTiempo("00:00:00");
        this.setTiempoCorrectoConFecha();
    }

    public Participante(int dorsal, Sexo sexo, String nombreCompleto, String nacionalidad, String club, int fechaNacimiento, String tiempo) {
        this.setDorsal(dorsal);
        this.setSexo(sexo);
        this.setNombreCompleto(nombreCompleto);
        this.setNacionalidad(nacionalidad);
        this.setClub(club);
        this.setFechaNacimiento(fechaNacimiento);
        this.setCategoria();
        this.setTiempo(tiempo);
        this.setTiempoCorrectoConFecha();
    }

    public Time getPosicionCategoria() {
        return posicionCategoria;
    }

    public void setPosicionCategoria(Time posicionCategoria) {
        this.posicionCategoria = posicionCategoria;
    }

    public int getPosicionNumeroCategoria() {
        return posicionNumeroCategoria;
    }

    public void setPosicionNumeroCategoria(int posicionNumeroCategoria) {
        this.posicionNumeroCategoria = posicionNumeroCategoria;
    }

    public int getPosicionNumeroGeneral() {
        return posicionNumeroGeneral;
    }

    public void setPosicionNumeroGeneral(int posicionNumeroGeneral) {
        this.posicionNumeroGeneral = posicionNumeroGeneral;
    }

    public Time getPosicionGeneral() {
        return posicionGeneral;
    }

    public void setPosicionGeneral(Time posicionCategoria) {
        this.posicionGeneral = posicionCategoria;
    }

    public Date getTiempoCorrectoConFecha() {
        return tiempoCorrectoConFecha;
    }

    public Time getTiempoCorrecto(){

        long lnMilisegundos = tiempoCorrectoConFecha.getTime();
        Time tiempoCorrecto = new java.sql.Time(lnMilisegundos);

        return tiempoCorrecto;
    }

    public void setTiempoCorrectoConFecha() {
        try {
            this.tiempoCorrectoConFecha = formatoTiempo.parse(tiempo);
        } catch (ParseException e) {
            System.out.println("Ha introducido un tiempo incorrecto.");
        } catch (NullPointerException e) {
            System.out.println("Introduzca tiempo correcto: ");
            try {
                this.tiempoCorrectoConFecha = formatoTiempo.parse(tiempo);
                setTiempoCorrectoConFecha();
            } catch (ParseException p) {

            }
        }

    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
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
    public static Comparator<Participante> comparadorPorTiempo = new Comparator<Participante>() {

        @Override
        public int compare(Participante p1, Participante p2) {
            return  p1.getTiempoCorrectoConFecha().compareTo(p2.getTiempoCorrectoConFecha());
        }
    };

    @Override
    public String toString() {
        if (this.getPosicionCategoria() != null) {
            return this.dorsal + " - " + this.nombreCompleto + " - " + "(a " + this.getPosicionCategoria() + ")" + " - " + this.getTiempoCorrecto();
        } else {
            return this.dorsal + " - " + this.nombreCompleto + " - " + this.getTiempoCorrecto();
        }
    }

    public String toStringIndividual(){
        if (posicionGeneral != null) {
            return
                    "INFORMACIÓN SOBRE ATLETA \n" +
                            "================================= \n" +
                            "Nombre: " + this.getNombreCompleto() + "\n" +
                            "Dorsal: " + this.getDorsal() + "\n" +
                            "Club: " + this.getClub() + "\n" +
                            "Nacionalidad: " + this.getNacionalidad().toUpperCase() + "\n" +
                            "Sexo: " + this.getSexo() + "\n" +
                            "Tiempo Oficial: [" + this.getTiempoCorrecto() + "] \n" +
                            "Posición General: #" + this.getPosicionNumeroGeneral() + " (a: " + this.getPosicionGeneral() + ") \n" +
                            "Posición Categoría: #" + this.getPosicionNumeroCategoria() + " (a: " + this.getPosicionCategoria() + ") \n";
        } else {
            return
                    "INFORMACIÓN SOBRE ATLETA \n" +
                            "================================= \n" +
                            "Nombre: " + this.getNombreCompleto() + "\n" +
                            "Dorsal: " + this.getDorsal() + "\n" +
                            "Club: " + this.getClub() + "\n" +
                            "Nacionalidad: " + this.getNacionalidad().toUpperCase() + "\n" +
                            "Sexo: " + this.getSexo() + "\n" +
                            "Tiempo Oficial: [" + this.getTiempoCorrecto() + "] \n" +
                            "Posición General: #" + this.getPosicionNumeroGeneral() + " \n" +
                            "Posición Categoría: [a: " + this.getNombreCompleto() + "] \n";
        }

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
                Objects.equals(this.getDorsal(), participante.getDorsal());
    }
}
