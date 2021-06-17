
import TADs.ClosedHash.ClosedHashTable;
import TADs.ClosedHash.HashTable;
import TADs.DoubleHash.DoubleHashTable;
import TADs.hash.MyClosedHashImpl;
import TADs.hash.MyHash;
import entities.*;
import filereader.*;

public class Main{

    public static HashTable<Integer, CastMember> castMemberHash;
    public static HashTable<Integer, Movie> movieHash;
    public static DoubleHashTable<Integer, Integer, MovieCastMember> movieCastMemberHash;


    public static HashTable<Integer, Rating> ratingHash;
    public static HashTable<Integer, MovieRating> movieRatingHash = new ClosedHashTable<>(); //revisar si usar otra  TAD


    //especificar tamaño de las tablas en los constructores

    public void cargaDatos() {

        castMemberHash = fileReader.readCastMember(); //carga causeOfDeathHash y castMemberHash
        movieHash = fileReader.readMovie();  //carga movieHash y ratingHash
        movieCastMemberHash = fileReader.readTitlePrincipals(movieHash,castMemberHash);

        fileReader.readMovieRating(movieHash);



        //fileReader.readMovieRating(movieRatingHash);


    }





}