package internet_store.core.service.product;

import internet_store.core.domain.Product;
import internet_store.core.request.product.CheckDuplicateRecordRequest;
import internet_store.core.response.product.CheckDuplicateRecordResponse;
import org.junit.Test;

import static org.junit.Assert.*;

public class CheckDuplicateRecordServiceTest {
    private final CheckDuplicateRecordService duplicateRecordService = new CheckDuplicateRecordService();

    @Test
    public void shouldReturnError_FoundRecordInDataBase() {
        Product product = new Product();
        product.setTitle("Title");
        CheckDuplicateRecordResponse response = duplicateRecordService.execute(new
                CheckDuplicateRecordRequest(product));

        assertTrue(response.hasErrors());
        assertEquals("error", response.getErrors().get(0).getField());
        assertEquals("Record exist in database", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnNoError() {
        CheckDuplicateRecordResponse response = duplicateRecordService.execute(new
                CheckDuplicateRecordRequest(null));

        assertFalse(response.hasErrors());
    }
}