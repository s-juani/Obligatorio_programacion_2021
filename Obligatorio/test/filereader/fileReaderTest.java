package filereader;

import TADs.ClosedHash.ClosedHashTable;
import TADs.ClosedHash.HashTable;
import TADs.ClosedHash.exceptions.*;
import TADs.LinkedList.exceptions.EmptyQueueException;
import TADs.LinkedList.interfaces.Lista;
import TADs.heap.exceptions.EmptyHeapException;
import TADs.heap.exceptions.HeapOverflowException;
import com.sun.tools.javac.Main;
import entities.CastMember;
import entities.CauseOfDeath;
import entities.Movie;
import entities.MovieCastMember;
import org.junit.jupiter.api.Test;
import reportes.Reportes;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class fileReaderTest {

    @Test
    public void testFlujoNormal(){
        HashTable<Integer, CastMember> castMemberHash = fileReader.readCastMember();
        HashTable<Integer, Movie> movieHash = fileReader.readMovie();
        HashTable<Integer, Lista<MovieCastMember>> castMemberIndex = new ClosedHashTable<>(59,0.5f);
        fileReader.readMovieRating(movieHash);
        fileReader.readTitlePrincipals(movieHash,castMemberHash, castMemberIndex);
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
    public void testMovieCastMember() throws HeapOverflowException, EmptyHeapException, KeyNotExistsException, EmptyQueueException, KeyAlreadyExistsException {
        HashTable<Integer, CastMember> castMemberHash = fileReader.readCastMember();
        HashTable<Integer, Movie> movieHash = fileReader.readMovie();
        HashTable<Integer, Lista<MovieCastMember>> castMemberIndex = new ClosedHashTable<>(59,0.5f);
        HashTable<Long, MovieCastMember> movieCastMemberHash = fileReader.readTitlePrincipals(movieHash,castMemberHash,castMemberIndex);
        //Reportes.showReporte1(castMemberIndex);



    }


}
