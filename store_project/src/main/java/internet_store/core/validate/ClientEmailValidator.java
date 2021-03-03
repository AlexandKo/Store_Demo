package internet_store.core.validate;

import internet_store.core.core_error.CoreError;
import internet_store.core.request.client.client_items.AddClientEmailRequest;
import org.apache.commons.validator.routines.*;

import java.util.ArrayList;
import java.util.List;

public class ClientEmailValidator {
    public List<CoreError> validate(AddClientEmailRequest addClientEmailRequest) {
        List<CoreError> errors = new ArrayList<>();

        boolean emailValidate = EmailValidator.getInstance().isValid(addClientEmailRequest.getEmail());

        if (!(emailValidate)) {
            errors.add(new CoreError("Email input error", "email unsupported format"));
        }
        return errors;
    }
}