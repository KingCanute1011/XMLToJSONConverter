

import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        String url = "https://gist.githubusercontent.com/evanr76/3365397/raw/7015f47f96a25" +
                "4ba71e1deb1d420a87bade42767/shipwire-rate-response-example.xml";


        int numRequests = 10;

        // Thread pool for concurrent execution
        ExecutorService executor = Executors.newFixedThreadPool(numRequests);

        for (int i = 0; i < numRequests; i++) {
            executor.execute(() -> {
                try {
                    // Fetch XML
                    String xmlData = fetchXML(url);

                    // Parse XML to JSON
                    String jsonData = parseXMLToJSON(xmlData);

                    // Generate unique filename
                    String uniqueId = UUID.randomUUID().toString();
                    String filename = "out-" + uniqueId + ".json";

                    // Save JSON to file
                    saveJSONToFile(jsonData, filename);

                    System.out.println("Saved JSON to " + filename);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
    }

    // Function to fetch XML from URL
    private static String fetchXML(String urlString) throws IOException {
        URL url = new URL(urlString);
        InputStream inputStream = url.openStream();
        byte[] buffer = inputStream.readAllBytes();
        return new String(buffer);
    }

    // Function to parse XML to JSON
    private static String parseXMLToJSON(String xmlData) {

        return xmlData;
    }

    // Function to save JSON to file
    private static void saveJSONToFile(String jsonData, String filename) throws IOException {
        Path filePath = Paths.get(filename);
        try (OutputStream outputStream = new FileOutputStream(filePath.toFile())) {
            outputStream.write(jsonData.getBytes());
        }
    }
}
