package controller;

import model.Categoria;
import model.Gestor;
import model.Participante;
import model.Sexo;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by garci on 14/06/2017.
 */
public class GestorApp {
    Gestor gestor;

    public GestorApp(){
        gestor = new Gestor();

        gestor.registarAtleta(new Participante(12,Sexo.HOMBRE, "Joaquin", "ESP", "Marta", 1997, "00:28:00"));
        gestor.registarAtleta(new Participante(10,Sexo.MUJER, "Marta", "ESP", "Juaki", 1993, "00:25:00"));
        gestor.registarAtleta(new Participante(9,Sexo.MUJER, "Carmen", "ESP", "", 1994, "00:27:00"));
        gestor.registarAtleta(new Participante(66,Sexo.MUJER, "Nadia", "ESP", "", 1990, "00:26:00"));
        gestor.registarAtleta(new Participante(24,Sexo.HOMBRE, "Jorge", "ESP", "", 1999, "00:24:00"));
        gestor.registarAtleta(new Participante(99,Sexo.HOMBRE, "Pepe", "ESP", "", 1980, "00:30:00"));
    }

    public void run() {
        int option;

        gestor.cargarParticipantes();

        while ((option = showMenu()) != 0) {
            switch (option) {
                case 1:
                    gestor.registarAtleta(leerAtleta());
                    break;
                case 2:
                    if (gestor.longitud() > 0) {
                        gestor.obtenerPosicionCategoria();
                        gestor.consultarClasificaciones(consultarGenero());
                    }
                    break;
                case 3:
                    if (gestor.longitud() > 0) {
                        gestor.obtenerPosicionCategoria();
                        gestor.obtenerPosicionGeneral();
                        gestor.consultarAtleta(getIdentificador());
                    }
                    break;
                case 4:
                    if (gestor.longitud() > 0) {
                        gestor.editarAtleta(getIdentificador());
                    }
                    break;
                case 5:
                    if (gestor.longitud() > 0) {
                        System.out.println(gestor.borrarAtleta(getIdentificador()));
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private int consultarGenero(){
        Scanner input = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Introduzca sexo: ");
            System.out.println("1. Hombre");
            System.out.println("2. Mujer");
            try {
                opcion = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Introduzca una opción correcta.");
                return consultarGenero();
            }
        } while (opcion!=1 && opcion != 2);

        return opcion;
    }

    /**
     * Muestra menú con las diferentes opciones para que el usuario indique
     * qué desea hacer.
     * @return
     */
    public int showMenu(){
        Scanner input = new Scanner(System.in);
        int option;

        System.out.println("**********************************");
        System.out.println("* 1. Añadir marca                *");
        if (gestor.longitud()>0) {
            System.out.println("* 2. Consultar clasificaciones   *");
            System.out.println("* 3. Consultar info atleta       *");
            System.out.println("* 4. Editar info atleta          *");
            System.out.println("* 5. Borrar Atleta               *");
        }
        System.out.println("* 0. Salir                       *");
        System.out.println("**********************************");

        System.out.println("Opción: ");

        /**
         * Te obliga a seleccionar siempre una opción correcta. En caso
         * contrario, te obligará a volver a elegir una.
         */
        try {
            option = input.nextInt();
            return option;
        }catch (InputMismatchException e){
            System.out.println("Opción inválida.");
        }
        return showMenu();
    }

    public Participante leerAtleta(){
        Scanner input = new Scanner(System.in);

        int dorsal;
        Sexo sexo;
        String nombreCompleto;
        String nacionalidad;
        String club;
        int fechaNacimiento;
        String marcaRealizada;

        dorsal = getIdentificador();

        if (consultarGenero() ==1){
            sexo = Sexo.HOMBRE;
        } else {
            sexo = Sexo.MUJER;
        }

        System.out.println("Inserte el nombre con apellidos: ");
        nombreCompleto = input.nextLine();

        System.out.println("Inserte la nacionalidad (formato de tres letras): ");
        nacionalidad = input.next();

        input.nextLine();

        System.out.println("Inserte el club: ");
        club = input.nextLine();

        fechaNacimiento = getFechaNacimiento();

        System.out.println("Marca realizada (hh:mm:ss): ");
        marcaRealizada = input.nextLine();

        return new Participante(dorsal,sexo, nombreCompleto, nacionalidad, club, fechaNacimiento, marcaRealizada);

    }

    private int getFechaNacimiento(){
        Scanner input = new Scanner(System.in);

        System.out.print("Indique el año de nacimiento: ");
        try {
            return input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Introduzca un año correcto.");
        }
        return getFechaNacimiento();
    }

    private int getIdentificador(){
        Scanner input = new Scanner(System.in);

        System.out.print("Dorsal: ");
        try {
            return input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Introduzca un dorsal correcto");
        }
        return getIdentificador();
    }

}
