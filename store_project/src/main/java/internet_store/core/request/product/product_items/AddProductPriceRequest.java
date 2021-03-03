package internet_store.core.request.product.product_items;

import lombok.Getter;

import java.math.BigDecimal;

public class AddProductPriceRequest {
    @Getter
    private final Object price;

    public AddProductPriceRequest(BigDecimal price) {
        this.price = price;
    }

    public AddProductPriceRequest(Integer price) {
        this.price = price;
    }

    public AddProductPriceRequest(Long price) {
        this.price = price;
    }
}
