package internet_store.core.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "order_for_client", schema = "store")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number")
    private String number;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;
    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private ProductInCart cart;
    @Column(name = "sum")
    private BigDecimal sum;
    @Column(name = "tax")
    private BigDecimal tax;
    @Column(name = "total")
    private BigDecimal total;
    @Column(name = "status")
    private String status;
}