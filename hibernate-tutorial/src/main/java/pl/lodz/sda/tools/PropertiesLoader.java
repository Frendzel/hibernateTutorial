package pl.lodz.sda.tools;

import pl.lodz.sda.environment.DB;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    public static String PATH = "connection.properties";
    Properties prop = new Properties();

    public void init() throws IOException {
        // zaczytywanie strumienia z classLoadera
        InputStream inputStream = getClass().
                getClassLoader().getResourceAsStream(PATH);

        // jezeli zaczytalismy, czyli znalezlismy plik
        if (inputStream != null) {
            try {
                // przekazmy strumien do obiektu properties
                prop.load(inputStream);
            } catch (IOException e) {
                // to musi dzialac
            } finally {
                // zamknijmy strumien
                inputStream.close();
            }
        } else {
            // jezeli nie wczytalismy strumieniem pliku
            // to wyrzucmy bardziej zrozumialy wyjatek
            // pokroju FileNotFoundException
            throw new FileNotFoundException("property file '" + PATH + "' not found in the classpath");
        }
    }

    // wyciagnijmy wartosc properties po kluczu
    private String getProperty(String key) {
        return prop.getProperty(key);
    }
    // zmapujmy te wartosc na enum DB
    public DB getDb() {
        return DB.valueOf(getProperty("db.engine"));
    }

}
