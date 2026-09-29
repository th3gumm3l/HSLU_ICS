package ch.hslu.oop.sw11.aufgabe1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;

public class MainSw11 {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainSw11.class);

    public static void main(String[] args) {
        int number = 12345;
        String fileName = "numbers.bin";

        writeIntToFile(number, fileName);
        int intResult = readIntFromFile(fileName);
        float floatResult = readIntFromFileAsFloat(fileName);
        LOGGER.info("Der Int ist: {}", intResult);
        LOGGER.info("Der Float ist: {}", floatResult);
    }

    private static void writeIntToFile(int input, String fileName) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
        DataOutputStream dos = new DataOutputStream(fos)){
            dos.writeInt(input);
            LOGGER.info("Der Integer {} wurde erfolgreich in '{}' geschrieben.", input, fileName);
        }
        catch (IOException e) {
            LOGGER.error("Fehler beim Lesen/Schreiben der Datei:", e);
        }
    }

    private static int readIntFromFile(String fileName) {
        try (FileInputStream fis = new FileInputStream(fileName);
             DataInputStream dis = new DataInputStream(fis)){
            return dis.readInt();
        }
        catch (IOException e) {
            LOGGER.error("Fehler beim Lesen/Schreiben der Datei:", e);
        }
        return 0;
    }

    private static float readIntFromFileAsFloat(String fileName) {
        try (FileInputStream fis = new FileInputStream(fileName);
             DataInputStream dis = new DataInputStream(fis)){
            return dis.readFloat();
        }
        catch (IOException e) {
            LOGGER.error("Fehler beim Lesen/Schreiben der Datei:", e);
        }
        return 0;
    }
}
