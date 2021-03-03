package internet_store.core.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "cart", schema = "store")
public class ProductInCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "quantity")
    private Long quantity;
    @Column(name = "sum")
    private BigDecimal sum;
    @Column(name = "deleted")
    private Boolean deleted = false;
    @Column(name = "ordered")
    private Boolean ordered = false;
    @Column(name="session_id")
    private String sessionId;
}