package internet_store.core.service.product.product_item;

import internet_store.core.core_error.CoreError;
import internet_store.core.request.product.product_items.AddProductPriceRequest;
import internet_store.core.response.product.product_item.AddProductPriceResponse;
import internet_store.core.validate.NumberValidator;

import java.util.List;

public class AddProductPriceService {
    public AddProductPriceResponse execute(AddProductPriceRequest addProductPriceRequest) {
        NumberValidator<?> numberValidator = new NumberValidator<>(addProductPriceRequest.getPrice());

        List<CoreError> errors = numberValidator.validate();

        return new AddProductPriceResponse(errors);
    }
}