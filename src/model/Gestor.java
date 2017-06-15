package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by garci on 14/06/2017.
 */
public class Gestor {
    private ArrayList<Participante> participantes;

    public Gestor(){
        participantes = new ArrayList<>();
    }

    /**
     * Recibido un cliente, lo añadirá en la lista en caso de no tenerlo ya.
     * Lo verificará por su dni/nif
     * @param participante
     */
    public void registarCliente(Participante participante) {
        if (participantes.contains(participante)) {
            participante = null;
            System.out.println("Ya hay un participante con este dorsal");
        }

        if (participante != null) {
            participantes.add(participante);
            guardarParticipantes();
        }
    }

    public int longitud(){
        return participantes.size();
    }

    public void consultarClasificaciones(int opcion){
        String sexo;
        Sexo sexoGenero;
        int indice = 0;

        if (opcion == 1){
            sexo = "hombres";
            sexoGenero = Sexo.HOMBRE;
        } else {
            sexo = "mujeres";
            sexoGenero = Sexo.MUJER;
        }

        Collections.sort(participantes,Participante.comparadorPorDorsal);

        System.out.println("# Clasificación general " + sexo);
        System.out.println("#POS DORSAL NOMBRE A TIEMPO");
        System.out.println("==============================================");

        for (Participante participante : participantes) {
            if (participante.getSexo().equals(sexoGenero)) {
                indice++;
                System.out.println(indice + " - " + participante);
            }
        }
    }

    public void consultarAtleta(int dorsal){

    }

    public void editarAtleta(int dorsal){

    }

    public void borrarAtleta(int dorsal) {

    }

    /**
     * Escribirá la lista actual de clientes en info/participantes.dat
     */
    public void guardarParticipantes() {
        try {
            ObjectOutputStream fos = new ObjectOutputStream(new FileOutputStream("info/participantes.dat"));
            fos.writeObject(participantes);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cargará la lista guardada de clientes en info/participantes.dat
     */
    public void cargarParticipantes(){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("info/participantes.dat"));

            participantes = (ArrayList<Participante>) ois.readObject();

            ois.close();
        } catch (IOException e) {
        } catch (ClassNotFoundException e){

        }
    }
}
