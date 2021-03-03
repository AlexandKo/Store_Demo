package internet_store.integration.telegram.service;

import internet_store.core.persistence.TelegramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CheckTelegramChatIdService {
    @Autowired
    private TelegramRepository telegramRepository;

    public boolean isRecordExists(long chatId, String orderNumber) {
        Integer result = telegramRepository.existsByChatIdAndOrderNumber(chatId, orderNumber);
        return result == 1;
    }
}