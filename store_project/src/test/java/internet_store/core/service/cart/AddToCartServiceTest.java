package internet_store.core.service.cart;

import internet_store.core.domain.Product;
import internet_store.core.domain.ProductInCart;
import internet_store.core.operation.Arithmetic;
import internet_store.core.persistence.CartRepository;
import internet_store.core.persistence.ProductRepository;
import internet_store.core.request.cart.AddProductToCartRequest;
import internet_store.core.response.cart.AddProductToCartResponse;
import internet_store.core.service.session.SessionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AddToCartServiceTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private SessionService sessionService;
    @Mock
    private Arithmetic arithmetic;
    @Mock
    private CartRepository cartRepository;
    @InjectMocks
    private AddToCartService addToCartService;

    @Test
    public void shouldReturn_NoError() {
        ProductInCart productInCart = new ProductInCart();
        Product product = new Product();
        product.setTitle("Product");
        product.setQuantity(5L);
        product.setCategory(1);
        product.setDescription("Description");
        product.setPrice(new BigDecimal("45.12"));
        productInCart.setProduct(product);
        productInCart.setQuantity(2L);
        productInCart.setSum(new BigDecimal("90.24"));

        Mockito.when(productRepository.findByTitle("Product")).thenReturn(product);
        Mockito.when(sessionService.getSessionId()).thenReturn(null);
        Mockito.when(arithmetic.multiplyBigDecimalAndRound(new BigDecimal("2"),
                new BigDecimal("45.12"), 2)).thenReturn(new BigDecimal("90.24"));
        Mockito.when(cartRepository.save(productInCart)).thenReturn(productInCart);

        AddProductToCartResponse response = addToCartService.execute(new AddProductToCartRequest(2, "Product"));

        Mockito.verify(cartRepository, Mockito.times(1)).save(productInCart);

        assertEquals(new BigDecimal("90.24"), response.getProductSum());
    }
}