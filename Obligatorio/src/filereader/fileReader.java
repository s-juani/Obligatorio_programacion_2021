package filereader;

import TADs.arraylist.MyArrayList;
import TADs.arraylist.MyArrayListImpl;
import TADs.hash.*;
import entities.*;

import java.util.Date;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
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

    public static MyHash readCastMember(){

        System.out.println("funca");

        BufferedReader reader = null;
        String line;
        MyArrayList row = new MyArrayListImpl(17);
        int column;
        final String castPath = "dataset\\IMDb names.csv";

        try{
            reader = new BufferedReader(new FileReader(castPath));
            column = 0;
            int current;
            int start;
            boolean inQuotes = false;

            while ((line = reader.readLine()) != null){
                start = 0;
                for (current = 0; current < line.length(); current++){
                    if (line.charAt(current) == '\"') inQuotes = !inQuotes;
                    else if (line.charAt(current) == ',' && !inQuotes){
                        row.setElement(column, line.substring(start,current));    //FIXME
                        column++;
                        start = current+1;
                    }
                }

                // ACA ESTA EL OUTPUT
                // START
                if (column == row.getLenght()-1){
                    row.setElement(column, line.substring(start, current));


                    /**
                     * row[0] = imdb_name_id            String
                     * row[1] = name                    String
                     * row[2] = birth_name              String
                     * row[3] = height                  int
                     * row[4] = bio                     String
                     * row[5] = birth_details        -- IGNORAR ------
                     * row[6] = date_of_birth           Date
                     * row[7] = place_of_birth          String[birthState, birthCountry, birthCity]
                     * row[8] = death_details        -- IGNORAR ------
                     * row[9] = date_of_death           Date
                     * row[10] = place_of_death         String[deathState, deathCountry, deathCity]
                     * row[11] = reason_of_death        CauseOfDeath (name String)
                     * row[12] = spouses_string         String
                     * row[13] = spouses                int
                     * row[14] = divorces               int
                     * row[15] = spouses_with_children  int
                     * row[16] = children               int
                     */

                    // Checkear si ya se registro un cause_of_death
                    // igual a este.
                    // FIXME





                    for (int i = 0; i < row.getLenght(); i++){
                        System.out.printf("%-40s","---"+row.get(i));
                    }
                    System.out.println();
                    column = 0;
                    inQuotes = false;
                }
                // FINISH



            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                assert reader != null;
                reader.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }


        return null;
    }

    public static void readCastMembers(MyHash<Integer, CastMember> castMemberHash, MyHash<Integer, CauseOfDeath> causeOfDeathHash){

    }

}
