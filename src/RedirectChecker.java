import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class RedirectChecker {
    public static void main(String[] args) {
        String csvFile = "C:\\Users\\User\\Downloads\\links.csv";
        List<String> links = readLinksFromCSV(csvFile);

        RedirectProcessor processor = new RedirectProcessor();
        processor.checkMultipleRedirects(links);
    }

    public static List<String> readLinksFromCSV(String csvFile) {
        List<String> links = new ArrayList<>();

        try (Reader reader = new FileReader(csvFile);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
            for (CSVRecord csvRecord : csvParser) {
                String link = csvRecord.get(0);
                links.add(link);
            }
        } catch (IOException e) {
            System.out.println("Błąd odczytu pliku CSV: " + e.getMessage());
        }

        return links;
    }
}
