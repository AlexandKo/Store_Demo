package internet_store.core.response.client.client_items;

import internet_store.core.core_error.CoreError;
import internet_store.core.core_error.CoreErrorResponse;
import lombok.Getter;

import java.util.List;

public class AddClientSurnameResponse extends CoreErrorResponse {
    @Getter
    private String surname;

    public AddClientSurnameResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddClientSurnameResponse(String surname) {
        this.surname = surname;
    }
}