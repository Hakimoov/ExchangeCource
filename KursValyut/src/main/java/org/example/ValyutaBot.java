package org.example;

import org.example.services.ValyutaService;
import org.example.services.ValyutaServiceImpl;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class ValyutaBot extends TelegramLongPollingBot {
    ValyutaService valyuta = new ValyutaServiceImpl();
    public ValyutaBot(String botToken, ValyutaService valyuta) {
        super(botToken);
        this.valyuta = valyuta;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()){
            SendMessage sendMessage = new SendMessage();
            String text = update.getMessage().getText();
            String userName = update.getMessage().getChat().getFirstName();
            printTerminal(text, userName);

            Long chatId = update.getMessage().getChatId();
            sendMessage.setChatId(chatId);

            switch (text){
                case "/start" -> {
                    startMenu(text, userName, sendMessage);
                }
                case "Uzbek\uD83C\uDDFA\uD83C\uDDFF" -> {
                    uzMenu(text, sendMessage);
                }
                case "Bot haqida\uD83D\uDC81" -> {
                    sendMessage.setText("Bu bot Internetdan hozirgi kurs valyutani" +
                            " aiqlab beruvchi botdir!");
                }
                case "English\uD83C\uDDFA\uD83C\uDDF8" -> {
                    enMenu(text, sendMessage);
                }
                case  "Bot info\uD83D\uDC81" -> {
                    sendMessage.setText("This bot is a bot that calculates current exchange " +
                            "rates from the Internet!\n");
                }
                case "\uD83D\uDD19" -> {
                    startMenu(text, userName, sendMessage);
                }
                case "Valyuta kursi\uD83D\uDD04" -> {
                    valyutaMenu(sendMessage);
                }
                case "Exchange course\uD83D\uDD04" -> {
                    valyutaMenuEn(sendMessage);
                }
                case "Calculate Course\uD83D\uDD21" -> {
                    calculateMenu(update, valyuta, sendMessage);
                }

                default ->{
//                    sendMessage.setText("Iltimos Ozgina sabr qiling ! ...");
                    calculateMenu(update, valyuta, sendMessage);

                }


            }

            try {
                execute(sendMessage);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }



    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }

    @Override
    public String getBotUsername() {
        return "kursValyutaHasbot";
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }

    private void printTerminal(String text, String userName){
        System.out.println(userName + "-->" + text);

    }

    private void startMenu(String text, String userName, SendMessage sendMessage){
        sendMessage.setText("Assalomu aleykum telegram botimizga hush kelibsiz " +
                "\uD83D\uDC4B");


        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> buttons = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();

        KeyboardButton buttonUz = new KeyboardButton();
        buttonUz.setText("Uzbek\uD83C\uDDFA\uD83C\uDDFF");

        KeyboardButton buttonEn = new KeyboardButton();
        buttonEn.setText("English\uD83C\uDDFA\uD83C\uDDF8");

        keyboardRow.add(buttonUz);
        keyboardRow.add(buttonEn);

        buttons.add(keyboardRow);

        replyKeyboardMarkup.setKeyboard(buttons);

        sendMessage.setReplyMarkup(replyKeyboardMarkup);
    }

    private void uzMenu(String text, SendMessage sendMessage){
        sendMessage.setText("Kurs valyuta hissoblagichga hush kelibsiz!");

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> buttons = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        KeyboardRow keyboardRow2 = new KeyboardRow();

        KeyboardButton courseChange = new KeyboardButton();
        courseChange.setText("Valyuta kursi\uD83D\uDD04");

        KeyboardButton botInfo = new KeyboardButton();
        botInfo.setText("Bot haqida\uD83D\uDC81");

        KeyboardButton goBack = new KeyboardButton();
        goBack.setText("\uD83D\uDD19");

        KeyboardButton calculateCourse = new KeyboardButton();
        calculateCourse.setText("Calculate Course\uD83D\uDD21");

        keyboardRow.add(courseChange);
        keyboardRow.add(botInfo);
        keyboardRow2.add(goBack);

        keyboardRow2.add(calculateCourse);

        buttons.add(keyboardRow);
        buttons.add(keyboardRow2);
        replyKeyboardMarkup.setKeyboard(buttons);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
    }

    private void enMenu(String text, SendMessage sendMessage){
        sendMessage.setText("Welcome to the exchange rate!");

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> buttons = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();

        KeyboardButton courseChange = new KeyboardButton();
        courseChange.setText("Exchange course\uD83D\uDD04");

        KeyboardButton botInfo = new KeyboardButton();
        botInfo.setText("Bot info\uD83D\uDC81");

        KeyboardButton goBack = new KeyboardButton();
        goBack.setText("\uD83D\uDD19");

        keyboardRow.add(courseChange);
        keyboardRow.add(botInfo);
        keyboardRow.add(goBack);

        buttons.add(keyboardRow);
        replyKeyboardMarkup.setKeyboard(buttons);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
    }

    private void valyutaMenu(SendMessage sendMessage){
        sendMessage.setText(valyuta.getValyuta().get(0).getCcy() + "$\uD83C\uDDFA\uD83C\uDDF8 \n" +
                "Pul birligi --> " + valyuta.getValyuta().get(0).getCcyNm_UZC() + "\uD83D\uDCB2 \n" +
                valyuta.getValyuta().get(0).getNominal() + " --> " + valyuta.getValyuta().get(0).getRate() +
                "SO'M\uD83C\uDDFA\uD83C\uDDFF \n" + "Yangilangan vaqt: " + valyuta.getValyuta().get(0).getDate() +
                "\n=============================\n"
                +valyuta.getValyuta().get(2).getCcy() + "\uD83C\uDDF7\uD83C\uDDFA \n" + "Pul birligi --> " +
                valyuta.getValyuta().get(2).getCcyNm_UZ()+" \uD83C\uDDF7\uD83C\uDDFA \n" +
                valyuta.getValyuta().get(2).getNominal() + "-->" + valyuta.getValyuta().get(2).getRate() +
                "SO'M\uD83C\uDDFA\uD83C\uDDFF \n" + "Yangilangan vaqt:" + valyuta.getValyuta().get(2).getDate() +
                "\n" + valyuta.getValyuta().get(0).getDate() +
                "\n=============================\n"
                +valyuta.getValyuta().get(35).getCcy()+ "\uD83C\uDDF0\uD83C\uDDF7 \n" +
                "Pul birligi --> " + valyuta.getValyuta().get(35).getCcyNm_UZ()+"\uD83C\uDDF0\uD83C\uDDF7 \n" +
                valyuta.getValyuta().get(35).getNominal() + "-->" + valyuta.getValyuta().get(35).getRate() +
                "SO'M\uD83C\uDDFA\uD83C\uDDFF \n" + "Yangilangan vaqt:" +
                valyuta.getValyuta().get(35).getDate());
    }

    private void valyutaMenuEn(SendMessage sendMessage){
        sendMessage.setText(valyuta.getValyuta().get(0).getCcy() + "$\uD83C\uDDFA\uD83C\uDDF8 \n" +
                "Currency --> " + valyuta.getValyuta().get(0).getCcyNm_UZC() + "\uD83D\uDCB2 \n" +
                valyuta.getValyuta().get(0).getNominal() + " --> " + valyuta.getValyuta().get(0).getRate() +
                "SUM\uD83C\uDDFA\uD83C\uDDFF \n" + "Updated time: " + valyuta.getValyuta().get(0).getDate() +
                "\n=============================\n"
                +valyuta.getValyuta().get(2).getCcy() + "\uD83C\uDDF7\uD83C\uDDFA \n" + "Currency --> " +
                valyuta.getValyuta().get(2).getCcyNm_UZ()+" \uD83C\uDDF7\uD83C\uDDFA \n" +
                valyuta.getValyuta().get(2).getNominal() + "-->" + valyuta.getValyuta().get(2).getRate() +
                "SUM\uD83C\uDDFA\uD83C\uDDFF \n" + "Updated time:" + valyuta.getValyuta().get(2).getDate() +
                "\n" + valyuta.getValyuta().get(0).getDate() +
                "\n=============================\n"
                +valyuta.getValyuta().get(35).getCcy()+ "\uD83C\uDDF0\uD83C\uDDF7 \n" +
                "Currency --> " + valyuta.getValyuta().get(35).getCcyNm_UZ()+"\uD83C\uDDF0\uD83C\uDDF7 \n" +
                valyuta.getValyuta().get(35).getNominal() + "-->" + valyuta.getValyuta().get(35).getRate() +
                "SUM\uD83C\uDDFA\uD83C\uDDFF \n" + "Updated time:" +
                valyuta.getValyuta().get(35).getDate());
    }

    private void calculateMenu(Update update, ValyutaService valyuta, SendMessage sendMessage) {
        sendMessage.setText("Hohlagan dollar miqdorini kiriting ayirboshlab beramiz!");

        String userNumber = update.getMessage().getText();
        String uzSom = valyuta.getValyuta().get(0).getRate();

        double som = Double.parseDouble(uzSom);
        double num = Double.parseDouble(userNumber);

        int total = (int) ((som * num) / 1000); // 345.545som

        String t = String.valueOf(total * 1000); // 345 * 100 = 345000

        sendMessage.setText(num + "USD $ --> " + t + "SO'M ");
    }

}
