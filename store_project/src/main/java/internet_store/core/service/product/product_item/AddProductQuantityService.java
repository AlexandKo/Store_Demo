package internet_store.core.service.product.product_item;

import internet_store.core.core_error.CoreError;
import internet_store.core.request.product.product_items.AddProductQuantityRequest;
import internet_store.core.response.product.product_item.AddProductQuantityResponse;
import internet_store.core.validate.NumberValidator;

import java.util.List;

public class AddProductQuantityService {

    public AddProductQuantityResponse execute(AddProductQuantityRequest addProductQuantityRequest) {
        NumberValidator<?> numberValidator = new NumberValidator<>(addProductQuantityRequest.getQuantity());

        List<CoreError> errors = numberValidator.validate();

        return new AddProductQuantityResponse(errors);
    }
}