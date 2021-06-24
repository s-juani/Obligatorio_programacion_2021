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
        HashTable<Integer, CastMember> castMemberHash;
        HashTable<Integer, Movie> movieHash;
        HashTable<Integer, Lista<Movie>> movieYearIndex;
        HashTable<Long, MovieCastMember> movieCastMemberHash;
        HashTable<Integer, Lista<MovieCastMember>> castMemberIndex = new ClosedHashTable<>(595411,0.5f);
        HashTable<Integer, Lista<MovieCastMember>> movieIndex;

        castMemberHash = fileReader.readCastMember(); //carga causeOfDeathHash y castMemberHash
        movieHash = fileReader.readMovie();  //carga movieHash y ratingHash
        movieCastMemberHash = fileReader.readTitlePrincipals(movieHash,castMemberHash,castMemberIndex);
        fileReader.readMovieRating(movieHash);

        Object[][] top5 = Reportes.reporte1(castMemberIndex);
        for (Object[] t : top5){
            CastMember member = (CastMember) t[1];
            int apariciones = (int) t[0];
            System.out.println("Nombre actor/actriz: " + member.getName());
            System.out.println("Cantidad de apariciones: " + apariciones + "\n");
        }
        System.out.println("Tiempo de ejecución de la consulta: " + "\n"); // tiempo de ejecucion
    }

    @Test
    public void testReporte2(){
        HashTable<Integer, CastMember> castMemberHash;
        HashTable<Integer, Movie> movieHash;
        HashTable<Integer, Lista<Movie>> movieYearIndex;
        HashTable<Long, MovieCastMember> movieCastMemberHash;
        HashTable<Integer, Lista<MovieCastMember>> castMemberIndex = new ClosedHashTable<>(595411, 0.5f);
        HashTable<Integer, Lista<MovieCastMember>> movieIndex;

        castMemberHash = fileReader.readCastMember(); //carga causeOfDeathHash y castMemberHash
        movieHash = fileReader.readMovie();  //carga movieHash y ratingHash
        movieCastMemberHash = fileReader.readTitlePrincipals(movieHash, castMemberHash, castMemberIndex);
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
}