package by.it_academy.homework3.db_properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataBaseProperty {
    private static Properties property = new Properties();

    private static void getFile() {
        try (FileInputStream file = new FileInputStream("src/main/resources/connectionDB.properties")) {
            property.load(file);
        } catch (IOException e) {
            System.out.println("file '.properties' not found");
        }
    }

    public static String getURL() {
        getFile();
        return property.getProperty("db.url");
    }

    public static String getDriver() {
        getFile();
        return property.getProperty("db.driver");
    }
}
