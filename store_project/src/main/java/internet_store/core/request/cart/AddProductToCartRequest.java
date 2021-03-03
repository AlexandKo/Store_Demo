package internet_store.core.request.cart;

import lombok.Getter;

public class AddProductToCartRequest {
    @Getter
    private final Long newQuantity;
    @Getter
    private final String  productTitle;

    public AddProductToCartRequest(long newQuantity, String  productTitle) {
        this.newQuantity = newQuantity;
        this.productTitle = productTitle;
    }
}