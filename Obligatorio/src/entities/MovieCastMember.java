package entities;

public class MovieCastMember {

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
