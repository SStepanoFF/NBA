package framework;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Loader {
    private static final String propFile = "../NBA.properties";
    static Properties proper = new Properties();
    static File resultFile = new File("log.txt");
    static FileWriter fileWriter = null;

    public static String loadProperty(String name) {

        try {
            proper.load(Loader.class.getResourceAsStream(propFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String value = "";
        if (name != null) {
            value = proper.getProperty(name);
        }
        return value;
    }

    //add new properties to file
    /*public static void reloadProperty(String text){
        try {
            proper.store(PropertyLoader.class.getResourceAsStream(propFile),String t);
        } catch (IOException e) {
            e.printStackTrace();
        }
            proper.setProperty("res",text);
    }*/

    public static void logWritter(String text) {
        try {
            fileWriter = new FileWriter(resultFile, true);
            fileWriter.append(text + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void clearResultFile(){
        try{
            fileWriter=new FileWriter(resultFile);
            fileWriter.write("");
            fileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
