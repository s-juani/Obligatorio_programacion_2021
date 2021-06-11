package entities;

import TADs.arraylist.MyArrayList;
import TADs.arraylist.MyArrayListImpl;

import java.util.Date;

public class Movie {

   private String imdbTitleId;
   private String title;
   private String originalTitle;
   private Integer Year;
   private Date datePublished;
   private MyArrayList<String> genre = new MyArrayListImpl<>(5);
   private Integer duration;
   private MyArrayList<String> country = new MyArrayListImpl<>(5);
   private String language;
   private MyArrayList<String> writer = new MyArrayListImpl<>(5);
   private String productionCompany;
   private MyArrayList<String> actors = new MyArrayListImpl<>(5);
   private String description;
   private Float avgVote;
   private Integer votes;
   private String budget;
   private String usaGrossIncome;
   private Float metaScore;
   private Float reviewsFromUsers;
   private Float reviewsFromCritics;


}
