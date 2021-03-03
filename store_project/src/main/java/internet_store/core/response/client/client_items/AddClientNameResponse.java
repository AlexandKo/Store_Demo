package internet_store.core.response.client.client_items;

import internet_store.core.core_error.CoreError;
import internet_store.core.core_error.CoreErrorResponse;
import lombok.Getter;

import java.util.List;

public class AddClientNameResponse extends CoreErrorResponse {
    @Getter
    private String name;

    public AddClientNameResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddClientNameResponse(String name) {
        this.name = name;
    }
}