package internet_store.core.service.cart;

import internet_store.core.domain.ProductInCart;
import internet_store.core.persistence.CartRepository;
import internet_store.core.request.cart.DeleteProductFromCartRequest;
import internet_store.core.response.cart.DeleteProductFromCartResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DeleteProductFromCartServiceTest {
    @Mock
    private CartRepository cartRepository;
    @InjectMocks
    private DeleteProductFromCartService deleteService;

    @Test
    public void noCorrect_Id_Return_Error() {
        DeleteProductFromCartResponse response = deleteService.execute(new DeleteProductFromCartRequest(-5L));

        assertTrue(response.hasErrors());
        assertEquals("Long input error", response.getErrors().get(0).getField());
        assertEquals("only positive number allowed", response.getErrors().get(0).getMessage());
    }

    @Test
    public void noCorrect_NoFindInDatabase_Error() {
        Optional<ProductInCart> productInCart = Optional.empty();
        Mockito.when(cartRepository.findById(5L)).thenReturn(productInCart);

        DeleteProductFromCartResponse response = deleteService.execute(new DeleteProductFromCartRequest(5L));

        assertTrue(response.hasErrors());
        assertEquals("error", response.getErrors().get(0).getField());
        assertEquals("record no exist in database", response.getErrors().get(0).getMessage());
    }

    @Test
    public void correct_Id_No_Errors() {
        ProductInCart productInCart = new ProductInCart();
        productInCart.setId(2L);
        Optional<ProductInCart> productInCartOptional = Optional.of(productInCart);
        Mockito.when(cartRepository.findById(2L)).thenReturn(productInCartOptional);

        DeleteProductFromCartResponse response = deleteService.execute(new DeleteProductFromCartRequest(2L));

        assertFalse(response.hasErrors());
    }
}