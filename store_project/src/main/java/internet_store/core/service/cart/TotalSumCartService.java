package internet_store.core.service.cart;

import internet_store.core.persistence.CartRepository;
import internet_store.core.service.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class TotalSumCartService {
    @Autowired
    private CartRepository CartRepository;
    @Autowired
    private SessionService sessionService;

    public BigDecimal calculateTotalSum() {
        BigDecimal sum = CartRepository.getCartTotalSum(sessionService.getSessionId());
        if (sum == null) {
            sum = new BigDecimal("0.00");
        }
        return sum;
    }
}