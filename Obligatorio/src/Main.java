
import TADs.ClosedHash.ClosedHashTable;
import TADs.ClosedHash.HashTable;
import TADs.ClosedHash.exceptions.KeyAlreadyExistsException;
import TADs.ClosedHash.exceptions.KeyNotExistsException;
import TADs.LinkedList.exceptions.EmptyQueueException;
import TADs.LinkedList.interfaces.Lista;
import TADs.heap.exceptions.EmptyHeapException;
import TADs.heap.exceptions.HeapOverflowException;
import entities.*;
import filereader.*;
import reportes.Reportes;


import java.util.Scanner;

public class Main{

    public static HashTable<Integer, CastMember> castMemberHash;
    public static HashTable<Integer, Movie> movieHash;
    public static HashTable<Integer, Lista<Movie>> movieYearIndex;
    public static HashTable<Long, MovieCastMember> movieCastMemberHash;
    public static HashTable<Integer, Lista<MovieCastMember>> castMemberIndex = new ClosedHashTable<>(595411,0.5f);
    public static HashTable<Integer, Lista<MovieCastMember>> movieIndex;


    //especificar tamaño de las tablas en los constructores

    public static void main(String[] args) throws HeapOverflowException, EmptyHeapException, KeyNotExistsException, KeyAlreadyExistsException, EmptyQueueException {
        menuPrincipal();
    }

    public static void menuPrincipal() throws HeapOverflowException, EmptyHeapException, KeyNotExistsException, KeyAlreadyExistsException, EmptyQueueException {
        while (true){

            //init userInput scanner
            Scanner sc = new Scanner(System.in);

            System.out.println("1. Carga de datos");
            System.out.println("2. Ejecutar consultas");
            System.out.println("3. Salir");

            int opcion = sc.nextInt();
            //int opcion = Integer.parseInt(input); //controlar que no sea null, entero o no este en el rango 1,2,3
            switch (opcion) {
                case 1 -> {
                    cargaDatos();
                }
                case 2 -> menuReportes();
                case 3 -> salir();
                default -> menuPrincipal();
            }
        }
    }

    public static void menuReportes() throws HeapOverflowException, EmptyHeapException, KeyNotExistsException, KeyAlreadyExistsException, EmptyQueueException {
        System.out.println("1. Indicar el Top 5 de actores/actrices que más apariciones han tenido a lo largo de los años.");
        System.out.println("2. Indicar el Top 5 de las causas de muerte más frecuentes en directores y productores nacidos en Italia, Estados Unidos, Francia y UK.");
        System.out.println("3. Mostrar de las 14 películas con más weightedAverage, el promedio de altura de sus actores/actrices si su valor es distinto de nulo.");
        System.out.println("4. Indicar el año más habitual en el que nacen los actores y las actrices.");
        System.out.println("5. Indicar el Top 10 de géneros de películas más populares, en las cuales al menos un actor/actriz tiene 2 o más hijos.");
        System.out.println("6. Salir");

        //init userInput scanner
        Scanner sc = new Scanner(System.in);

        int opcion = sc.nextInt(); //controlar que no sea null
        switch (opcion) {
            case 1 -> showReporte1();
            case 2 -> showReporte2();
            case 3 -> showReporte3();
            case 4 -> showReporte4();
            case 5 -> showReporte5();
            case 6 -> salir();
            default -> menuReportes();
        }
    }

    public static void showReporte1() throws HeapOverflowException, EmptyHeapException, KeyNotExistsException, KeyAlreadyExistsException, EmptyQueueException {

        Object[][] top5 = Reportes.reporte1(castMemberIndex);
        for (Object[] t : top5){
            CastMember member = (CastMember) t[1];
            int apariciones = (int) t[0];
            System.out.println("Nombre actor/actriz: " + member.getName());
            System.out.println("Cantidad de apariciones: " + apariciones + "\n");
        }
        System.out.println("Tiempo de ejecución de la consulta: " + "\n"); // tiempo de ejecucion
        menuReportes();
    }
    public static void showReporte2() throws HeapOverflowException, EmptyHeapException, KeyNotExistsException, KeyAlreadyExistsException, EmptyQueueException {
        String[][] top5 = Reportes.reporte2(castMemberHash);

        long startTime = System.nanoTime();

        for (int i=0; i<5; i++){
            System.out.println("Causa de muerte: " + top5[i][1]);
            System.out.println("Cantidad de personas: " + top5[i][0] + "\n");
        }

        long finishTime = System.nanoTime();
        finishTime -= startTime;
        finishTime /= 1000000;

        System.out.println("Tiempo de ejecución de la consulta: " + finishTime + "ms\n"); // tiempo de ejecucion
        menuReportes();
    }
    public static void showReporte3() throws HeapOverflowException, EmptyHeapException, KeyNotExistsException, KeyAlreadyExistsException, EmptyQueueException {
        Object[] resultado = Reportes.reporte3(movieYearIndex, movieIndex);
        for (Object[] t : (Object[][]) resultado){
            System.out.println("Id película: ");
            System.out.println("Nombre: ");
            System.out.println("Altura promedio de actores:" + "\n");

        }
        System.out.println("Tiempo de ejecución de la consulta: " + "\n"); // tiempo de ejecucion
        menuReportes();
    }
    public static void showReporte4() throws HeapOverflowException, EmptyHeapException, KeyNotExistsException, KeyAlreadyExistsException, EmptyQueueException {
        Object[][] resultado = Reportes.reporte4(castMemberHash);
        System.out.println("Actores: ");
        System.out.println("        Año: " + resultado[0][0]);
        System.out.println("        Cantidad: " + resultado[0][1] + "\n");
        System.out.println("Actrices: ");
        System.out.println("        Año: " + resultado[1][0]);
        System.out.println("        Cantidad: " + resultado[1][1] + "\n");
        System.out.println("Tiempo de ejecución de la consulta: " + "\n"); // tiempo de ejecucion
        menuReportes();
    }
    public static void showReporte5() throws HeapOverflowException, EmptyHeapException, KeyNotExistsException, KeyAlreadyExistsException, EmptyQueueException {
        Object[] top10 = Reportes.reporte5(movieHash);
        for (Object[] t : (Object[][]) top10){
            System.out.println("Genero pelicula: ");
            System.out.println("Cantidad: " + "\n");
        }
        System.out.println("Tiempo de ejecución de la consulta: " + "\n"); // tiempo de ejecucion
        menuReportes();
    }
    public static void salir(){
        System.exit(0);
    }

    public static void cargaDatos() {

        long startTime = System.nanoTime();

        castMemberHash = fileReader.readCastMember(); //carga causeOfDeathHash y castMemberHash
        movieHash = fileReader.readMovie();  //carga movieHash y ratingHash
        movieCastMemberHash = fileReader.readTitlePrincipals(movieHash,castMemberHash,castMemberIndex);
        fileReader.readMovieRating(movieHash);

        long finishTime = System.nanoTime();
        finishTime -= startTime;
        finishTime /= 1000000;

        System.out.println("Carga de datos exitosa, tiempo de ejecución de la carga: " + finishTime + "ms");


    }

/*
    long startTime = System.nanoTime();
    long finishTime = System.nanoTime();
    finishTime -= startTime;
    finishTime /= 1000000;
    */




}