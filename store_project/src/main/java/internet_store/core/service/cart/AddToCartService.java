package internet_store.core.service.cart;

import internet_store.core.domain.Product;
import internet_store.core.domain.ProductInCart;
import internet_store.core.operation.Arithmetic;
import internet_store.core.persistence.CartRepository;
import internet_store.core.persistence.ProductRepository;
import internet_store.core.request.cart.AddProductToCartRequest;
import internet_store.core.response.cart.AddProductToCartResponse;
import internet_store.core.service.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class AddToCartService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository CartRepository;
    @Autowired
    private Arithmetic arithmetic;
    @Autowired
    private SessionService sessionService;

    public AddProductToCartResponse execute(AddProductToCartRequest request) {
        ProductInCart productInCart = new ProductInCart();

        Product product = productRepository.findByTitle(request.getProductTitle());
        BigDecimal price = product.getPrice();

        productInCart.setProduct(product);
        productInCart.setQuantity(request.getNewQuantity());

        BigDecimal sum = arithmetic.multiplyBigDecimalAndRound(new BigDecimal(request.getNewQuantity().toString())
                , price, 2);
        productInCart.setSum(sum);

        productInCart.setSessionId(sessionService.getSessionId());
        CartRepository.save(productInCart);

        return new AddProductToCartResponse(productInCart.getSum());
    }
}