import TADs.ClosedHash.ClosedHashTable;
import TADs.ClosedHash.HashTable;
import TADs.ClosedHash.exceptions.KeyAlreadyExistsException;
import TADs.ClosedHash.exceptions.KeyNotExistsException;
import TADs.LinkedList.exceptions.EmptyQueueException;
import TADs.LinkedList.interfaces.Lista;
import TADs.arraylist.MyArrayList;
import TADs.arraylist.MyArrayListImpl;
import TADs.heap.exceptions.EmptyHeapException;
import TADs.heap.exceptions.HeapOverflowException;
import entities.CastMember;
import entities.Movie;
import entities.MovieCastMember;
import filereader.fileReader;
import org.junit.jupiter.api.Test;
import reportes.Reportes;


import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    public void causeOfDeathTest(){
        String row = ",cause1,, cause2 and, cause3";
        MyArrayList<String> causes = new MyArrayListImpl<>(5);
        for (String t : row.split(",")) {
            for ( String s : t.split("and")) {
                if (!s.equals(" ") && !s.equals("")) causes.add(s);
            }
        }
        String[] causesOfDeath = new String[causes.size()];
        for (int i=0; i<causes.size(); i++) {
            causesOfDeath[i]=causes.get(i);
        }
        for (String c : causesOfDeath) {
            System.out.println(c);
        }
        assertTrue(true);
    }

    @Test
    public void test1(){
        Long code = 8200742*10000000L + 8572003;
        System.out.println(code);
        System.out.println((code.hashCode()));
        code = -code;
        System.out.println(code);
        System.out.println((code.hashCode()));
        //82007428572003
    }

    @Test
    public void testReporte1() throws KeyNotExistsException, KeyAlreadyExistsException, HeapOverflowException, EmptyHeapException, EmptyQueueException {
        HashTable<Integer, CastMember> castMemberHash = fileReader.readCastMember(); //carga causeOfDeathHash y castMemberHash
        HashTable<Integer, Movie> movieHash = fileReader.readMovie();
        HashTable<Long, MovieCastMember> movieCastMemberHash = fileReader.readTitlePrincipals(movieHash,castMemberHash);
        fileReader.readMovieRating(movieHash);

        long startTime = System.nanoTime();
        Object[][] top5 = Reportes.reporte1(MovieCastMember.getCastMemberIndex());
        for (Object[] t : top5){
            CastMember member = (CastMember) t[1];
            int apariciones = (int) t[0];
            System.out.println("Nombre actor/actriz: " + member.getName());
            System.out.println("Cantidad de apariciones: " + apariciones + "\n");
        }
        long finishTime = System.nanoTime();
        finishTime -= startTime;
        finishTime /= 1000000;
        System.out.println("Tiempo de ejecución de la consulta: " + finishTime + " ms\n"); // tiempo de ejecucion
    }

    @Test
    public void testReporte2(){
        HashTable<Integer, CastMember> castMemberHash = fileReader.readCastMember(); //carga causeOfDeathHash y castMemberHash
        HashTable<Integer, Movie> movieHash = fileReader.readMovie();
        HashTable<Long, MovieCastMember> movieCastMemberHash = fileReader.readTitlePrincipals(movieHash,castMemberHash);
        fileReader.readMovieRating(movieHash);

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
    }

    @Test
    public void testReporte3(){
        HashTable<Integer, CastMember> castMemberHash = fileReader.readCastMember(); //carga causeOfDeathHash y castMemberHash
        HashTable<Integer, Movie> movieHash = fileReader.readMovie();
        HashTable<Long, MovieCastMember> movieCastMemberHash = fileReader.readTitlePrincipals(movieHash,castMemberHash);
        fileReader.readMovieRating(movieHash);

        long startTime = System.nanoTime();
        Object[][] resultado = Reportes.reporte3(Movie.getYearIndex(), MovieCastMember.getMovieIndex());
        for (int i = 0; resultado[i][0]!=null; i++){
            Movie movie = (Movie) resultado[i][1];
            float promedio = (float) resultado[i][0];
            System.out.println("Id película: " + movie.getImdbTitleId());
            System.out.println("Nombre: " + movie.getTitle());
            System.out.println("Altura promedio de actores:" + promedio + "\n");

        }

        long finishTime = System.nanoTime();
        finishTime -= startTime;
        finishTime /= 1000000;

        System.out.println("Tiempo de ejecución de la consulta: " + finishTime + "ms\n");
    }

    @Test
    public void testReporte4(){
        HashTable<Integer, CastMember> castMemberHash = fileReader.readCastMember(); //carga causeOfDeathHash y castMemberHash
        HashTable<Integer, Movie> movieHash = fileReader.readMovie();
        HashTable<Long, MovieCastMember> movieCastMemberHash = fileReader.readTitlePrincipals(movieHash,castMemberHash);
        fileReader.readMovieRating(movieHash);

        long startTime = System.nanoTime();
        Object[][] resultado = Reportes.reporte4(castMemberHash);
        System.out.println("Actores: ");
        System.out.println("        Año: " + resultado[0][0]);
        System.out.println("        Cantidad: " + resultado[0][1] + "\n");
        System.out.println("Actrices: ");
        System.out.println("        Año: " + resultado[1][0]);
        System.out.println("        Cantidad: " + resultado[1][1] + "\n");

        long finishTime = System.nanoTime();
        finishTime -= startTime;
        finishTime /= 1000000;

        System.out.println("Tiempo de ejecución de la consulta: " + finishTime + "ms\n");
    }

    @Test
    public void testReporte5(){
        HashTable<Integer, CastMember> castMemberHash = fileReader.readCastMember(); //carga causeOfDeathHash y castMemberHash
        HashTable<Integer, Movie> movieHash = fileReader.readMovie();
        HashTable<Long, MovieCastMember> movieCastMemberHash = fileReader.readTitlePrincipals(movieHash,castMemberHash);
        fileReader.readMovieRating(movieHash);

        long startTime = System.nanoTime();

        String[][] top10 = Reportes.reporte5(MovieCastMember.getMovieIndex());
        for (String[] t : top10){
            System.out.println("Genero pelicula: " + t[1]);
            System.out.println("Cantidad: " + t[0] + "\n");
        }
        long finishTime = System.nanoTime();
        finishTime -= startTime;
        finishTime /= 1000000;

        System.out.println("Tiempo de ejecución de la consulta: " + finishTime + "ms\n");
    }

}