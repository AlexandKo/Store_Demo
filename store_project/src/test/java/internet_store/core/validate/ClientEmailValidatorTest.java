package internet_store.core.validate;


import internet_store.core.core_error.CoreError;
import internet_store.core.request.client.client_items.AddClientEmailRequest;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ClientEmailValidatorTest {
    private final ClientEmailValidator clientEmailValidator = new ClientEmailValidator();

    @Test
    public void shouldReturnNoErrors() {
        List<CoreError> errors = clientEmailValidator.validate(new AddClientEmailRequest("alex@alex.lv"));
        assertEquals(0, errors.size());
    }

    @Test
    public void shouldReturnErrors_1() {
        List<CoreError> errors = clientEmailValidator.validate(new AddClientEmailRequest("alex@alex"));
        assertEquals(1, errors.size());
        assertEquals("Email input error", errors.get(0).getField());
        assertEquals("email unsupported format", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrors_2() {
        List<CoreError> errors = clientEmailValidator.validate(new AddClientEmailRequest("@alex"));
        assertEquals(1, errors.size());
        assertEquals("Email input error", errors.get(0).getField());
        assertEquals("email unsupported format", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrors_3() {
        List<CoreError> errors = clientEmailValidator.validate(new AddClientEmailRequest("alex@"));
        assertEquals(1, errors.size());
        assertEquals("Email input error", errors.get(0).getField());
        assertEquals("email unsupported format", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrors_4() {
        List<CoreError> errors = clientEmailValidator.validate(new AddClientEmailRequest("email"));
        assertEquals(1, errors.size());
        assertEquals("Email input error", errors.get(0).getField());
        assertEquals("email unsupported format", errors.get(0).getMessage());
    }
}