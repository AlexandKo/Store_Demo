package internet_store.core.service.product.product_item;

import internet_store.core.request.product.product_items.AddProductQuantityRequest;
import internet_store.core.response.product.product_item.AddProductQuantityResponse;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class AddProductQuantityServiceTest {
    private final AddProductQuantityService service = new AddProductQuantityService();

    @Test
    public void shouldReturnCurrentBigDecimal() {
        AddProductQuantityRequest request = new AddProductQuantityRequest(new BigDecimal("0.15"));
        AddProductQuantityResponse response = service.execute(request);
        assertFalse(response.hasErrors());
    }

    @Test
    public void shouldReturnCurrentInteger() {
        AddProductQuantityRequest request = new AddProductQuantityRequest(75);
        AddProductQuantityResponse response = service.execute(request);
        assertFalse(response.hasErrors());
    }

    @Test
    public void shouldReturnCurrentLong() {
        AddProductQuantityRequest request = new AddProductQuantityRequest(55L);
        AddProductQuantityResponse response = service.execute(request);
        assertFalse(response.hasErrors());
    }

    @Test
    public void shouldReturnError_Long() {
        AddProductQuantityRequest request = new AddProductQuantityRequest(-1L);
        AddProductQuantityResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("Long input error", response.getErrors().get(0).getField());
        assertEquals("only positive number allowed", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_BigDecimal() {
        AddProductQuantityRequest request = new AddProductQuantityRequest(new BigDecimal("-1487.99"));
        AddProductQuantityResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("BigDecimal input error", response.getErrors().get(0).getField());
        assertEquals("only positive number allowed", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_Integer() {
        AddProductQuantityRequest request = new AddProductQuantityRequest(-8963);
        AddProductQuantityResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("Integer input error", response.getErrors().get(0).getField());
        assertEquals("only positive number allowed", response.getErrors().get(0).getMessage());
    }
}