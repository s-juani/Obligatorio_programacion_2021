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
import TADs.arraylist.MyArrayListImpl;
import TADs.arraylist.MyArrayList;
import TADs.heap.Heap;
import TADs.heap.HeapImpl;
import TADs.heap.exceptions.EmptyHeapException;
import TADs.heap.exceptions.HeapOverflowException;
import entities.CastMember;
import entities.CauseOfDeath;
import entities.Movie;
import entities.MovieCastMember;

import java.awt.event.FocusEvent;


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




    // top 5 causas de muerte de productores y directores nacidos en Italia, USA, Francia, UK
    public static String[][] reporte2(HashTable<Integer, CastMember> castMemberHash){
        String[][] top5 = new String[5][2];

        castMemberHash.iteratorReset();
        CastMember castMember;
        String country;

        MyArrayList<SortNode<Integer,CauseOfDeath>> list = new MyArrayListImpl<>();

        while ((castMember = castMemberHash.iteratorNext())!=null){
            country = castMember.getBirthCountry();
            if (country.contains("USA") || country.contains("UK") || country.contains("France") || country.contains("Italy")) {
                if (castMember.getOcupation().find("producer") || castMember.getOcupation().find("director")){

                    //agregar causa de muerte al arrayList o incrementar su cantidad si ya estaba

                    for (int i=0; i<castMember.getReasonOfDeathLenght(); i++){
                        boolean hasCauseOfDeath = false;
                        for (int j=0; j<list.getLenght(); j++){
                            if (list.get(j).getValue().equals(castMember.getReasonOfDeath(i))){
                                list.get(j).setKey(list.get(j).getKey() + 1);
                                hasCauseOfDeath = true;
                                break; // verificar que no corte el loop for de la i
                            }
                        }
                        if (!hasCauseOfDeath){
                            SortNode<Integer,CauseOfDeath> temp = new SortNode<>(1, castMember.getReasonOfDeath(i));
                            list.add(temp);
                        }
                    }




                    /*Object[] coso = new Object[2];
                    coso[0] = 3;
                    coso[1] = "abcd";
                    list.add(coso);
                    Integer x = (int) list.get(0)[0];*/

                }
            }
        }
        Sort<Integer,CauseOfDeath> sort = new Sort<>();
        sort.QuickSort(list);


        for (int i=0; i<5; i++){
            SortNode<Integer, CauseOfDeath> temp = list.get(i);
            top5[i][0] = String.valueOf(temp.getKey());
            top5[i][1] = temp.getValue().getName();
        }


        /**
         * Iterar castMemberHash, checkear si ocupacion.contains(productor) >> checkear si birthCountry.contains(pais) >>
         * >> agregar aparicion de causeOfDeath
         * >> cargar en un ArrayList
         * >> ordenar con QuickSort
         * >> extraer los primeros 5
         */







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
