package internet_store.core.service.client.client_item;

import internet_store.core.core_error.CoreError;
import internet_store.core.request.client.client_items.AddClientEmailRequest;
import internet_store.core.response.client.client_items.AddClientEmailResponse;
import internet_store.core.validate.ClientEmailValidator;

import java.util.List;

public class AddClientEmailService {
    public AddClientEmailResponse execute(AddClientEmailRequest request) {
        ClientEmailValidator emailValidator = new ClientEmailValidator();

        List<CoreError> errors = emailValidator.validate(request);

        return new AddClientEmailResponse(errors);
    }
}