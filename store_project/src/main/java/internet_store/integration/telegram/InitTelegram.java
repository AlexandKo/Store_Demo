package internet_store.integration.telegram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class InitTelegram {
    @Autowired
    private ChatBot chatBot;

    @SneakyThrows(TelegramApiException.class)
    public void init() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(chatBot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}