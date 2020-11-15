package framework.base;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class BaseForm {

    private URL requiredUrl;
    private HttpURLConnection conn;
    private int responseCode = 0;
    private Scanner scanner = null;
    private String jsonResponse = "";

    public BaseForm() {
    }

    public int getResponseCode(String url) {
        try {
            requiredUrl = new URL(url);
            conn = (HttpURLConnection)requiredUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            responseCode =  conn.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseCode;
    }

    public String getJsonBody(String url) {
        try {
            requiredUrl = new URL(url);
            scanner = new Scanner(requiredUrl.openStream());
            while (scanner.hasNext()) {
                jsonResponse += scanner.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        scanner.close();
        return jsonResponse;
    }
}
