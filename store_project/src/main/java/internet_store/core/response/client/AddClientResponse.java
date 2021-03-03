package internet_store.core.response.client;

import internet_store.core.core_error.CoreError;
import internet_store.core.core_error.CoreErrorResponse;

import java.util.List;

public class AddClientResponse extends CoreErrorResponse {
    public AddClientResponse(List<CoreError> errors) {
        super(errors);
    }
}