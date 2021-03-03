package internet_store.core.response.product.product_item;

import internet_store.core.core_error.CoreError;
import internet_store.core.core_error.CoreErrorResponse;

import java.util.List;

public class AddProductCategoryResponse extends CoreErrorResponse {
    public AddProductCategoryResponse(List<CoreError> errors) {
        super(errors);
    }
}