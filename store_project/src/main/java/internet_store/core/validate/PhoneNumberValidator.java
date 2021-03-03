package internet_store.core.validate;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import internet_store.core.core_error.CoreError;
import internet_store.core.request.client.client_items.AddClientPhoneRequest;

import java.util.ArrayList;
import java.util.List;

public class PhoneNumberValidator {
    public List<CoreError> validate(AddClientPhoneRequest addClientPhoneRequest, String region) {
        List<CoreError> errors = new ArrayList<>();
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        boolean phoneNumberValidate = false;

        try {
            Phonenumber.PhoneNumber thePhoneNumber = phoneUtil.parse(addClientPhoneRequest.getPhoneNumber(),
                    region);
            phoneNumberValidate = phoneUtil.isValidNumber(thePhoneNumber);
        } catch (NumberParseException e) {
            errors.add(new CoreError("Phone number input error", "phone number input error"));
        }

        if (!(phoneNumberValidate)) {
            errors.add(new CoreError("Phone number input error", "phone number unsupported format"));
        }
        return errors;
    }
}