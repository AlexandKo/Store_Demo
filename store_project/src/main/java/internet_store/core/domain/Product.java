package internet_store.core.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "product", schema = "store")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "category")
    private int category;
    @Column(name = "quantity")
    private Long quantity;
    @Column(name = "price")
    private BigDecimal price;
}