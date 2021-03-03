package internet_store.core.service.product.product_item;

import internet_store.core.request.product.product_items.AddProductTitleRequest;
import internet_store.core.response.product.product_item.AddProductTitleResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class AddProductTitleServiceTest {
    private final AddProductTitleService service = new AddProductTitleService();

    @Test
    public void shouldReturnCurrentString() {
        AddProductTitleRequest request = new AddProductTitleRequest("item");
        AddProductTitleResponse response = service.execute(request);
        assertFalse(response.hasErrors());
    }

    @Test
    public void shouldReturnError_1() {
        AddProductTitleRequest request = new AddProductTitleRequest("");
        AddProductTitleResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("error", response.getErrors().get(0).getField());
        assertEquals("Empty field", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_2() {
        AddProductTitleRequest request = new AddProductTitleRequest(null);
        AddProductTitleResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("error", response.getErrors().get(0).getField());
        assertEquals("Empty field", response.getErrors().get(0).getMessage());
    }
}