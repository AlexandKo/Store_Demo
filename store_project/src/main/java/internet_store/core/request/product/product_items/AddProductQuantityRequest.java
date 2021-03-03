package internet_store.core.request.product.product_items;

import lombok.Getter;

import java.math.BigDecimal;

public class AddProductQuantityRequest {
    @Getter
    private final Object quantity;

    public AddProductQuantityRequest(Long quantity) {
        this.quantity = quantity;
    }

    public AddProductQuantityRequest(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public AddProductQuantityRequest(Integer quantity) {
        this.quantity = quantity;
    }
}