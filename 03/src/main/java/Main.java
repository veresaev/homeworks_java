import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        NasaBot bot1 = new NasaBot("nasa_space_photography_bot", "8239461145:AAH_8kVS1fZyScTfEUPtb8ehUdCO9I5A05Y");
//        NasaBot bot2 = new NasaBot("another_bot", "AAA:BBB_CCC");
    }
}
