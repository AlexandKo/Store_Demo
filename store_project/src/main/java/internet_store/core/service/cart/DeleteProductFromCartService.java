package internet_store.core.service.cart;

import internet_store.core.core_error.CoreError;
import internet_store.core.domain.ProductInCart;
import internet_store.core.persistence.CartRepository;
import internet_store.core.request.cart.DeleteProductFromCartRequest;
import internet_store.core.response.cart.DeleteProductFromCartResponse;
import internet_store.core.validate.NumberValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DeleteProductFromCartService {
    @Autowired
    private CartRepository CartRepository;

    public DeleteProductFromCartResponse execute(DeleteProductFromCartRequest request) {
        NumberValidator<?> numberValidator = new NumberValidator<>(request.getId());

        List<CoreError> errors = numberValidator.validate();
        Optional<ProductInCart> productInCart = Optional.empty();

        if (errors.isEmpty()) {
            productInCart = CartRepository.findById(request.getId());
        }
        if (productInCart.isEmpty()) {
            errors.add(new CoreError("error", "record no exist in database"));
            return new DeleteProductFromCartResponse(errors);
        }
        productInCart.ifPresent(cart -> cart.setDeleted(true));
        productInCart.ifPresent(cart -> CartRepository.save(cart));

        return new DeleteProductFromCartResponse(errors);
    }
}