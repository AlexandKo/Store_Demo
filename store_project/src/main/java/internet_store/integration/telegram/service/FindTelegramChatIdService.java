package internet_store.integration.telegram.service;

import internet_store.core.domain.TelegramChatId;
import internet_store.core.persistence.TelegramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindTelegramChatIdService {
    @Autowired
    private TelegramRepository telegramRepository;

    public List<TelegramChatId> execute(String orderNumber) {
        return telegramRepository.findAllByOrderNumber(orderNumber);
    }
}