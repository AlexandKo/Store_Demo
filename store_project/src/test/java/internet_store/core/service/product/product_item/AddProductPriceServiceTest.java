package internet_store.core.service.product.product_item;

import internet_store.core.request.product.product_items.AddProductPriceRequest;
import internet_store.core.response.product.product_item.AddProductPriceResponse;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class AddProductPriceServiceTest {
    private final AddProductPriceService service = new AddProductPriceService();

    @Test
    public void shouldReturnCurrentBigDecimal() {
        AddProductPriceRequest request = new AddProductPriceRequest(new BigDecimal("105"));
        AddProductPriceResponse response = service.execute(request);
        assertFalse(response.hasErrors());
    }

    @Test
    public void shouldReturnCurrentInteger() {
        AddProductPriceRequest request = new AddProductPriceRequest(45);
        AddProductPriceResponse response = service.execute(request);
        assertFalse(response.hasErrors());
    }

    @Test
    public void shouldReturnCurrentLong() {
        AddProductPriceRequest request = new AddProductPriceRequest(106L);
        AddProductPriceResponse response = service.execute(request);
        assertFalse(response.hasErrors());
    }

    @Test
    public void shouldReturnError_BigDecimal() {
        AddProductPriceRequest request = new AddProductPriceRequest(new BigDecimal("-10"));
        AddProductPriceResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("BigDecimal input error", response.getErrors().get(0).getField());
        assertEquals("only positive number allowed", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_Integer() {
        AddProductPriceRequest request = new AddProductPriceRequest(-155);
        AddProductPriceResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("Integer input error", response.getErrors().get(0).getField());
        assertEquals("only positive number allowed", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_Long() {
        AddProductPriceRequest request = new AddProductPriceRequest(-315L);
        AddProductPriceResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("Long input error", response.getErrors().get(0).getField());
        assertEquals("only positive number allowed", response.getErrors().get(0).getMessage());
    }
}