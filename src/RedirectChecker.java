import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

/**
 * The RedirectChecker class is used to check redirects of links from a CSV file.
 */

public class RedirectChecker {
    /**
     * The mian method of the program that reads links from CSV file and check for redirects.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        String csvFile = "C:\\Users\\User\\Downloads\\links.csv";
        List<String> links = readLinksFromCSV(csvFile);

        RedirectProcessor processor = new RedirectProcessor();
        processor.checkMultipleRedirects(links);
    }

    /**
     * Reads lins from a CSV file witch are separeted by "," and returns them as a list.
     * @param csvFile the path to the csv file
     * @return a list of links read from the CSV file
     */
    public static List<String> readLinksFromCSV(String csvFile) {
        List<String> links = new ArrayList<>();

        try (Reader reader = new FileReader(csvFile);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
            for (CSVRecord csvRecord : csvParser) {
                String link = csvRecord.get(0);
                links.add(link);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading a CSV file: " + e.getMessage());
        }

        return links;
    }
}
