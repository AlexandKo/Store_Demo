package internet_store.core.service.product.product_item;

import internet_store.core.request.product.product_items.AddProductDescriptionRequest;
import internet_store.core.response.product.product_item.AddProductDescriptionResponse;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddProductDescriptionServiceTest {
    private final AddProductDescriptionService service = new AddProductDescriptionService();

    @Test
    public void shouldReturnCurrentString() {
        AddProductDescriptionRequest request = new AddProductDescriptionRequest("description");
        AddProductDescriptionResponse response = service.execute(request);
        assertEquals("description", response.getProductDescription());
    }

    @Test
    public void shouldReturnError_1() {
        AddProductDescriptionRequest request = new AddProductDescriptionRequest("");
        AddProductDescriptionResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("Empty field", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_2() {
        AddProductDescriptionRequest request = new AddProductDescriptionRequest(null);
        AddProductDescriptionResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("Empty field", response.getErrors().get(0).getMessage());
    }
}