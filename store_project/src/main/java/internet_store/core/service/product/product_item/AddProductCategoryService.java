package internet_store.core.service.product.product_item;

import internet_store.core.core_error.CoreError;
import internet_store.core.request.product.product_items.AddProductCategoryRequest;
import internet_store.core.response.product.product_item.AddProductCategoryResponse;
import internet_store.core.validate.NumberValidator;

import java.util.List;

public class AddProductCategoryService {

    public AddProductCategoryResponse execute(AddProductCategoryRequest request) {
        NumberValidator<?> numberValidator = new NumberValidator<>(request.getCategory());

        List<CoreError> errors = numberValidator.validate();

        if (request.getCategory()==0) {
            errors.add(new CoreError("error","category no set"));
        }
        return new AddProductCategoryResponse(errors);
    }
}