package internet_store.core.validate;

import internet_store.core.core_error.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class StringTypeValidatorTest {
    private final StringTypeValidator stringValidator = new StringTypeValidator();

    @Test
    public void shouldReturnNoError() {
        List<CoreError> errors = stringValidator.validate("Test string");
        assertEquals(0, errors.size());
    }

    @Test
    public void shouldReturnError_NullString() {
        List<CoreError> errors = stringValidator.validate(null);
        assertEquals(1, errors.size());
        assertEquals("error", errors.get(0).getField());
        assertEquals("Empty field", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnError_EmptyString() {
        List<CoreError> errors = stringValidator.validate("");
        assertEquals(1, errors.size());
        assertEquals("error", errors.get(0).getField());
        assertEquals("Empty field", errors.get(0).getMessage());
    }
}