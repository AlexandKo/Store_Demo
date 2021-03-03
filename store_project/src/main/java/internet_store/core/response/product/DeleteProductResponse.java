package internet_store.core.response.product;

import internet_store.core.core_error.CoreError;
import internet_store.core.core_error.CoreErrorResponse;

import java.util.List;

public class DeleteProductResponse extends CoreErrorResponse {

    public DeleteProductResponse(List<CoreError> errors) {
        super(errors);
    }
}