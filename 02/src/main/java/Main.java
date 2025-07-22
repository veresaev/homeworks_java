
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        String url = "https://api.nasa.gov/planetary/apod?api_key=w0bUmEc7sjrKiuqsswbRp2q6E1Tz90EDesaAxxfk" + "&date=2025-07-21";

        CloseableHttpClient client = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();

        HttpGet request = new HttpGet(url);
        CloseableHttpResponse response = client.execute(request);

//        Scanner scanner = new Scanner(response.getEntity().getContent());
//        while (scanner.hasNext()) {
//            System.out.println(scanner.nextLine());
//        }

        Answer answer = mapper.readValue(response.getEntity().getContent(), Answer.class);
//        System.out.println(answer.url);
//        System.out.println(answer.title);
        String imageUrl = answer.url;
        String[] splittedUrl = imageUrl.split("/");
        String filename = splittedUrl[splittedUrl.length-1];

        request = new HttpGet(imageUrl);
        response = client.execute(request);

        FileOutputStream fos = new FileOutputStream("images/" + filename);
        response.getEntity().writeTo(fos);
    }
}
