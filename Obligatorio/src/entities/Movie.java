package entities;

import TADs.arraylist.MyArrayList;
import TADs.arraylist.MyArrayListImpl;

import java.util.Date;

public class Movie {

   private String imdbTitleId;
   private String title;
   private String originalTitle;
   private Integer year;
   private Date datePublished;
   private String[] genre;
   private Integer duration;
   private String[] country;
   private String language;
   private String[] director;
   private String[] writer;
   private String productionCompany;
   private String[] actors;
   //private MyArrayList<String> actors = new MyArrayListImpl<>(5);
   private String description;
   private Float avgVote;
   private Integer votes;
   private String budget;
   private String usaGrossIncome;
   private String worlwideGrossIncome;
   private Float metaScore;
   private Float reviewsFromUsers;
   private Float reviewsFromCritics;
   private MovieRating rating;

   public Movie(String imdbTitleId, String title, String originalTitle, Integer year, Date datePublished, String[] genre, Integer duration, String[] country, String language, String[] director, String[] writer, String productionCompany, String[] actors, String description, Float avgVote, Integer votes, String budget, String usaGrossIncome, String worlwideGrossIncome, Float metaScore, Float reviewsFromUsers, Float reviewsFromCritics) {
      this.imdbTitleId = imdbTitleId;
      this.title = title;
      this.originalTitle = originalTitle;
      this.year = year;
      this.datePublished = datePublished;
      this.genre = genre;
      this.duration = duration;
      this.country = country;
      this.language = language;
      this.director = director;
      this.writer = writer;
      this.productionCompany = productionCompany;
      this.actors = actors;
      this.description = description;
      this.avgVote = avgVote;
      this.votes = votes;
      this.budget = budget;
      this.usaGrossIncome = usaGrossIncome;
      this.worlwideGrossIncome = worlwideGrossIncome;
      this.metaScore = metaScore;
      this.reviewsFromUsers = reviewsFromUsers;
      this.reviewsFromCritics = reviewsFromCritics;
      this.rating = null;
   }

   public MovieRating getRating() {
      return rating;
   }

   public void setRating(MovieRating rating) {
      this.rating = rating;
   }

   public boolean equals(Object o){
      if (o == this){
         return true;
      }
      else if (!(o instanceof Movie)){
         return false;
      }
      Movie m = (Movie) o;
      return m.getImdbTitleId().equals(this.imdbTitleId);
   }

   public int hashCode(){
      return Integer.parseInt(imdbTitleId.substring(2));
   }

   public String getImdbTitleId() {
      return imdbTitleId;
   }

   public String getTitle() {
      return title;
   }

   public String getOriginalTitle() {
      return originalTitle;
   }

   public Integer getYear() {
      return year;
   }

   public Date getDatePublished() {
      return datePublished;
   }

   public String[] getGenre() {
      return genre;
   }

   public Integer getDuration() {
      return duration;
   }

   public String[] getCountry() {
      return country;
   }

   public String getLanguage() {
      return language;
   }

   public String[] getDirector() {
      return director;
   }

   public String[] getWriter() {
      return writer;
   }

   public String getProductionCompany() {
      return productionCompany;
   }

   public String[] getActors() {
      return actors;
   }

   public String getDescription() {
      return description;
   }

   public Float getAvgVote() {
      return avgVote;
   }

   public Integer getVotes() {
      return votes;
   }

   public String getBudget() {
      return budget;
   }

   public String getUsaGrossIncome() {
      return usaGrossIncome;
   }

   public String getWorlwideGrossIncome() {
      return worlwideGrossIncome;
   }

   public Float getMetaScore() {
      return metaScore;
   }

   public Float getReviewsFromUsers() {
      return reviewsFromUsers;
   }

   public Float getReviewsFromCritics() {
      return reviewsFromCritics;
   }
}
