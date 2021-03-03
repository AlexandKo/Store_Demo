package internet_store.core.response.cart;

import internet_store.core.core_error.CoreError;
import internet_store.core.core_error.CoreErrorResponse;

import java.util.List;

public class DeleteProductFromCartResponse extends CoreErrorResponse {

    public DeleteProductFromCartResponse(List<CoreError> errors) {
        super(errors);
    }
}