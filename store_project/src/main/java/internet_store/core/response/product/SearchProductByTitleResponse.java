package internet_store.core.response.product;


import internet_store.core.domain.Product;
import lombok.Getter;

public class SearchProductByTitleResponse {
    @Getter
    private final Product product;

    public SearchProductByTitleResponse(Product product) {
        this.product = product;
    }
}