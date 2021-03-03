package internet_store.core.validate;

import internet_store.core.core_error.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ProductQuantityValidatorTest {

    private final ProductQuantityValidator validator = new ProductQuantityValidator();

    @Test
    public void shouldReturnTrue_QuantityMoreZero() {
        List<CoreError> errors = validator.validate(5L, 1L);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnError_QuantityIsZero() {
        List<CoreError> errors = validator.validate(0L, 0L);
        assertFalse(errors.isEmpty());
        assertEquals("Quantity error ", errors.get(0).getField());
        assertEquals("Product quantity is zero", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnError_QuantityIsZero_1() {
        List<CoreError> errors = validator.validate(0L, 5L);
        assertFalse(errors.isEmpty());
        assertEquals("Quantity error ", errors.get(0).getField());
        assertEquals("Product quantity is zero", errors.get(0).getMessage());
        assertEquals("Quantity error ", errors.get(1).getField());
        assertEquals("No more product's quantity", errors.get(1).getMessage());
    }

    @Test
    public void shouldReturnError_QuantityIsZero_2() {
        List<CoreError> errors = validator.validate(5L, 15L);
        assertFalse(errors.isEmpty());
        assertEquals("Quantity error ", errors.get(0).getField());
        assertEquals("No more product's quantity", errors.get(0).getMessage());
    }
}