package internet_store.core.persistence;

import internet_store.core.domain.ProductInCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<ProductInCart, Long> {

    @Query(value = "SELECT COUNT(*) FROM cart WHERE deleted=false AND ordered=false AND session_id=?", nativeQuery = true)
    Long countProductInCart(String sessionId);

    @Query(value = "SELECT * FROM cart WHERE deleted=false AND ordered=false AND session_id=? limit ? offset ?", nativeQuery = true)
    List<ProductInCart> getLimitsCartRecords(String sessionId, int limit, int offset);

    @Query(value = "SELECT SUM(sum) FROM cart WHERE deleted=false AND ordered=false AND session_id=?", nativeQuery = true)
    BigDecimal getCartTotalSum(String sessionId);

    @Query(value = "SELECT * FROM cart WHERE deleted=false AND ordered=false AND session_id=?", nativeQuery = true)
    List<ProductInCart> itemsForOrder(String sessionId);
}