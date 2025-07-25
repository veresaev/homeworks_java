import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.IOException;

public class Utils {
    public static String getImage() {
        String url = "https://api.nasa.gov/planetary/apod" +
                "?api_key=w0bUmEc7sjrKiuqsswbRp2q6E1Tz90EDesaAxxfk";

        CloseableHttpClient client = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();

        try {
            HttpGet request = new HttpGet(url);
            CloseableHttpResponse response = client.execute(request);
            Answer answer = mapper.readValue(response.getEntity().getContent(), Answer.class);
            String imageUrl = answer.url;
            return imageUrl;
        } catch (IOException e) {
            System.out.println("Серевер Nasa не отвечает");
            return "";
        }
    }

    public static String getImageForDate(String date) {
        String url = "https://api.nasa.gov/planetary/apod" +
                "?api_key=w0bUmEc7sjrKiuqsswbRp2q6E1Tz90EDesaAxxfk" + "&date=" + date;

        CloseableHttpClient client = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();

        try {
            HttpGet request = new HttpGet(url);
            CloseableHttpResponse response = client.execute(request);
            Answer answer = mapper.readValue(response.getEntity().getContent(), Answer.class);
            String imageUrl = answer.url;
            return imageUrl;
        } catch (IOException e) {
            System.out.println("Серевер Nasa не отвечает");
            return "";
        }
    }
}
