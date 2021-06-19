package filereader;

import TADs.ClosedHash.ClosedHashTable;
import TADs.ClosedHash.HashTable;
import TADs.ClosedHash.exceptions.KeyNotExistsException;
import TADs.DoubleHash.DoubleHashTable;
import TADs.hash.MyClosedHashImpl;
import TADs.hash.MyHash;
import entities.CastMember;
import entities.CauseOfDeath;
import entities.Movie;
import entities.MovieCastMember;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class fileReaderTest {

    @Test
    public void testFlujoNormal(){
        fileReader.readCastMember();
        fileReader.readMovie();
    }

    @Test
    public void testCastMember() throws KeyNotExistsException {
        HashTable<Integer, CastMember> castMemberHash = fileReader.readCastMember();
        //HashTable<Integer, CauseOfDeath> causeOfDeathHash = new ClosedHashTable<>();
        System.out.println(castMemberHash.get(1).getImdbNameId());
        System.out.println(castMemberHash.get(1).getChildren());
        System.out.println(castMemberHash.size());
        assertTrue(true);
    }

    @Test
    public void testMovie() throws KeyNotExistsException {
        HashTable<Integer, Movie> castMovie = fileReader.readMovie();
        //HashTable<Integer, CauseOfDeath> causeOfDeathHash = new ClosedHashTable<>();
        System.out.println(castMovie.get(9).getImdbTitleId());
        System.out.println(Arrays.toString(castMovie.get(9).getCountry()));
        System.out.println(castMovie.size());
        assertTrue(true);
    }

    @Test
    public void testMovieCastMember(){
        HashTable<Integer, CastMember> castMemberHash = fileReader.readCastMember();
        HashTable<Integer, Movie> castMovie = fileReader.readMovie();
        DoubleHashTable<Integer, Integer, MovieCastMember> movieCastMemberHash = fileReader.readTitlePrincipals(castMovie,castMemberHash);


    }


    @Test
    public void test2(){
        Integer[][] prueba = new Integer[35000][35000];


    }
}
