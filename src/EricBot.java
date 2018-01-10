import org.telegram.telegrambots.api.methods.GetFile;
import org.telegram.telegrambots.api.methods.send.SendDocument;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.File;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.PhotoSize;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.*;
import java.util.logging.Level;


/**
 * Created by eric on 21/09/17.
 */
public class EricBot extends TelegramLongPollingBot{
    private final String myId = "81863253";
    private String groupChatId = "-1001113515771";
    private HashMap<String,String> roles;



    public EricBot() {
        roles = new HashMap<>();
    }

    @Override
    public void onUpdateReceived(Update update) {
        // Set variables
        if(!update.hasMessage())return;
        String message_text = update.getMessage().getText();
        String userid = update.getMessage().getFrom().getId().toString();
        String chatid = update.getMessage().getChatId().toString();
        String user_first_name = update.getMessage().getFrom().getFirstName();
        String user_username = update.getMessage().getChat().getUserName();


        SendMessage s = new SendMessage().setChatId(groupChatId);
        //System.out.println("messaggio ricevuto");
        try {
            if(chatid.equals(myId)){
                System.out.println("scrivo dalla mia chat");
                s.setText("xd");
                s.setChatId(myId);
                execute(s);

            }else if(chatid.equals(groupChatId)){
                //System.out.println("gruppo");

                if(message_text.startsWith("/setruolo")&& message_text.length()>9){
                    //System.out.println(message_text);
                    AggiungiRuolo(user_first_name, message_text.split(" ", 2)[1]);
                    System.out.println();
                }
                if(message_text.startsWith("/resetruoli")) resetRuoli();
                if(message_text.startsWith("/ruoli")){
                    System.out.println(roles);
                    Set<String> users = roles.keySet();
                    String m = "I ruoli sono:\n";
                    for(String u : users){
                        m += u +": "+ roles.get(u) +"\n";

                    }
                    //System.out.println("Sending:\n"+ m);
                    s.setText(m);
                    execute(s);
                }

            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getBotUsername() {
        return "systemoutprintlnbetabot";
    }

    @Override
    public String getBotToken() {
        return "549922158:AAFNRseEV4AHMy_rarDVsnqitCxQ2Whxinw";
    }

    private void AggiungiRuolo(String user, String ruolo){
        //System.out.println(user +": "+ruolo);
        roles.put(user, ruolo);
    }

    private void resetRuoli(){
        roles.clear();
    }
}
