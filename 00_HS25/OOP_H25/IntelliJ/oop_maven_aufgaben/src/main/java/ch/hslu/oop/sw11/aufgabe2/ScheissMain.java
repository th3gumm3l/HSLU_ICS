package ch.hslu.oop.sw11.aufgabe2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScheissMain {
    public static void main(String[] args) {
        //String filePath = "src/main/java/ch/hslu/oop/sw11/aufgabe2/netatmo-export-202501-202504.csv";
        String filePath = "";
        Logger logger = LoggerFactory.getLogger(ScheissMain.class);

        TempReader tempReader = new TempReader();
        TempFileModel tempFileModel = new TempFileModel();

        tempReader.readTextFile(filePath, tempFileModel);

        // Maximale Temperatur
        logger.info("Max temperatur: {}", tempFileModel.maxTemperatur());

        // Minimale Temperatur
        logger.info("Min temperatur: {}", tempFileModel.minTemperatur());

        // Avg Temperatur
        logger.info("Avg temperatur: {}", tempFileModel.avgTemperatur());

        // MaxTemp + Timestamp
        logger.info(tempFileModel.maxTemperaturAndTime());

        // MinTemp + Timestamp
        logger.info(tempFileModel.minTemperaturAndTime());
    }
}
