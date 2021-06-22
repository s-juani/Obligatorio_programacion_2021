package reportes;

import TADs.ClosedHash.HashTable;
import TADs.ClosedHash.exceptions.KeyAlreadyExistsException;
import TADs.ClosedHash.exceptions.KeyNotExistsException;
import TADs.DoubleHash.DoubleHashTable;
import TADs.LinkedList.ListaEnlazada;
import TADs.LinkedList.Nodo;
import TADs.LinkedList.PriorityNode;
import TADs.LinkedList.exceptions.EmptyQueueException;
import TADs.LinkedList.interfaces.ListWithPriority;
import TADs.LinkedList.interfaces.PriorityQueue;
import TADs.heap.Heap;
import TADs.heap.HeapImpl;
import TADs.heap.exceptions.EmptyHeapException;
import TADs.heap.exceptions.HeapOverflowException;
import entities.CastMember;
import entities.Movie;
import entities.MovieCastMember;


public abstract class Reportes {

    // top 5 de actores/actrices segun cantidad de apariciones en peliculas
    public static Object[][] reporte1() throws KeyNotExistsException, KeyAlreadyExistsException, HeapOverflowException, EmptyHeapException, EmptyQueueException {

        Object[][] top5 = new Object[5][2];
        ListWithPriority<CastMember> topList = new ListaEnlazada<>();
        for (Nodo<MovieCastMember> movieCastMember = MovieCastMember.getIterator().getHead(); movieCastMember!=null; movieCastMember=movieCastMember.getNext()){
            CastMember member = movieCastMember.getValue().getCastMember();
            PriorityNode<CastMember> temp = topList.findPriorityNode(member);
            if (movieCastMember.getValue().getCategory().equals("actor") || movieCastMember.getValue().getCategory().equals("actress")) {
                if (temp == null) {
                    topList.enqueueWithPriority(member, 1);
                } else {
                    topList.removeValue(temp.getValue());
                    temp.setNext(null);
                    topList.enqueueWithPriority(temp.getValue(), temp.getPriority()+1);
                }
            }
        }   // se obtiene lista de los actores ordenada por cantidad de apariciones en peliculas

        for (int p=0; p<5; p++){
            top5[p] = new Object[]{topList.getPriorityHead().getPriority(),topList.dequeue()};
        }   // se extraen los 5 primeros con sus respectivas cantidades de apariciones

        return top5;

        /*
        Heap<Integer, CastMember> topHeap = new HeapImpl<>(5,"min");
        for (Nodo<CastMember> member = CastMember.iterator.getHead(); member.getNext()!=null; member = member.getNext()){
            if (member.getValue().getMovieRoles().find("actor") ||
                    member.getValue().getMovieRoles().find("actress")) { // FIXME seleccionar solo las apariciones como actores
                int apariciones = movieCastMemberHash.columnSize(member.hashCode());
                if (i < 5) {
                    topHeap.add(apariciones, member.getValue());
                    i++;
                } else if (((Integer) topHeap.top()[0]) < apariciones) {
                    topHeap.delete();
                    topHeap.add(apariciones, member.getValue());
                }
            }
        }
         */
    }

    //prueba reporte1
    /*
    public static void showReporte1() throws HeapOverflowException, EmptyHeapException, KeyNotExistsException, KeyAlreadyExistsException, EmptyQueueException {
        Object[][] top5 = Reportes.reporte1();
        for (Object[] t : top5){
            CastMember member = (CastMember) t[1];
            int apariciones = (int) t[0];
            System.out.println("Nombre actor/actriz: " + member.getName());
            System.out.println("Cantidad de apariciones: " + apariciones + "\n");

        }
        System.out.println("Tiempo de ejecución de la consulta: "); // tiempo de ejecucion
    }

     */

    // top 5 causas de muerte de productores y directores nacidos en Italia, USA, Francia, UK
    // (fallecidos en esos paises tambien?)
    public static Object[] reporte2(HashTable<Integer, CastMember> castMemberHash){
        Object[] top5 = new Object[5];
        // iterar castMember en un hash indexado por pais de nacimiento (listas por paises)
        // filtrar por roles.find(producer,director) (y pais de muerte?)
        // ir agregando las causas a una lista enlazada: add.{causa, cantidad}  (priorityQueue puede servir)
        // cantidad ++ si la causa ya existe en la lista
        // llenar top5 con las causas con mas cantidad de muertes

        return top5;
    }


    // de las 14 peliculas de 1950-1960 con mas weightedAverage determinar el promedio de altura de los actores (not null)
    public static Object[] reporte3(HashTable<Integer, Movie> movieHash){
        // obtener todas las peliculas de 1950-1960 (hash indexado por anio)
        // extraer las 14 peliculas con mas weightedAverage (heap con weightedAverage como key y remove 14 veces)
        // en cada pelicula obtener los actores y calcular su promedio de altura
        return new Object[14];
    }

    // anio mas frecuente de nacimiento de actores y actrices
    public static Object[][] reporte4(HashTable<Integer, CastMember> castMemberHash){
        // usar CastMember.iterator y filtrar por roles.find(actor,actress)
        // ir agregando los anios de nacimiento a una lista ordenada por cantidad de nacimientos (una por actor y otra por actriz)
        // obtener el anio con mas nacimientos en ambos casos

        return new Object[2][2];
    }

    // top 10 de generos mas comunes de peliculas en las cuales haya participado algun act(or/riz) con mas de 2 hijos
    public static Object[] reporte5(HashTable<Integer, Movie> movieHash){
        // iterar por pelicula y filtrar segun tenga algun actor con mas de 2 hijos (getChildren()>2)
        // en la iteracion anterior ir agregando a una lista los generos y llevar la cuenta de la cantidad
        // filtrar los 10 generos mas comunes

        return new Object[10];
    }


}
