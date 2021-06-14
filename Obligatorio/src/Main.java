
import TADs.hash.MyClosedHashImpl;
import TADs.hash.MyHash;
import entities.*;
import filereader.*;

public class Main{

    public static MyHash<Integer, CastMember> castMemberHash;
    public static MyHash<Integer, Movie> movieHash;
    public static MyHash<Integer, MovieCastMember> movieCastMemberHash;


    public static MyHash<Integer, Rating> ratingHash;
    public static MyHash<Integer, MovieRating> movieRatingHash = new MyClosedHashImpl<>(); //revisar si usar otra  TAD


    //especificar tamaño de las tablas en los constructores

    public void cargaDatos() {

        castMemberHash = fileReader.readCastMember(); //carga causeOfDeathHash y castMemberHash
        movieHash = fileReader.readMovie();  //carga movieHash y ratingHash

        fileReader.readMovieRating(movieHash);



        //fileReader.readMovieRating(movieRatingHash);
        //fileReader.readMovieCastMember(movieCastMemberHash);

    }





}