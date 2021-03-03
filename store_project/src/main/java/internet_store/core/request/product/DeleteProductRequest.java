package internet_store.core.request.product;

import internet_store.core.domain.Product;
import lombok.Getter;

public class DeleteProductRequest {
    @Getter
    private final Product product;

    public DeleteProductRequest(Product product) {
        this.product = product;
    }
}