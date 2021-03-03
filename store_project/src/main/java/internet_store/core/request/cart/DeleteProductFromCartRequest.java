package internet_store.core.request.cart;

import lombok.Getter;

public class DeleteProductFromCartRequest {
    @Getter
    private final long id;

    public DeleteProductFromCartRequest(long id) {
        this.id = id;
    }
}