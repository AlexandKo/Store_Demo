package internet_store.core.service.product.product_item;

import internet_store.core.request.product.product_items.AddProductCategoryRequest;
import internet_store.core.response.product.product_item.AddProductCategoryResponse;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddProductCategoryServiceTest {
    private final AddProductCategoryService service = new AddProductCategoryService();

    @Test
    public void shouldReturnCurrentInteger() {
        AddProductCategoryRequest request = new AddProductCategoryRequest(75);
        AddProductCategoryResponse response = service.execute(request);
        assertFalse(response.hasErrors());
    }

    @Test
    public void shouldReturnError_Integer() {
        AddProductCategoryRequest request = new AddProductCategoryRequest(-1130);
        AddProductCategoryResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("Integer input error", response.getErrors().get(0).getField());
        assertEquals("only positive number allowed", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_CategoryNoSet() {
        AddProductCategoryRequest request = new AddProductCategoryRequest(0);
        AddProductCategoryResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("error", response.getErrors().get(0).getField());
        assertEquals("category no set", response.getErrors().get(0).getMessage());
    }
}