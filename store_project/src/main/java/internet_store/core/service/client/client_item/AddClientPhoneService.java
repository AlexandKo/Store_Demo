package internet_store.core.service.client.client_item;

import internet_store.core.core_error.CoreError;
import internet_store.core.request.client.client_items.AddClientPhoneRequest;
import internet_store.core.response.client.client_items.AddClientPhoneResponse;
import internet_store.core.validate.PhoneNumberValidator;

import java.util.List;

public class AddClientPhoneService {
    public AddClientPhoneResponse execute(AddClientPhoneRequest addClientPhoneRequest) {
        PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();
        List<CoreError> errors = phoneNumberValidator.validate(addClientPhoneRequest,"LV");

        return new AddClientPhoneResponse(errors);
    }
}