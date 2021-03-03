package internet_store.core.request.product;

import lombok.Getter;

public class CheckStockQuantityRequest {
    @Getter
    private final Long quantity;
    @Getter
    private final String title;

    public CheckStockQuantityRequest(Long quantity, String title) {
        this.quantity = quantity;
        this.title = title;
    }
}