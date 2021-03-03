package internet_store.core.service.cart;

import internet_store.core.persistence.CartRepository;
import internet_store.core.service.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CartProductsCountService {
    @Autowired
    private CartRepository CartRepository;
    @Autowired
    private SessionService sessionService;

    public long getCartCount() {
        return CartRepository.countProductInCart(sessionService.getSessionId());
    }
}