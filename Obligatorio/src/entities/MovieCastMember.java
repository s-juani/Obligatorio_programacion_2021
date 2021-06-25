package entities;

import TADs.ClosedHash.ClosedHashTable;
import TADs.ClosedHash.HashTable;
import TADs.LinkedList.ListaEnlazada;
import TADs.LinkedList.interfaces.Lista;

public class MovieCastMember {
    private static HashTable<Integer, Lista<MovieCastMember>> castMemberIndex = new ClosedHashTable<>(595411,0.5f);
    private static HashTable<Integer, Lista<MovieCastMember>> movieIndex = new ClosedHashTable<>(171711,0.5f);

    public static HashTable<Integer, Lista<MovieCastMember>> getCastMemberIndex() {
        return castMemberIndex;
    }

    public static HashTable<Integer, Lista<MovieCastMember>> getMovieIndex() {
        return movieIndex;
    }

    private Movie movie; //new
    private Integer ordering;
    private CastMember castMember;  //new
    private String category;
    private String job;
    private String[] characters;

    public MovieCastMember(Movie movie, Integer ordering, CastMember castMember, String category, String job, String[] characters) {
        this.movie = movie;
        this.castMember = castMember;
        this.ordering = ordering;
        this.category = category;
        this.job = job;
        this.characters = characters;
    }

    public long longHashCode(){
        long temp = (long) movie.hashCode()*10000000L + (long) castMember.hashCode();
        if (temp >= 0) return temp;
        else return -temp;
    }

    public boolean equals (Object o){
        if (o == this){
            return true;
        }
        else if (!(o instanceof MovieCastMember)){
            return false;
        }
        MovieCastMember c = (MovieCastMember) o;
        return castMember.equals(c.getCastMember()) && movie.equals(c.getMovie());

    }

    public Integer getOrdering() {
        return ordering;
    }

    public String getCategory() {
        return category;
    }

    public String getJob() {
        return job;
    }

    public String[] getCharacters() {
        return characters;
    }

    public CastMember getCastMember() {
        return castMember;
    }

    public Movie getMovie() {
        return movie;
    }
}
