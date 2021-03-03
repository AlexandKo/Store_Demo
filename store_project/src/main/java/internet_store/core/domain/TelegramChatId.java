package internet_store.core.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "telegram", schema = "store")
public class TelegramChatId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "chatid")
    private Long chatId;
    @Column(name = "order_number")
    private String orderNumber;
}