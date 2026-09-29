package ch.hslu.oop.sw11.aufgabe2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TempReader {
    private final Logger logger;

    public TempReader() {
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    public void readTextFile(final String file, TempFileModel tempFileModel) {
        File fileObj = new File(file);

        if (!fileObj.exists() || !fileObj.isFile()) {
            logger.error("Datei existiert nicht oder ist keine Datei");
            throw new IllegalArgumentException("Datei existiert nicht oder ist keine Datei");
        }

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)))
        {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] columns = line.split(";");

                if (columns.length >= 4) {
                    try{
                        tempFileModel.weNotCare.add(columns[0]);
                        tempFileModel.timestamp.add(LocalDateTime.parse(columns[1], DateTimeFormatter.ofPattern("\"yyyy/MM/dd HH:mm:ss\"")));
                        tempFileModel.temperatur.add(Float.parseFloat(columns[2]));
                        tempFileModel.luftfeuchtigkeit.add(columns[3]);
                    }
                    catch (NullPointerException | NumberFormatException | DateTimeParseException exception){
                        logger.error(exception.getMessage());
                    }
                }
            }
        }
        catch (IOException ioe) {
            logger.error(ioe.getMessage(), ioe);
        }
    }
}
