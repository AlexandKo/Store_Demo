package internet_store.core.request.product;

import internet_store.core.domain.Product;
import lombok.Getter;

public class AddProductRequest {
    @Getter
    private final Product product;

    public AddProductRequest(Product product) {
        this.product = product;
    }
}