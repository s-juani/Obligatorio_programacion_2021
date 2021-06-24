package reportes;

import TADs.ClosedHash.HashTable;
import TADs.ClosedHash.exceptions.KeyAlreadyExistsException;
import TADs.ClosedHash.exceptions.KeyNotExistsException;
import TADs.LinkedList.exceptions.EmptyQueueException;
import TADs.LinkedList.interfaces.Lista;
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
import reportes.sort.Sort;
import reportes.sort.SortNode;


public abstract class Reportes {

    // top 5 de actores/actrices segun cantidad de apariciones en peliculas
    public static Object[][] reporte1(HashTable<Integer,Lista<MovieCastMember>> castMemberIndex) throws KeyNotExistsException, KeyAlreadyExistsException, HeapOverflowException, EmptyHeapException, EmptyQueueException {
        Object[][] top5 = new Object[5][2];
        Heap<Integer, CastMember> topHeap = new HeapImpl<>(5,"min");

        Lista<MovieCastMember> movieList;
        CastMember castMember;
        MovieCastMember movieCastMember;
        castMemberIndex.iteratorReset();
        int apariciones;
        while ((movieList = castMemberIndex.iteratorNext()) != null) {
            apariciones = 0;
            movieList.iteratorReset();
            movieCastMember = movieList.iteratorNext();
            castMember = movieCastMember.getCastMember();
            while ((movieCastMember) != null) {
                if (movieCastMember.getCategory().equals("actor") || movieCastMember.getCategory().equals("actress")) {
                    apariciones++;
                }
                movieCastMember = movieList.iteratorNext();
            }
            if (apariciones != 0) {
                if (topHeap.size() == 5) {
                    if (((Integer) topHeap.top()[0]) < apariciones) {
                        topHeap.delete();
                        topHeap.add(apariciones, castMember);
                    }
                } else {
                    topHeap.add(apariciones, castMember);
                }
            }
        }
        for (int p = 4; p >= 0; p--){
            Object[] element = topHeap.remove();
            top5[p] = new Object[]{element[0],element[1]};
        }

        /*
        for (Nodo<CastMember> member = castMemberIndex.iteratorNext(); member.getNext()!=null; member = member.getNext()){
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

        return top5;

        /*
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
        *
        * */

    }



    // top 5 causas de muerte de productores y directores nacidos en Italia, USA, Francia, UK
    public static String[][] reporte2(HashTable<Integer, CastMember> castMemberHash){
        String[][] top5 = new String[5][2];

        castMemberHash.iteratorReset();
        CastMember castMember;
        String country;

        MyArrayList<SortNode<Integer,CauseOfDeath>> list = new MyArrayListImpl<>(1000);

        while ((castMember = castMemberHash.iteratorNext())!=null){
            country = castMember.getBirthCountry();
            if (country.contains("USA") || country.contains("UK") || country.contains("France") || country.contains("Italy")) {
                if (castMember.getOcupation().find("producer") || castMember.getOcupation().find("director")){

                    //agregar causa de muerte al arrayList o incrementar su cantidad si ya estaba
                    boolean hasCauseOfDeath = false;
                    for (int j=0; j<list.size(); j++){
                        //System.out.println(list.get(j).getValue().getName());
                        if (list.get(j).getValue().getName() == castMember.getReasonOfDeath().getName()){
                            list.get(j).setKey(list.get(j).getKey() + 1);
                            hasCauseOfDeath = true;
                            break;
                        }
                    }
                    if (!hasCauseOfDeath && castMember.getReasonOfDeath()!=null){
                        SortNode<Integer,CauseOfDeath> temp = new SortNode<>(1, castMember.getReasonOfDeath());
                        list.add(temp);
                    }
                }
            }
        }
        Sort<Integer,CauseOfDeath> sort = new Sort<>();
        sort.quickSort(list, 0, list.size()-1);

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
    public static Object[][] reporte3(HashTable<Integer, Lista<Movie>> yearMovieIndex,
                                    HashTable<Integer, Lista<MovieCastMember>> movieCastMemberIndex){
        Object[][] resultado = new Object[14][2];
        Heap<Float, Movie> topHeap = new HeapImpl<>(14,"min");

        // extraer las 14 peliculas de 1950-1960 con mas weightedAverage
        for (int year = 1950; year<=1960; year++){
            try{
                Lista<Movie> movieList = yearMovieIndex.get(year);
                movieList.iteratorReset();
                Movie tempMovie;
                while ((tempMovie = movieList.iteratorNext()) != null){
                    Float wheightedAverage = tempMovie.getRating().getWeightedAverage();
                    if (topHeap.size() == 14) {
                        if (((Float) topHeap.top()[0]) < wheightedAverage) {
                            topHeap.delete();
                            topHeap.add(wheightedAverage, tempMovie);
                        }
                    } else topHeap.add(wheightedAverage, tempMovie);
                }
            } catch (KeyNotExistsException | HeapOverflowException | EmptyHeapException ignore) {}
        }

        // en cada pelicula obtener los actores y calcular su promedio de altura
        int i = 0;
        while (topHeap.size()!=0){
            try {
                Movie movie = (Movie) topHeap.remove()[1];
                float promedio = 0.0f;
                Integer altura;
                int cantidad = 0;

                Lista<MovieCastMember> actorsList = movieCastMemberIndex.get(movie.hashCode());
                actorsList.iteratorReset();
                MovieCastMember movieCastMember;
                while ((movieCastMember = actorsList.iteratorNext()) != null){
                    if (movieCastMember.getCategory().equals("actor") || movieCastMember.getCategory().equals("actress")){
                        altura = movieCastMember.getCastMember().getHeight();
                        if (altura != null){
                            promedio += (float) altura;
                            cantidad++;
                        }
                    }
                }
                if (cantidad != 0) {
                    promedio /= (float) cantidad;
                    resultado[i] = new Object[]{promedio, movie};
                    i++;
                }

            } catch (KeyNotExistsException | EmptyHeapException | HeapOverflowException ignore) {}
        }
        return resultado;
    }

    // anio mas frecuente de nacimiento de actores y actrices
    public static Object[][] reporte4(HashTable<Integer, CastMember> castMemberHash){

        //sortNode = key:year , value:[cantidad actores, cantidad actrices]
        MyArrayList<SortNode<Integer, Integer[]>> list = new MyArrayListImpl<>(200);
        castMemberHash.iteratorReset();
        CastMember castMember;
        while ((castMember = castMemberHash.iteratorNext()) != null){
            if (castMember.getBirthDate()!=null){
                Integer year = castMember.getBirthDate().getYear();
                boolean yearExists = false;
                for (int j=0; j<list.size(); j++){
                    //System.out.println(list.get(j).getValue().getName());
                    if (list.get(j).getKey().equals(year)){
                        if (castMember.getOcupation().find("actor")){
                            Integer[] cantidad = list.get(j).getValue();
                            cantidad[0]++;
                            list.get(j).setValue(cantidad);
                        } else if (castMember.getOcupation().find("actress")){
                            Integer[] cantidad = list.get(j).getValue();
                            cantidad[1]++;
                            list.get(j).setValue(cantidad);
                        }
                        yearExists = true;
                        break;
                    }
                }
                if (!yearExists){
                    Integer[] cantidad = new Integer[]{0, 0};;
                    if (castMember.getOcupation().find("actor")){
                        cantidad[0] = 1;
                    } else if (castMember.getOcupation().find("actress")){
                        cantidad[1] = 1;
                    }
                    SortNode<Integer, Integer[]> temp = new SortNode<>(year,cantidad);
                    list.add(temp);
                }
            //actors
                int yearActor = 0;
                int yearActress = 0;
                for (int i = 0; i < list.size(); i++){

                }


            }
        }

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
