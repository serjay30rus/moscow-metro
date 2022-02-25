import com.google.gson.Gson;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Exer_9_5 {
    public static void main(String[] args) throws IOException, ParseException {

        System.out.println("======= LINES ======");
        System.out.println(loadLinesFromDoc().toString());

        System.out.println("======= STATIONS ======");
        System.out.println(loadStationsFromDoc().toString());

        List<MetroLine> lines = loadLinesFromDoc();
        List<MetroStation> stations = loadStationsFromDoc();

        Gson gsonLines = new Gson();                                     // Creating of new GSON object
        String jsonLnes = gsonLines.toJson(lines);
        System.out.println("Json Lines\t" + jsonLnes);

        Gson gsonStations = new Gson();                                  // Creating of new GSON object
        String jsonStations = gsonStations.toJson(stations);
        System.out.println("Json Stations\t" + jsonStations);

        try {
            File fileStations = new File("output\\Stations.txt");              // New txt-file for stations
            FileWriter writerStation = new FileWriter(fileStations);
            writerStation.write(jsonStations + "\n");
            writerStation.flush();

            File fileLines = new File("output\\Lines.txt");              // New txt-file for lines
            FileWriter writerLine = new FileWriter(fileLines);
            writerLine.write(jsonLnes + "\n");
            writerLine.flush();


        } catch (IOException exc) {
            exc.printStackTrace();
        }


        
    }

    private static List<MetroLine> loadLinesFromDoc() {

        final String LINE_NAME_CLASS = "t-metrostation-list-header";

        ArrayList<MetroLine> newLineList = new ArrayList<MetroLine>();            // Creating of new Stations list
        try {
            String sourceUrl = "https://www.moscowmap.ru/metro.html#lines";  // Creating of new variable with URL for download
            Document doc = Jsoup.connect(sourceUrl).maxBodySize(0)
                    .get();

            Elements lineNames = doc.getElementsByClass(LINE_NAME_CLASS);      // Getting of lines list from web-site

            for (Element lineName : lineNames) {
                String[] fragments = lineName.toString().split("\\s+");      // New object of lineName

                String clearLineName = fragments[5].split(">|<")[1];
                String clearLineNumber = fragments[4].split("-|\"")[1];

                newLineList.add(new MetroLine(clearLineName, clearLineNumber));
            }

        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return newLineList;
    }

    private static List<MetroStation> loadStationsFromDoc() {

        final String STATIONS_LIST = "t-metrostation-list-table";
        String sourceUrl = "https://www.moscowmap.ru/metro.html#lines";  // Creating of new variable with URL for download
        List<MetroStation> newStationList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(sourceUrl).maxBodySize(0)
                    .get();

            Elements stationNames = doc.getElementsByClass(STATIONS_LIST);
            Elements links = stationNames.select("a[href]");

            for (Element link : links) {
                String[] fragments = link.toString().split("\\s+");

                String lineName = fragments[2].split("/")[2];
                String stationName = fragments[4].split("<|>")[1];

                newStationList.add(new MetroStation(stationName, lineName));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return newStationList;
    }
}
