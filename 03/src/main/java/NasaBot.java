import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class NasaBot extends TelegramLongPollingBot {
    String botName;
    String botToken;

    public NasaBot(String botName, String botToken) {
        this.botName = botName;
        this.botToken = botToken;
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(this);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            String text = update.getMessage().getText();
            System.out.println("Из чата номер " + chatId + " написали " + text);

            String[] splittedText = text.split(" ");
            String option = splittedText[0];

            switch (option) {
                case "/start":
                    sendMessage(chatId, "Я Бот Nasa. Я присылаю картинку дня");
                    break;
                case "/help":
                    sendMessage(chatId, "Для получения сегодняшней картинки введи /image");
                    break;
                case "/image":
                    String image = Utils.getImage();
                    sendMessage(chatId, image);
                    break;
                case "/date":
                    String date = splittedText[1];
                    image = Utils.getImageForDate(date);
                    sendMessage(chatId, image);
                    break;
                default:
                    sendMessage(chatId, "Я не знаю такой команды");
            }
        }
    }

    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
        message.setChatId(chatId);
        message.setText(text);

        try {
            execute(message); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        // TODO
        return botName;
    }

    @Override
    public String getBotToken() {
        // TODO
        return botToken;
    }
}
