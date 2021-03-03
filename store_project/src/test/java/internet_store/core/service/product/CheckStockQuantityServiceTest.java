package internet_store.core.service.product;

import internet_store.core.domain.Product;
import internet_store.core.request.product.CheckStockQuantityRequest;
import internet_store.core.response.product.CheckStockQuantityResponse;
import internet_store.core.persistence.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CheckStockQuantityServiceTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private CheckStockQuantityService stockQuantityService;

    @Test
    public void shoulReturn_NoError() {
        Product productInStock = new Product();
        productInStock.setQuantity(15L);

        Mockito.when(productRepository.findByTitle("Title")).thenReturn(productInStock);
        CheckStockQuantityRequest request = new CheckStockQuantityRequest(7L, "Title");
        CheckStockQuantityResponse response = stockQuantityService.execute(request);

        assertFalse(response.hasErrors());
    }

    @Test
    public void shoulReturn_errorQuantityMoreThanInStock() {
        Product productInStock = new Product();
        productInStock.setTitle("Product#1");
        productInStock.setQuantity(15L);

        Mockito.when(productRepository.findByTitle("Product#1")).thenReturn(productInStock);
        CheckStockQuantityRequest request = new CheckStockQuantityRequest(21L, "Product#1");
        CheckStockQuantityResponse response = stockQuantityService.execute(request);

        assertTrue(response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("Quantity error", response.getErrors().get(0).getField());
        assertEquals("For this product available only 15", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shoulReturn_errorQuantityEqualZero() {
        Product productInStock = new Product();
        productInStock.setTitle("Product#1");
        productInStock.setQuantity(15L);

        Mockito.when(productRepository.findByTitle("Product#1")).thenReturn(productInStock);
        CheckStockQuantityRequest request = new CheckStockQuantityRequest(0L, "Product#1");
        CheckStockQuantityResponse response = stockQuantityService.execute(request);

        assertTrue(response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("Quantity error", response.getErrors().get(0).getField());
        assertEquals("Quantity can't equal zero", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shoulReturn_errorQuantityLessZero() {
        Product productInStock = new Product();
        productInStock.setTitle("Product#1");
        productInStock.setQuantity(15L);

        Mockito.when(productRepository.findByTitle("Product#1")).thenReturn(productInStock);
        CheckStockQuantityRequest request = new CheckStockQuantityRequest(-45L, "Product#1");
        CheckStockQuantityResponse response = stockQuantityService.execute(request);

        assertTrue(response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("Quantity error", response.getErrors().get(0).getField());
        assertEquals("Quantity can't less zero", response.getErrors().get(0).getMessage());
    }
}