package filereader;

import TADs.ClosedHash.ClosedHashTable;
import TADs.ClosedHash.HashTable;
import TADs.ClosedHash.exceptions.*;
import TADs.DoubleHash.DoubleHashTable;
import TADs.DoubleHash.DoubleHashTableImpl;
import TADs.arraylist.MyArrayList;
import TADs.arraylist.MyArrayListImpl;
import TADs.hash.*;
import entities.*;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Esto es total y completamente en modalidad de prueba, esta clase no es definitiva y
 * puede ser eliminada en un futuro. No trabajo sobre un archivo aparte porque preciso
 * testear con entities ya existentes.
 *      Santiago - 03/06 - 16:25
 *
 * Esta completa toda la parte de extraer los archivos y almacenarlos en un String[], lo proximo a
 * hacer es implementar que los datos se guarden en entities y almacenar dichos entities en el tipo
 * de dato seleccionado.
 *      Santiago - 03/06 - 23:14
 *
 */

public abstract class fileReader {


    public static HashTable<Integer, CastMember> readCastMember(){
        BufferedReader reader = null;
        String line;
        String[] row = new String[17];
        int column;
        final String castPath = "dataset\\IMDb names2.csv";

        HashTable<Integer,CastMember> hashToReturn = new ClosedHashTable<>(496187, 0.75f);


        try{
            reader = new BufferedReader(new FileReader(castPath));
            line = reader.readLine();
            column = 0;
            int current;
            int start;
            boolean inQuotes = false;

            while ((line = reader.readLine()) != null){
                start = 0;
                for (current = 0; current < line.length(); current++){
                    if (line.charAt(current) == '\"') inQuotes = !inQuotes;
                    else if (line.charAt(current) == ',' && !inQuotes){
                        row[column] = line.substring(start,current);
                        column++;
                        start = current+1;
                    }
                }

                // ACA ESTA EL OUTPUT
                // START
                if (column == row.length-1){
                    row[column] = line.substring(start, current);


                    //--------------------
                    // int casts
                    Integer height = null;
                    if (!row[3].equals("")) height = Integer.parseInt(row[3]);
                    Integer spouses = null;
                    if (!row[13].equals("")) spouses = Integer.parseInt(row[13]);
                    Integer divorces = null;
                    if (!row[14].equals("")) divorces = Integer.parseInt(row[14]);
                    Integer spousesWithChildren = null;
                    if (!row[15].equals("")) spousesWithChildren = Integer.parseInt(row[15]);
                    Integer children = null;
                    if (!row[16].equals("")) children = Integer.parseInt(row[16]);

                    //birth_location a lista
                    String[] birth = new String[3];
                    if (!row[7].equals("")){
                        row[7] = row[7].substring(1,row[7].length()-1);
                        String[] birthArray = row[7].split(",");
                        if (birthArray.length>3) {
                            birth[0]=birthArray[0]; //city
                            birth[1]=birthArray[1]; //state
                            for (int i=2; i<birthArray.length; i++) birth[2]=birth[2]+birthArray[i]; //country
                        } else if (birthArray.length==3) birth=birthArray;
                        else if (birthArray.length==2){
                            birth[0]=birthArray[0]; //city
                            birth[2]=birthArray[1]; //country
                        } else if (birthArray.length==1) birth[2]=birthArray[0]; //country
                    }

                    //death_location a lista
                    String[] death = new String[3];
                    if (!row[10].equals("")){
                        row[10] = row[10].substring(1,row[10].length()-1);
                        String[] deathArray = row[10].split(",");
                        if (deathArray.length>=3) {
                            death[0] = deathArray[0]; //city
                            death[1] = deathArray[1]; //state
                            for (int i = 2; i < deathArray.length; i++) death[2] = death[2] + deathArray[i]; //countr
                        } else if (deathArray.length==3) death=deathArray;
                        else if (deathArray.length==2){
                            death[0]=deathArray[0]; //city
                            death[2]=deathArray[1]; //country
                        } else if (deathArray.length==0) death[2]=deathArray[0]; //country
                    }

                    //cause of death -> to list
                    MyArrayList<String> causes = new MyArrayListImpl<>(5);
                    for (String t : row[11].split(",")) {
                        for (String s : t.split("and")) {
                            if (!s.equals(" ") && !s.equals("")) causes.add(s);
                        }
                    }
                    String[] causesOfDeath = new String[causes.size()];
                    for (int i=0; i<causes.size(); i++) {
                        causesOfDeath[i]=causes.get(i);
                    }

                    //birthDate -> adapt to java.date
                    Date birthDate = null;  // row[6]
                    if (!row[6].equals("")){
                        try{
                            birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(row[6]);
                        } catch (Exception e){
                            String year = "";
                            for (int i=0; i<row[6].length(); i++){
                                if (Character.isDigit(row[6].charAt(i))){
                                    year += row[6].charAt(i);
                                }
                            }
                            try{
                                birthDate = new SimpleDateFormat("yyyy").parse(year);
                            } catch (Exception f){
                                f.printStackTrace();
                            }
                        }
                    }

                    //deathDate -> adapt to java.date
                    Date deathDate = null; //row[9]
                    if (!row[6].equals("")){
                        try{
                            deathDate = new SimpleDateFormat("yyyy-MM-dd").parse(row[9]);
                        } catch (ParseException e){
                            //ignore
                        }
                    }

                    CastMember memberToAdd = new CastMember(row[0], row[1], row[2], height, row[4],
                                                            birthDate,      // row[6] buscar como castear a Date
                                                            birth[0],       //row[7] array con birth city, state, country
                                                            birth[1],
                                                            birth[2],
                                                            deathDate,      //row[9] buscar como castear a Date
                                                            death[0],       //row[10] array con death city, state, country
                                                            death[1],
                                                            death[2],
                                                            causesOfDeath,
                                                            row[12],
                                                            spouses,
                                                            divorces,
                                                            spousesWithChildren,
                                                            children);

                    /**
                     *                             row[0],
                     *                             row[1],
                     *                             row[2],
                     *                             height,
                     *                             row[4],
                     *                             null, // row[6] buscar como castear a Date
                     *                             birth[0], //row[7] array con birth state, country, city
                     *                             birth[1],
                     *                             birth[2],
                     *                             null, // row[9] buscar como castear a Date
                     *                             death[0], //row[10] array con death state, country, city
                     *                             death[1],
                     *                             death[2],
                     *                             causesOfDeath, //row[11] new CauseOfDeath (name String)
                     *                             row[12],
                     *                             spouses,
                     *                             divorces,
                     *                             spousesWithChildren,
                     *                             children);
                     */

                    //for (int i = 0; i < row.length; i++){
                    //    System.out.printf("%-40s","---"+row[i]);
                    //}
                    //System.out.println(" ");
                    //------------

                    hashToReturn.put(memberToAdd.getImdbNameId().hashCode(),memberToAdd);

                    column = 0;
                    inQuotes = false;
                }
                // FINISH
            }
        } catch (IOException | KeyAlreadyExistsException | KeyNotExistsException e) {
            e.printStackTrace();
        } finally {
            try{
                assert reader != null;
                reader.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return hashToReturn;
    }

    public static HashTable<Integer, Movie> readMovie(){
        BufferedReader reader = null;
        String line;
        String[] row = new String[22];
        int column;
        final String castPath = "dataset\\IMDb movies2.csv";

        HashTable<Integer,Movie> hashToReturn = new ClosedHashTable<>(496187, 0.75f); //ajustar tamanio

        try{
            reader = new BufferedReader(new FileReader(castPath));
            line = reader.readLine(); //saltear cabezales de columna
            column = 0;
            int current;
            int start;
            boolean inQuotes = false;

            while ((line = reader.readLine()) != null){
                start = 0;
                for (current = 0; current < line.length(); current++){
                    if (line.charAt(current) == '\"') inQuotes = !inQuotes;
                    else if (line.charAt(current) == ',' && !inQuotes){
                        row[column] = line.substring(start,current);
                        column++;
                        start = current+1;
                    }
                }
                // Integer casts: year, duration, votes
                Integer year = null;
                if (!row[3].equals("")) year = Integer.parseInt(row[3]);
                Integer duration = null;
                if (!row[6].equals("")) duration = Integer.parseInt(row[6]);
                Integer votes = null;
                if (!row[15].equals("")) votes = Integer.parseInt(row[15]);

                // Date casts: datePublished //FIXME
                Date datePublished = null;
                if (!row[4].equals("")){
                    try{
                        datePublished = new SimpleDateFormat("yyyy-MM-dd").parse(row[4]);
                    } catch (ParseException e){
                        //ignore
                    }
                }

                // String[] casts: genre, country, director, writer, actors
                String[] genre = row[5].split(",");
                String[] country = row[7].split(",");
                String[] director = row[9].split(",");
                String[] writer = row[10].split(",");
                String[] actors = row[12].split(",");

                // Float casts: avgVote, metaScore, reviewsFromUsers, reviewsFromCritics
                Float avgVote = null;
                if (row[14] != null) {if (!row[14].equals("")) avgVote = Float.parseFloat(row[14]);};

                Float metaScore = null;
                if (row[19] != null) {if (!row[19].equals("")) metaScore = Float.parseFloat(row[19]);};
                Float reviewsFromUsers = null;
                if (row[20] != null) {if (!row[20].equals("")) reviewsFromUsers = Float.parseFloat(row[20]);};
                Float reviewsFromCritics = null;
                if (row[21] != null) {if (!row[21].equals("")) reviewsFromCritics = Float.parseFloat(row[21]);};

                // ACA ESTA EL OUTPUT
                // START
                if (column == row.length-1){
                    row[column] = line.substring(start, current);

                    Movie movieToAdd = new Movie(
                            row[0],
                            row[1],
                            row[2],
                            year,
                            datePublished,
                            genre,
                            duration,
                            country,
                            row[8],
                            director,
                            writer,
                            row[11],
                            actors,
                            row[13],
                            avgVote,
                            votes,
                            row[16],
                            row[17],
                            row[18],
                            metaScore,
                            reviewsFromUsers,
                            reviewsFromCritics
                    );

                    /**
                     *    row[0] String imdbTitleId,
                     *    row[1] String title,
                     *    row[2] String originalTitle,
                     *    row[3] Integer year,
                     *    row[4] Date datePublished,
                     *    row[5] String[] genre,
                     *    row[6] Integer duration,
                     *    row[7] String[] country,
                     *    row[8] String language,
                     *    row[9] String[] director,
                     *    row[10] String[] writer,
                     *    row[11] String productionCompany,
                     *    row[12] String[] actors,
                     *    row[13] String description,
                     *    row[14] Float avgVote,
                     *    row[15] Integer votes,
                     *    row[16] String budget,
                     *    row[17] String usaGrossIncome,
                     *    row[18] String worlwideGrossIncome,
                     *    row[19] Float metascore,
                     *    row[20] Float reviewsFromUsers,
                     *    row[21] Float reviewsFromCritics
                     */

                    //for (int i = 0; i < row.length; i++){
                    //    System.out.printf("%-40s","---"+row[i]);
                    //}
                    //System.out.println(" ");
                    //------------

                    hashToReturn.put(movieToAdd.getImdbTitleId().hashCode(),movieToAdd);

                    column = 0;
                    inQuotes = false;
                }
                // FINISH
            }
        } catch (IOException | KeyAlreadyExistsException e) {
            e.printStackTrace();
        } finally {
            try{
                assert reader != null;
                reader.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return hashToReturn;

    }


    public static void readMovieRating(HashTable<Integer, Movie> movieHash){
        BufferedReader reader = null;
        String line;
        String[] row = new String[49];
        int column;
        final String castPath = "dataset\\IMDb ratings2.csv";  /**"dataset\\IMDb ratings.csv" **/

        try{
            reader = new BufferedReader(new FileReader(castPath));
            line = reader.readLine();
            column = 0;
            int current;
            int start;
            boolean inQuotes = false;

            while ((line = reader.readLine()) != null){
                start = 0;
                for (current = 0; current < line.length(); current++){
                    if (line.charAt(current) == '\"') inQuotes = !inQuotes;
                    else if (line.charAt(current) == ',' && !inQuotes){
                        row[column] = line.substring(start,current);
                        column++;
                        start = current+1;
                    }
                }

                // ACA ESTA EL OUTPUT
                // START
                if (column == row.length-1){
                    row[column] = line.substring(start, current);

                    MovieRating movieRating = new MovieRating(); //FIXME

                    Integer movieHashCode = Integer.parseInt(row[0].substring(2));

                    Movie movie = movieHash.get(movieHashCode);

                    /**
                     *    imdb_title_id,
                     *    weighted_average_vote,
                     *    total_votes,
                     *    mean_vote,
                     *    median_vote,
                     *    votes_10,
                     *    votes_9,
                     *    votes_8,
                     *    votes_7,
                     *    votes_6,
                     *    votes_5,
                     *    votes_4,
                     *    votes_3,
                     *    votes_2,
                     *    votes_1,
                     *    allgenders_0age_avg_vote,
                     *    allgenders_0age_votes,
                     *    allgenders_18age_avg_vote,
                     *    allgenders_18age_votes,
                     *    allgenders_30age_avg_vote,
                     *    allgenders_30age_votes,
                     *    allgenders_45age_avg_vote,
                     *    allgenders_45age_votes,
                     *    males_allages_avg_vote,
                     *    males_allages_votes,
                     *    males_0age_avg_vote,
                     *    males_0age_votes,
                     *    males_18age_avg_vote,
                     *    males_18age_votes,
                     *    males_30age_avg_vote,
                     *    males_30age_votes,
                     *    males_45age_avg_vote,
                     *    males_45age_votes,
                     *    females_allages_avg_vote,
                     *    females_allages_votes,
                     *    females_0age_avg_vote,
                     *    females_0age_votes,
                     *    females_18age_avg_vote,
                     *    females_18age_votes,
                     *    females_30age_avg_vote,
                     *    females_30age_votes,
                     *    females_45age_avg_vote,
                     *    females_45age_votes,
                     *    top1000_voters_rating,
                     *    top1000_voters_votes,
                     *    us_voters_rating,
                     *    us_voters_votes,
                     *    non_us_voters_rating,
                     *    non_us_voters_votes
                     */

                    //for (int i = 0; i < row.length; i++){
                    //    System.out.printf("%-40s","---"+row[i]);
                    //}
                    //System.out.println(" ");
                    //------------

                    movie.setRating(movieRating);

                    column = 0;
                    inQuotes = false;
                }
                // FINISH
            }
        } catch (IOException | KeyNotExistsException e) {
            e.printStackTrace();
        } finally {
            try{
                assert reader != null;
                reader.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }


    }

    public static DoubleHashTable<Integer, Integer, MovieCastMember> readTitlePrincipals(HashTable<Integer, Movie> movieHash, HashTable<Integer, CastMember> memberHash){

        BufferedReader reader = null;
        String line;
        String[] row = new String[6];
        int column;
        final String titlePrincipalsPath = "dataset\\IMDb title_principals2.csv";

        DoubleHashTable<Integer, Integer, MovieCastMember> hashToReturn = new DoubleHashTableImpl<>(movieHash, memberHash); //FIXME esto va a ser un doble hash

        try{
            reader = new BufferedReader(new FileReader(titlePrincipalsPath));
            line = reader.readLine();
            column = 0;
            int current;
            int start;
            boolean inQuotes = false;

            while((line = reader.readLine()) != null){
                start = 0;
                for (current = 0; current < line.length(); current++){
                    if (line.charAt(current) == '\"') inQuotes = !inQuotes;
                    else if (line.charAt(current) == ',' && !inQuotes){
                        row[column] = line.substring(start,current);
                        column++;
                        start = current;
                    }
                }

                if (column == row.length-1){
                    row[column] = line.substring(start, current);

                    /**
                     * row[0] String imdb_title_id
                     * row[1] Integer ordering
                     * row[2] String imdb_name_id
                     * row[3] String category
                     * row[4] String job
                     * row[5] String[] characters
                     */

                    // (int)ordering
                    Integer ordering = null;
                    if (!row[1].equals("")) ordering = Integer.parseInt(row[3]);

                    // characters -> list
                    String[] characters = row[5].split(",");

                    // get movie & castMember
                    Movie movie = movieHash.get(row[0].hashCode());
                    CastMember castMember = memberHash.get(row[2].hashCode());

                    MovieCastMember movieCastMemberToAdd = new MovieCastMember(movie, ordering, castMember, row[3], row[4], characters);

                    hashToReturn.put(
                            movieHash.getPosition(row[0].hashCode()),
                            memberHash.getPosition(row[2].hashCode()),
                            movieCastMemberToAdd);

                    column = 0;
                    inQuotes = false;
                }
            }
        } catch (IOException | KeyNotExistsException | KeyAlreadyExistsException e){
            e.printStackTrace();
        } finally {
            try{
                assert reader != null;
                reader.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return hashToReturn;
    }
}
