package internet_store.core.service.product;

import internet_store.core.domain.Product;
import internet_store.core.request.product.DeleteProductRequest;
import internet_store.core.response.product.DeleteProductResponse;
import internet_store.core.persistence.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DeleteProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private DeleteProductService deleteProductService;

    @Test
    public void shouldReturnNoError_DeleteProduct() {
        Product product = new Product();
        product.setTitle("Product#1");

        Mockito.when(productRepository.existsByTitle("Product#1")).thenReturn(true);
        Mockito.doNothing().when(productRepository).delete(product);
        DeleteProductResponse response = deleteProductService
                .execute(new DeleteProductRequest(product));

        assertFalse(response.hasErrors());
    }

    @Test
    public void shouldReturnError_ProductNoExist() {
        Product product = new Product();
        product.setTitle("Product#1");

        Mockito.when(productRepository.existsByTitle("Product#1")).thenReturn(false);
        DeleteProductResponse response = deleteProductService
                .execute(new DeleteProductRequest(product));

        assertTrue(response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("error", response.getErrors().get(0).getField());
        assertEquals("Product not exist in database", response.getErrors().get(0).getMessage());
    }
}