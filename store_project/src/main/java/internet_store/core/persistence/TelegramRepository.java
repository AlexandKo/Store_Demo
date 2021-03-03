package internet_store.core.persistence;

import internet_store.core.domain.TelegramChatId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelegramRepository extends JpaRepository<TelegramChatId, Long> {
    @Query(value = "SELECT EXISTS(SELECT * FROM telegram WHERE chatid=? AND order_number=?)", nativeQuery = true)
    Integer existsByChatIdAndOrderNumber(long chatId, String orderNumber);

    List<TelegramChatId> findAllByOrderNumber(String number);
}