package filereader;

import TADs.hash.*;

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

    public static MyHash readCast(){

        BufferedReader reader = null;
        String line;
        String[] row = new String[17];
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
                        row[column] = line.substring(start,current);
                        column++;
                        start = current+1;
                    }
                }

                if (column == row.length-1){
                    row[column] = line.substring(start, current);
                    for (int i = 0; i < row.length; i++){
                        System.out.printf("%-40s","---"+row[i]);
                    }
                    System.out.println();
                    column = 0;
                    inQuotes = false;
                }
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
}
