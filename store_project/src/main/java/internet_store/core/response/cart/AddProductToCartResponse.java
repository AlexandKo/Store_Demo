package internet_store.core.response.cart;

import lombok.Getter;

import java.math.BigDecimal;

public class AddProductToCartResponse  {
    @Getter
    private final BigDecimal productSum;

    public AddProductToCartResponse(BigDecimal productSum) {
        this.productSum = productSum;
    }
}