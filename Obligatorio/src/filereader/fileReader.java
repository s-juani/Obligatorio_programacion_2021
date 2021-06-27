package filereader;

import TADs.ClosedHash.*;
import TADs.ClosedHash.exceptions.*;
import TADs.LinkedList.ListaEnlazada;
import TADs.LinkedList.interfaces.Lista;
import TADs.arraylist.*;
import entities.*;

import java.text.ParseException;
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
        final String castPath = "dataset\\IMDb names.csv";

        HashTable<Integer,CastMember> hashToReturn = new ClosedHashTable<>(595411,0.5f);

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
                    try{
                        children = Integer.parseInt(row[16]);
                    } catch (Exception e){
                        e.printStackTrace();
                    }

                    //birth_location a lista
                    String[] birth = new String[2];
                    if (!row[7].equals("")){
                        String tempRow = row[7].substring(1,row[7].length()-1);
                        String[] birthArray = tempRow.split(",");
                        if (birthArray.length>=2) {
                            birth[0]=birthArray[0]; //city
                            birth[1]=birthArray[1]; //state
                        } else if (birthArray.length==1) birth[1]=birthArray[0];
                    }

                    //death_location a lista
                    String[] death = new String[3];
                    if (!row[10].equals("")){
                        String tempRow = row[10].substring(1,row[10].length()-1);
                        String[] deathArray = tempRow.split(",");
                        if (deathArray.length>3) {
                            death[0] = deathArray[0]; //city
                            death[1] = deathArray[1]; //state
                            for (int i = 2; i < deathArray.length; i++) death[2] = death[2] + deathArray[i]; //countr
                        } else if (deathArray.length==3) death=deathArray;
                        else if (deathArray.length==2){
                            death[0]=deathArray[0]; //city
                            death[2]=deathArray[1]; //country
                        } else if (deathArray.length==1) death[2]=deathArray[0]; //country
                    }

                    //birthDate -> adapt to java.date
                    Date birthDate = null;  // row[6]
                    Integer birthYear = null;
                    if (!row[6].equals("")){
                        try{
                            birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(row[6]);
                            birthYear = Integer.parseInt(row[6].substring(0,4));
                        } catch (Exception e){
                            String year = "";
                            for (int i=0; i<row[6].length(); i++){
                                if (Character.isDigit(row[6].charAt(i))){
                                    year += row[6].charAt(i);
                                }
                            }
                            try{
                                birthDate = new SimpleDateFormat("yyyy").parse(year);
                                birthYear = Integer.parseInt(year);
                            } catch (Exception f){
                                f.printStackTrace();
                            }
                        }
                    }

                    //deathDate -> adapt to java.date
                    Date deathDate = null; //row[9]
                    if (!row[9].equals("")){
                        try{
                            deathDate = new SimpleDateFormat("yyyy-MM-dd").parse(row[9]);
                        } catch (ParseException ignore){}
                    }

                    //causeOfDeath
                    String causeOfDeath = row[11];
                    if (!row[11].isEmpty()){
                        while (causeOfDeath.charAt(0) == ' ' || causeOfDeath.charAt(0) =='\"') {
                            causeOfDeath = causeOfDeath.substring(1);
                        }
                        while (causeOfDeath.charAt(causeOfDeath.length()-1) == ' ' || causeOfDeath.charAt(causeOfDeath.length()-1) =='\"') {
                            causeOfDeath = causeOfDeath.substring(0,causeOfDeath.length()-1);
                        }
                    }


                    CastMember memberToAdd = new CastMember(row[0], row[1], row[2], height, row[4],
                                                            birthDate,// row[6] buscar como castear a Date
                                                            birthYear,
                                                            birth[0],       //row[7] array con birth city, state, country
                                                            birth[1],
                                                            row[7],
                                                            deathDate,
                                                            death[0],       //row[10] array con death city, state, country
                                                            death[1],
                                                            row[10],
                                                            causeOfDeath,
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
                     *                             row[6] birthDate
                     *                             birthYear: primeros 4 caracteres de row[6]
                     *                             birth[0], //row[7] array con birth state, country, city
                     *                             birth[1],
                     *                             birth[2],
                     *                             row[9] deathDate
                     *                             death[0], //row[10] array con death state, country, city
                     *                             death[1],
                     *                             death[2],
                     *                             reasonOfDeath, //row[11] new CauseOfDeath (name String)
                     *                             row[12],
                     *                             spouses,
                     *                             divorces,
                     *                             spousesWithChildren,
                     *                             children
                     *
                     *                             row[0] imdb_name_id String
                     *                             row[1] name String
                     *                             row[2] birth_name String
                     *                             row[3] height Integer
                     *                             row[4] bio String
                     *                             row[5] birth_details *ignored*
                     *                             row[6] date_of_birth Date
                     *                             row[7] place_of_birth String
                     *                             row[8] death_details *ignored*
                     *                             row[9] date_of_death Date
                     *                             row[10] place_of_death String
                     *                             row[11] reason_of_death CauseOfDeath
                     *                             row[12] spouses_string String
                     *                             row[13] spouses Integer
                     *                             row[14] divorces Integer
                     *                             row[15] spouses_with_children Integer
                     *                             row[16] children Integer
                     */

                    //for (int i = 0; i < row.length; i++){
                    //    System.out.printf("%-40s","---"+row[i]);
                    //}
                    //System.out.println(" ");
                    //------------
                    hashToReturn.put(memberToAdd.hashCode(),memberToAdd);


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
        final String castPath = "dataset\\IMDb movies.csv";

        HashTable<Integer,Movie> hashToReturn = new ClosedHashTable<>(171711, 0.5f); //ajustar tamanio

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
                try{
                    if (!row[3].equals("")) year = Integer.parseInt(row[3]);
                } catch (Exception e){
                    //ignore
                }
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
                String[] genre = null;
                if (!row[5].isEmpty()){
                    genre = row[5].split(",");
                    for (int i=0; i<genre.length; i++) {
                        if (!genre[i].isEmpty()) {
                            while (genre[i].charAt(0) == ' ' || genre[i].charAt(0) == '\"') {
                                genre[i] = genre[i].substring(1);
                            }
                            while (genre[i].charAt(genre[i].length() - 1) == ' ' || genre[i].charAt(genre[i].length() - 1) == '\"') {
                                genre[i] = genre[i].substring(0, genre[i].length() - 1);
                            }
                        }
                    }
                }

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

                    hashToReturn.put(movieToAdd.hashCode(),movieToAdd);

                    try {
                        if(year != null) Movie.getYearIndex().get(year).add(movieToAdd);
                    } catch (KeyNotExistsException e) {
                        try {
                            Movie.getYearIndex().put(year, new ListaEnlazada<>());
                            Movie.getYearIndex().get(year).add(movieToAdd);
                        } catch (KeyAlreadyExistsException ignored) {}
                    }




                    column = 0;
                    inQuotes = false;
                }
                // FINISH
            }  //
        } catch (IOException | KeyNotExistsException |KeyAlreadyExistsException e) {
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
        final String castPath = "dataset\\IMDb ratings.csv";  /**"dataset\\IMDb ratings.csv" **/

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

                    //weighted_average_vote
                    if (!row[1].isEmpty()){
                        movieRating.setWeightedAverage(Float.parseFloat(row[1]));
                    }

                    //total_votes
                    if (!row[2].isEmpty()){
                        movieRating.setTotalVotes(Integer.parseInt(row[2]));
                    }

                    //mean_vote
                    if (!row[3].isEmpty()){
                        movieRating.setMeanVote(Float.parseFloat(row[3]));
                    }

                    //median_vote
                    if (!row[4].isEmpty()){
                        movieRating.setMedianVote(Float.parseFloat(row[4]));
                    }

                    //votes_list
                    Integer[] votesRating = new Integer[10];
                    int j=0;
                    for (int i=5; i<15; i++){
                        votesRating[j] = Integer.parseInt(row[i]);
                        j++;
                    }
                    movieRating.setVotesRating(votesRating);


                    Float numberVotes;
                    Float averageRating;
                    Float mVotes;
                    Float mAvg;
                    Float fVotes;
                    Float fAvg;

                    //malesRating
                    numberVotes = null;
                    if (!row[23].isEmpty()){
                        numberVotes = Float.parseFloat(row[23]);
                    }
                    averageRating = null;
                    if (!row[24].isEmpty()){
                        averageRating = Float.parseFloat(row[24]);
                    }
                    movieRating.setMalesRating(new Rating(averageRating, numberVotes));
                    mVotes = numberVotes;
                    mAvg = averageRating;

                    //femalesRating
                    numberVotes = null;
                    if (!row[33].isEmpty()){
                        numberVotes = Float.parseFloat(row[33]);
                    }
                    averageRating = null;
                    if (!row[34].isEmpty()){
                        averageRating = Float.parseFloat(row[34]);
                    }
                    movieRating.setFemalesRating(new Rating(averageRating, numberVotes));
                    fVotes = numberVotes;
                    fAvg = averageRating;

                    //allGenders Rating
                    if (mVotes == null) mVotes=0f;
                    if (fVotes == null) fVotes=0f;
                    if (mAvg == null) mAvg=0f;
                    if (fAvg == null) fAvg=0f;
                    numberVotes = mVotes+fVotes;
                    averageRating = (mVotes*mAvg + fVotes*fAvg)/numberVotes;
                    movieRating.setAllGendersRating(new Rating(averageRating, numberVotes));

                    //top1000Rating
                    numberVotes = null;
                    if (!row[43].isEmpty()){
                        numberVotes = Float.parseFloat(row[43]);
                    }
                    averageRating = null;
                    if (!row[44].isEmpty()){
                        averageRating = Float.parseFloat(row[44]);
                    }
                    movieRating.setTop1000Rating(new Rating(averageRating, numberVotes));

                    //usRating
                    numberVotes = null;
                    if (!row[45].isEmpty()){
                        numberVotes = Float.parseFloat(row[45]);
                    }
                    averageRating = null;
                    if (!row[46].isEmpty()){
                        averageRating = Float.parseFloat(row[46]);
                    }
                    movieRating.setUsRating(new Rating(averageRating, numberVotes));

                    //nonUsRating
                    numberVotes = null;
                    if (!row[47].isEmpty()){
                        numberVotes = Float.parseFloat(row[47]);
                    }
                    averageRating = null;
                    if (!row[48].isEmpty()){
                        averageRating = Float.parseFloat(row[48]);
                    }
                    movieRating.setNonUsRating(new Rating(averageRating, numberVotes));

                    /**
                     *    imdb_title_id,
                     *    weighted_average_vote,
                     *    total_votes,
                     *    mean_vote,
                     *    median_vote,
                     *
                     *
                     *
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
                     *
                     *
                     *
                     *    allgenders_0age_avg_vote, row[15]
                     *    allgenders_0age_votes,    row[16]
                     *    allgenders_18age_avg_vote,
                     *    allgenders_18age_votes,
                     *    allgenders_30age_avg_vote,
                     *    allgenders_30age_votes,
                     *    allgenders_45age_avg_vote,
                     *    allgenders_45age_votes,
                     *
                     *
                     *
                     *    males_allages_avg_vote,   row[23]
                     *    males_allages_votes,      row[24]
                     *
                     *    males_0age_avg_vote,
                     *    males_0age_votes,
                     *    males_18age_avg_vote,
                     *    males_18age_votes,
                     *    males_30age_avg_vote,
                     *    males_30age_votes,
                     *    males_45age_avg_vote,
                     *    males_45age_votes,
                     *
                     *
                     *
                     *    females_allages_avg_vote, row[33]
                     *    females_allages_votes,    row[34]
                     *
                     *    females_0age_avg_vote,
                     *    females_0age_votes,
                     *    females_18age_avg_vote,
                     *    females_18age_votes,
                     *    females_30age_avg_vote,
                     *    females_30age_votes,
                     *    females_45age_avg_vote,
                     *    females_45age_votes,
                     *
                     *
                     *    top1000_voters_rating,    row[43]
                     *    top1000_voters_votes,     row[44]
                     *
                     *
                     *    us_voters_rating,         row[45]
                     *    us_voters_votes,          row[46]
                     *
                     *
                     *    non_us_voters_rating,     row[47]
                     *    non_us_voters_votes       row[48]
                     */

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

    public static HashTable<Long, MovieCastMember> readTitlePrincipals(HashTable<Integer, Movie> movieHash,
                                                                       HashTable<Integer, CastMember> memberHash){

        BufferedReader reader = null;
        String line;
        String[] row = new String[6];
        int column;
        final String titlePrincipalsPath = "dataset\\IMDb title_principals.csv";

        HashTable<Long, MovieCastMember> hashToReturn = new ClosedHashTable<>(1670954,0.5f);

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
                    char character = line.charAt(current);
                    if (character == '\"') inQuotes = !inQuotes;
                    else if (character == ',' && !inQuotes){
                        row[column] = line.substring(start,current);
                        column++;
                        start = current+1;
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
                    if (!row[1].equals("")) ordering = Integer.parseInt(row[1]);

                    // characters -> list
                    String[] characters = row[5].split(",");

                    // get movie & castMember
                    Integer movieHashCode = Integer.parseInt(row[0].substring(2));
                    Movie movie = movieHash.get(movieHashCode);

                    Integer castMemberHashCode = Integer.parseInt(row[2].substring(2));
                    CastMember castMember = memberHash.get(castMemberHashCode);

                    //category
                    String category = row[3];
                    if (!row[3].isEmpty()){
                        while (category.charAt(0) == ' ' || category.charAt(0) =='\"') {
                            category = category.substring(1);
                        }
                        while (category.charAt(category.length()-1) == ' ' || category.charAt(category.length()-1) =='\"') {
                            category = category.substring(0,category.length()-1);
                        }
                    }

                    castMember.setOcupation(category);

                    MovieCastMember movieCastMemberToAdd = new MovieCastMember(movie, ordering, castMember, category, row[4], characters);

                    try {
                        MovieCastMember.getCastMemberIndex().get(castMember.hashCode()).add(movieCastMemberToAdd);
                    } catch (KeyNotExistsException e) {
                        try {
                            MovieCastMember.getCastMemberIndex().put(castMember.hashCode(), new ListaEnlazada<>());
                            MovieCastMember.getCastMemberIndex().get(castMember.hashCode()).add(movieCastMemberToAdd);
                        } catch (KeyAlreadyExistsException ignored) {}
                    }

                    try {
                        MovieCastMember.getMovieIndex().get(movie.hashCode()).add(movieCastMemberToAdd);
                    } catch (KeyNotExistsException e) {
                        try {
                            MovieCastMember.getMovieIndex().put(movie.hashCode(), new ListaEnlazada<>());
                            MovieCastMember.getMovieIndex().get(movie.hashCode()).add(movieCastMemberToAdd);
                        } catch (KeyAlreadyExistsException ignored) {}
                    }



                    /*
                    // start modify
                    try{
                        hashToReturn.put(movieCastMemberToAdd.longHashCode(), movieCastMemberToAdd);
                    } catch (KeyAlreadyExistsException e){
                        //ignore
                    }
                    // finish modify

                     */

                    column = 0;
                    inQuotes = false;
                }
            }
        } catch (IOException | KeyNotExistsException e){
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