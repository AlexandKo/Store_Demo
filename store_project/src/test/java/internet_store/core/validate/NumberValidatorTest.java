package internet_store.core.validate;

import internet_store.core.core_error.CoreError;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class NumberValidatorTest {

    @Test
    public void checkNullObject() {
        NumberValidator<?> validator = new NumberValidator<>(null);
        List<CoreError> result = validator.validate();
        assertEquals("error", result.get(0).getField());
        assertEquals("Empty field", result.get(0).getMessage());
    }

    @Test
    public void checkIntegerLessZero() {
        NumberValidator<?> validator = new NumberValidator<>(-5);
        List<CoreError> result = validator.validate();
        assertEquals("Integer input error", result.get(0).getField());
        assertEquals("only positive number allowed", result.get(0).getMessage());
    }

    @Test
    public void checkLongLessZero() {
        NumberValidator<?> validator = new NumberValidator<>(-105L);
        List<CoreError> result = validator.validate();
        assertEquals("Long input error", result.get(0).getField());
        assertEquals("only positive number allowed", result.get(0).getMessage());
    }

    @Test
    public void checkBigDecimalLessZero() {
        NumberValidator<?> validator = new NumberValidator<>(new BigDecimal("-985"));
        List<CoreError> result = validator.validate();
        assertEquals("BigDecimal input error", result.get(0).getField());
        assertEquals("only positive number allowed", result.get(0).getMessage());
    }
}