package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {

    private FileUtils() {
    }

    /**
     * Loads a file into a string
     * @param file file to load
     * @return file stringified
     */
    public static String loadAsString(String file) {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String buffer = "";
            while((buffer = reader.readLine()) != null) {
                builder.append(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

}
