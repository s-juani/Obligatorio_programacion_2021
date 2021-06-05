
import TADs.hash.MyClosedHashImpl;
import TADs.hash.MyHash;
import entities.*;
import filereader.*;

public class Main{
    public static MyHash<Integer, CastMember> castMemberHash = new MyClosedHashImpl<>();
    public static MyHash<Integer, Movie> movieHash = new MyClosedHashImpl<>();
    public static MyHash<Integer, Rating> ratingHash = new MyClosedHashImpl<>();
    public static MyHash<Integer, CauseOfDeath> causeOfDeathHash = new MyClosedHashImpl<>();

    public static MyHash<Integer, MovieRating> movieRatingHash = new MyClosedHashImpl<>(); //revisar si usar otra  TAD
    public static MyHash<Integer, MovieCastMember> movieCastMemberHash = new MyClosedHashImpl<>(); //revisar si usar otra  TAD

    //especificar tamaño de las tablas en los constructores

    public void cargaDatos() {
        //fileReader.readCastMember(castMemberHash, causeOfDeathHash); //carga causeOfDeathHash y castMemberHash
        //fileReader.readMovie(movieHash);  //carga movieHash
        //fileReader.readRating(ratingHash);   //carga ratingHash

        //fileReader.readMovieRating(movieRatingHash);
        //fileReader.readMovieCastMember(movieCastMemberHash);

    }


}