package entities;

import TADs.arraylist.MyArrayList;
import TADs.arraylist.MyArrayListImpl;

import java.util.Date;

public class Movie {

   private String imdbTitleId;
   private String title;
   private String originalTitle;
   private int Year;
   private Date datePublished;
   private MyArrayList<String> genre = new MyArrayListImpl<>(5);
   private int duration;
   private MyArrayList<String> country = new MyArrayListImpl<>(5);
   private String language;
   private MyArrayList<String> writer = new MyArrayListImpl<>(5);
   private String productionCompany;
   private MyArrayList<String> actors = new MyArrayListImpl<>(5);
   private String description;
   private float avgVote;
   private int votes;
   private String budget;
   private String usaGrossIncome;
   private float metaScore;
   private float reviewsFromUsers;
   private float reviewsFromCritics;


}
