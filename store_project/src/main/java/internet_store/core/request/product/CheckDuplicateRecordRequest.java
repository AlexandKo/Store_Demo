package internet_store.core.request.product;

import internet_store.core.domain.Product;
import lombok.Getter;

public class CheckDuplicateRecordRequest {
    @Getter
    private final Product product;

    public CheckDuplicateRecordRequest(Product product) {
        this.product = product;
    }
}