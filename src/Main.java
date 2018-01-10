import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

/**
 * Created by eric on 21/09/17.
 */
public class Main {
    public static void main(String[] args) {
        //initialize Api Context
        ApiContextInitializer.init();
        //Instantiate Telegram Bots API
        TelegramBotsApi telegramBotApi = new TelegramBotsApi();
        EricBot bot = new EricBot();

        //Register our bot
        try {
            telegramBotApi.registerBot(bot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }


    }
}