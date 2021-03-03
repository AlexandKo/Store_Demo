package internet_store.core.service.product;

import internet_store.core.domain.Product;
import internet_store.core.request.product.SearchProductByTitleRequest;
import internet_store.core.response.product.SearchProductByTitleResponse;
import internet_store.core.persistence.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class SearchProductByTitleServiceTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private SearchProductByTitleService productByTitleService;

    @Test
    public void shouldReturnNoError_ProductExist() {
        Product product = new Product();
        product.setTitle("Product#1");

        Mockito.when(productRepository.findByTitle("Product#1")).thenReturn(product);
        SearchProductByTitleRequest request = new SearchProductByTitleRequest("Product#1");
        SearchProductByTitleResponse response = productByTitleService.execute(request);

        assertNotNull(response.getProduct());
    }

    @Test
    public void shouldReturnError_ProductNoExists() {
        Mockito.when(productRepository.findByTitle("Product#1")).thenReturn(null);
        SearchProductByTitleRequest request = new SearchProductByTitleRequest("Product#1");
        SearchProductByTitleResponse response = productByTitleService.execute(request);

        assertNull(response.getProduct());
    }
}