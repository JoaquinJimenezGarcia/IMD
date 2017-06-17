package model;

import java.io.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Created by garci on 14/06/2017.
 */
public class Gestor {
    private ArrayList<Participante> participantes;
    private ArrayList<Participante> hombres;
    private ArrayList<Participante> mujeres;

    public Gestor(){
        participantes = new ArrayList<>();
        hombres = new ArrayList<>();
        mujeres = new ArrayList<>();
    }

    /**
     * Recibido un cliente, lo añadirá en la lista en caso de no tenerlo ya.
     * Lo verificará por su dni/nif
     * @param participante
     */
    public void registarAtleta(Participante participante) {
        if (participantes.contains(participante)) {
            participante = null;
            System.out.println("Ya hay un participante con este dorsal");
        }

        if (participante != null) {
            participantes.add(participante);
            Collections.sort(participantes,Participante.comparadorPorTiempo);

            if (participante.getSexo().equals(Sexo.HOMBRE)){
                hombres.add(participante);
                Collections.sort(hombres,Participante.comparadorPorTiempo);
            } else {
                mujeres.add(participante);
                Collections.sort(mujeres,Participante.comparadorPorTiempo);
            }

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

        Collections.sort(participantes,Participante.comparadorPorTiempo);

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
        for (Participante p: participantes) {
            if (p.getDorsal() == dorsal) {
                System.out.println(p.toStringIndividual());
            }
        }
    }

    public void editarAtleta(int dorsal){

    }

    public void borrarAtleta(int dorsal) {
        Iterator<Participante> itParticipante = participantes.iterator();

        while (itParticipante.hasNext()){
            Participante participante = itParticipante.next();
            String comprobador = null;

            try {
                if (participante.getDorsal() == dorsal) {
                    itParticipante.remove();
                    guardarParticipantes();
                    comprobador = "";
                    System.out.println("Participante " + participante.getNombreCompleto() + " borrado con éxito.");
                }

                if (comprobador == null){
                    System.out.println("El atleta no existe.");
                }
            } catch (NullPointerException e) {
                System.out.println("El atleta no existe.");
            }
        }

    }

    public void obtenerPosicionGeneral(){
        obtenerPosicion(participantes);
    }

    public void obtenerPosicionCategoria() {
        obtenerPosicion(hombres);
        obtenerPosicion(mujeres);
    }

    public void obtenerPosicion(ArrayList<Participante> tipo) {
        for (int i = 0; i < tipo.size(); i++) {
            if (!tipo.get(i).equals(tipo.get(0))) {
                long tiempoAnterior = tipo.get(0).getTiempoCorrecto().getTime();
                long tiempoActual = tipo.get(i).getTiempoCorrecto().getTime();
                long diferiencia = tiempoActual - tiempoAnterior;
                Time tiempoCorrecto = new java.sql.Time(diferiencia);

                System.out.println(tipo.get(i));

                tipo.get(i).setPosicionGeneral(tiempoCorrecto);
                tipo.get(i).setPosicionNumeroGeneral(i + 1);
            } else {
                tipo.get(i).setPosicionNumeroGeneral(i + 1);
            }
        }
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
