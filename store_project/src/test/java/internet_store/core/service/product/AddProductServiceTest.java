package internet_store.core.service.product;

import internet_store.core.domain.Product;
import internet_store.core.request.product.AddProductRequest;
import internet_store.core.response.product.AddProductResponse;
import internet_store.core.persistence.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AddProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private AddProductService addProductService;

    @Test
    public void returnNoError() {
        Product product = new Product();
        product.setTitle("Title");
        product.setDescription("Description");
        product.setCategory(1);
        product.setQuantity(5L);
        product.setPrice(new BigDecimal("75.05"));

        Mockito.when(productRepository.findByTitle(product.getTitle())).thenReturn(null);
        Mockito.when(productRepository.save(product)).thenReturn(product);
        AddProductResponse response = addProductService.execute(new AddProductRequest(product));

        assertFalse(response.hasErrors());
    }

    @Test
    public void returnError_DuplicateInDatabase() {
        Product product = new Product();
        product.setTitle("Title");
        product.setDescription("Description");
        product.setCategory(1);
        product.setQuantity(5L);
        product.setPrice(new BigDecimal("75.05"));

        Mockito.when(productRepository.findByTitle(product.getTitle())).thenReturn(product);
        AddProductResponse response = addProductService.execute(new AddProductRequest(product));

        assertTrue(response.hasErrors());
        assertEquals("error", response.getErrors().get(0).getField());
        assertEquals("Record exist in database", response.getErrors().get(0).getMessage());
    }

    @Test
    public void returnError_AllErrorsWithoutDuplicate() {
        Product product = new Product();

        Mockito.when(productRepository.findByTitle(product.getTitle())).thenReturn(null);
        AddProductResponse response = addProductService.execute(new AddProductRequest(product));

        assertTrue(response.hasErrors());
        assertEquals(5, response.getErrors().size());
        assertEquals("error", response.getErrors().get(0).getField());
        assertEquals("Empty field", response.getErrors().get(0).getMessage());
        assertEquals("error", response.getErrors().get(1).getField());
        assertEquals("Empty field", response.getErrors().get(1).getMessage());
        assertEquals("error", response.getErrors().get(2).getField());
        assertEquals("Empty field", response.getErrors().get(2).getMessage());
        assertEquals("error", response.getErrors().get(3).getField());
        assertEquals("Empty field", response.getErrors().get(3).getMessage());
        assertEquals("error", response.getErrors().get(4).getField());
        assertEquals("category no set", response.getErrors().get(4).getMessage());
    }

    @Test
    public void shouldReturnError_NoTitle() {
        Product product = new Product();
        product.setDescription("Description");
        product.setCategory(1);
        product.setQuantity(5L);
        product.setPrice(new BigDecimal("75.05"));

        Mockito.when(productRepository.findByTitle(product.getTitle())).thenReturn(null);

        AddProductResponse response = addProductService.execute(new AddProductRequest(product));
        assertTrue(response.hasErrors());
        assertEquals("error", response.getErrors().get(0).getField());
        assertEquals("Empty field", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_NoDiscription() {
        Product product = new Product();
        product.setTitle("Title");
        product.setCategory(1);
        product.setQuantity(5L);
        product.setPrice(new BigDecimal("75.05"));

        Mockito.when(productRepository.findByTitle(product.getTitle())).thenReturn(null);

        AddProductResponse response = addProductService.execute(new AddProductRequest(product));
        assertTrue(response.hasErrors());
        assertEquals("error", response.getErrors().get(0).getField());
        assertEquals("Empty field", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_QuantityLessZero() {
        Product product = new Product();
        product.setTitle("Title");
        product.setDescription("Description");
        product.setCategory(1);
        product.setQuantity(-55L);
        product.setPrice(new BigDecimal("75.05"));

        Mockito.when(productRepository.findByTitle(product.getTitle())).thenReturn(null);

        AddProductResponse response = addProductService.execute(new AddProductRequest(product));
        assertTrue(response.hasErrors());
        assertEquals("Long input error", response.getErrors().get(0).getField());
        assertEquals("only positive number allowed", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_PriceLessZero() {
        Product product = new Product();
        product.setTitle("Title");
        product.setDescription("Description");
        product.setCategory(1);
        product.setQuantity(55L);
        product.setPrice(new BigDecimal("-1175.89"));

        Mockito.when(productRepository.findByTitle(product.getTitle())).thenReturn(null);

        AddProductResponse response = addProductService.execute(new AddProductRequest(product));
        assertTrue(response.hasErrors());
        assertEquals("BigDecimal input error", response.getErrors().get(0).getField());
        assertEquals("only positive number allowed", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_NoSetCategory() {
        Product product = new Product();
        product.setTitle("Title");
        product.setDescription("Description");
        product.setQuantity(5L);
        product.setPrice(new BigDecimal("75.05"));

        Mockito.when(productRepository.findByTitle(product.getTitle())).thenReturn(null);

        AddProductResponse response = addProductService.execute(new AddProductRequest(product));
        assertTrue(response.hasErrors());
        assertEquals("error", response.getErrors().get(0).getField());
        assertEquals("category no set", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_CategoryLessZero() {
        Product product = new Product();
        product.setTitle("Title");
        product.setDescription("Description");
        product.setCategory(-55);
        product.setQuantity(5L);
        product.setPrice(new BigDecimal("75.05"));

        Mockito.when(productRepository.findByTitle(product.getTitle())).thenReturn(null);

        AddProductResponse response = addProductService.execute(new AddProductRequest(product));
        assertTrue(response.hasErrors());
        assertEquals("Integer input error", response.getErrors().get(0).getField());
        assertEquals("only positive number allowed", response.getErrors().get(0).getMessage());
    }
}