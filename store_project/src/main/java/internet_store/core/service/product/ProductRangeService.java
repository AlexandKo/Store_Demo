package internet_store.core.service.product;

import internet_store.core.domain.Product;
import internet_store.core.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductRangeService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProductsRange(int limit, int offset) {
        return productRepository.getLimitsProductsRecords(limit, offset);
    }
}