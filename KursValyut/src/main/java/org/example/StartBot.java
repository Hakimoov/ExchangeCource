package org.example;

import org.example.services.ValyutaService;
import org.example.services.ValyutaServiceImpl;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class StartBot {
    public static void main(String[] args) {
        ValyutaService valyutaService = new ValyutaServiceImpl();
        ValyutaBot valyutaBot = new ValyutaBot(
        "6323581936:AAGSaRTLC0AhViaVZUPJ3SRe29ZgDj7zh2Y", valyutaService);
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(valyutaBot);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}