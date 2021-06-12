package filereader;

import TADs.arraylist.MyArrayList;
import TADs.arraylist.MyArrayListImpl;
import TADs.hash.*;
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


    public static MyHash readCastMember(){
        BufferedReader reader = null;
        String line;
        String[] row = new String[17];
        int column;
        final String castPath = "dataset\\IMDb names2.csv";

        MyHash<Integer,CastMember> hashToReturn = new MyClosedHashImpl<>(496187, 0.75);


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

                    hashToReturn.put(memberToAdd.hashCode(),memberToAdd);

                    column = 0;
                    inQuotes = false;
                }
                // FINISH
            }
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
        return hashToReturn;
    }
}
