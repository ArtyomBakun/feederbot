package com.arba.feederbot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static com.arba.feederbot.config.Vk2TgFeederBotConfig.*;


public class Vk2TgFeederBot extends TelegramLongPollingBot {

    private String username;
    private String token;

    public Vk2TgFeederBot() {
        this.username = getPropertyByKey(TG_BOT_USERNAME_KEY);
        this.token = getPropertyByKey(TG_BOT_TOKEN_KEY);
    }

    /**
     * Метод для приема сообщений.
     *
     * @param update Содержит сообщение от пользователя.
     */
    @Override
    public void onUpdateReceived(Update update) {
        System.out.printf("asrgargar");

        String message = update.getMessage().getText();
//        sendMsg(update.getMessage().getChatId().toString(), getKuAPost());
        sendMsg(update.getMessage().getChatId().toString(), update.getMessage().getText().toUpperCase());
    }

    /**
     * Метод для настройки сообщения и его отправки.
     *
     * @param chatId id чата
     * @param s      Строка, которую необходимот отправить в качестве сообщения.
     */
    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override//name of bot reserved in @BotFather
    public String getBotUsername() {
        return username;
    }

    @Override//access token for bot gotten from @BotFather
    public String getBotToken() {
        return token;
    }

    public static void main(String[] args) {
        ApiContextInitializer.init();
        try {
            new TelegramBotsApi().registerBot(new Vk2TgFeederBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
