package reportes;

import TADs.ClosedHash.ClosedHashTable;
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
import entities.*;
import reportes.sort.Sort;
import reportes.sort.SortNode;


public abstract class Reportes {

    // top 5 de actores/actrices segun cantidad de apariciones en peliculas
    public static Object[][] reporte1(HashTable<Integer,Lista<MovieCastMember>> castMemberIndex) throws HeapOverflowException, EmptyHeapException, EmptyQueueException {
        Object[][] top5 = new Object[5][2];
        Heap<Integer, CastMember> topHeap = new HeapImpl<>(5,"min"); //heap minimo, de tamanio 5 para obtener el top 5

        Lista<MovieCastMember> movieList;
        CastMember castMember;
        MovieCastMember movieCastMember;
        castMemberIndex.iteratorReset();
        int apariciones;
        // se itera por castMember y la lista de MovieCastMember asociados (peliculas en las que participo)
        // en el indice por castMember de las instancias de MovieCastMember
        while ((movieList = castMemberIndex.iteratorNext()) != null) {
            apariciones = 0;
            movieList.iteratorReset();
            movieCastMember = movieList.iteratorNext();
            castMember = movieCastMember.getCastMember();
            while ((movieCastMember) != null) { //se verifica si la participacion es como actor o actress y se van contando
                if (movieCastMember.getCategory().equals("actor") || movieCastMember.getCategory().equals("actress")) {
                    apariciones++;
                }
                movieCastMember = movieList.iteratorNext();
            }
            if (apariciones != 0) {
                if (topHeap.size() == 5) {  // se sustituye en el tope del heap si las apariciones son mayores que el minimo
                    if (((Integer) topHeap.top()[0]) < apariciones) {
                        topHeap.delete();
                        topHeap.add(apariciones, castMember);
                    }
                } else {
                    topHeap.add(apariciones, castMember); //se llena el heap con los primeros 5
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
        //se itera en el hash de castMember
        while ((castMember = castMemberHash.iteratorNext())!=null){
            country = castMember.getBirthCountry();
            //se filtra por pais de nacimiento y ocupation
            if (country.contains("USA") || country.contains("UK") || country.contains("France") || country.contains("Italy")) {
                if (castMember.getOcupation().find("producer") || castMember.getOcupation().find("director")){

                    //agregar causa de muerte al arrayList o incrementar su cantidad si ya estaba
                    boolean hasCauseOfDeath = false;
                    for (int j=0; j<list.size(); j++){
                        if (list.get(j).getValue().equals(castMember.getReasonOfDeath())){
                            list.get(j).setKey(list.get(j).getKey() + 1);
                            hasCauseOfDeath = true;
                            break;
                        }
                    }
                    if (!hasCauseOfDeath && !castMember.getReasonOfDeath().getName().isEmpty()){
                        SortNode<Integer,CauseOfDeath> temp = new SortNode<>(1, castMember.getReasonOfDeath());
                        list.add(temp);
                    }
                }
            }
        }
        Sort<Integer,CauseOfDeath> sort = new Sort<>();
        //sort.quickSort(list, 0, list.size()-1);
        sort.HeapSort(list);
        int j=0;
        for (int i=list.size()-1; i>list.size()-6; i--){
            SortNode<Integer, CauseOfDeath> temp = list.get(i);
            top5[j][0] = String.valueOf(temp.getKey());
            top5[j][1] = temp.getValue().getName();
            j++;
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
        Heap<Float, Movie> topHeap = new HeapImpl<>(14,"min"); //heap para almacenar las 14 peliculas con mas wheightedAverage

        // extraer las 14 peliculas de 1950-1960 con mas weightedAverage
        for (int year = 1950; year<=1960; year++){
            try {
                Lista<Movie> movieList = yearMovieIndex.get(year); //peliculas del a??o 'year'
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

        // en cada pelicula obtener los actores y calcular su promedio de altura si no es null
        int i = 0;
        while (topHeap.size()!=0){
            try {
                Movie movie = (Movie) topHeap.remove()[1]; //se van obteniendo las peliculas
                float promedio = 0.0f;
                Integer altura;
                int cantidad = 0;

                Lista<MovieCastMember> actorsList = movieCastMemberIndex.get(movie.hashCode());
                actorsList.iteratorReset();
                MovieCastMember movieCastMember;
                while ((movieCastMember = actorsList.iteratorNext()) != null){ //se itera entre los participantes de la pelicula
                    //se filtra por category actor o actress, y por altura no nula
                    if (movieCastMember.getCategory().equals("actor") || movieCastMember.getCategory().equals("actress")){
                        altura = movieCastMember.getCastMember().getHeight();
                        if (altura != null){
                            promedio += (float) altura;   //suma de las alturas no nulas
                            cantidad++;                    //cantidad de alturas no nulas
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


    // a??o mas frecuente de nacimiento de actores y actrices
    public static Object[][] reporte4(HashTable<Integer, CastMember> castMemberHash){
        Object[][] resultado = new Object[2][2];
        //sortNode = key: year , value: [cantidad actores, cantidad actrices]
        MyArrayList<SortNode<Integer, Integer[]>> list = new MyArrayListImpl<>(200);

        //se itera en el hash de CastMember
        castMemberHash.iteratorReset();
        CastMember castMember;
        while ((castMember = castMemberHash.iteratorNext()) != null){
            if (castMember.getBirthYear()!=null){
                Integer year = castMember.getBirthYear();
                boolean yearExists = false;
                for (int j=0; j<list.size(); j++){
                    if (list.get(j).getKey().equals(year)){
                        if (castMember.getOcupation().find("actor")){
                            Integer[] cantidad = list.get(j).getValue();
                            cantidad[0]++; //se incrementa la cantidad de nacimientos de actores
                            list.get(j).setValue(cantidad);
                        }
                        if (castMember.getOcupation().find("actress")){
                            Integer[] cantidad = list.get(j).getValue();
                            cantidad[1]++; //se incrementa la cantidad de nacimientos de actrices
                            list.get(j).setValue(cantidad);
                        }
                        yearExists = true;
                        break;
                    }
                }
                if (!yearExists){
                    Integer[] cantidad = new Integer[]{0, 0};
                    if (castMember.getOcupation().find("actor")){
                        cantidad[0] = 1;
                    }
                    if (castMember.getOcupation().find("actress")){
                        cantidad[1] = 1;
                    }
                    SortNode<Integer, Integer[]> temp = new SortNode<>(year,cantidad);
                    list.add(temp);
                }
            }
        }

        SortNode<Integer, Integer[]> yearActor = list.get(0);
        SortNode<Integer, Integer[]> yearActress = list.get(0);
        for (int i = 1; i < list.size(); i++){
            SortNode<Integer, Integer[]> tempYear = list.get(i);
            if (tempYear.getValue()[0].compareTo(yearActor.getValue()[0])>0){
                yearActor = tempYear;
            }
            if (tempYear.getValue()[1].compareTo(yearActress.getValue()[1])>0){
                yearActress = tempYear;
            }
        }

        resultado[0] = new Object[]{yearActor.getKey(), yearActor.getValue()[0]};
        resultado[1] = new Object[]{yearActress.getKey(), yearActress.getValue()[1]};

        return resultado;
    }


    // top 10 de generos mas comunes de peliculas en las cuales haya participado algun actor/actriz con 2 o mas hijos
    public static String[][] reporte5(HashTable<Integer, Lista<MovieCastMember>> movieIndex){
        // iterar por pelicula y filtrar segun tenga algun actor con 2 o mas hijos (getChildren()>=2)
        // en la iteracion anterior ir agregando a una lista los generos y llevar la cuenta de la cantidad
        // filtrar los 10 generos mas comunes
        String[][] resultado = new String[10][2];
        MyArrayList<SortNode<Integer, Genre>> list = new MyArrayListImpl<>(200);

        Lista<MovieCastMember> movieCastMemberList;
        Movie movie;
        CastMember actor;
        movieIndex.iteratorReset();
        boolean hasActors;


        while ((movieCastMemberList = movieIndex.iteratorNext()) != null){
            hasActors = false;
            movie = movieCastMemberList.get(0).getMovie();

            for (int p = 0; p<movieCastMemberList.size(); p++){
                actor = movieCastMemberList.get(p).getCastMember();
                if (actor.getOcupation().find("actor") || actor.getOcupation().find("actress")){
                    if (actor.getChildren()>=2) {
                        hasActors = true;
                        break;
                    }
                }
            }
            if (hasActors){
                HashTable<String,Genre> genreWentThrough = new ClosedHashTable<String,Genre>(20,0.7f);
                for (Genre genre : movie.getGenre()){
                    SortNode<Integer, Genre> newNode = new SortNode<>(1,genre);
                    boolean exists = false;
                    for (int j = 0; j< list.size(); j++){
                        if (list.get(j).equals(newNode)){
                            list.get(j).setKey(list.get(j).getKey()+1);
                            exists = true;
                            break;
                        }
                    }
                    if (!exists){
                        list.add(newNode);
                    }
                }
            }
        }

        Sort<Integer, Genre> sort = new Sort<>();
        sort.HeapSort(list);

        int j=0;
        for (int i=list.size()-1; i>list.size()-11; i--){
            SortNode<Integer, Genre> temp = list.get(i);
            resultado[j][0] = String.valueOf(temp.getKey());
            resultado[j][1] = temp.getValue().getName();
            j++;
        }

        return resultado;
    }
}
