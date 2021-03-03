package internet_store.core.service.product;

import internet_store.core.core_error.CoreError;
import internet_store.core.domain.Product;
import internet_store.core.request.product.CheckStockQuantityRequest;
import internet_store.core.response.product.CheckStockQuantityResponse;
import internet_store.core.validate.StockQuantityValidator;
import internet_store.core.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CheckStockQuantityService {
    @Autowired
    private ProductRepository productRepository;

    public CheckStockQuantityResponse execute(CheckStockQuantityRequest request) {

        Product productInStock = productRepository.findByTitle(request.getTitle());
        Long quantityFromUser = request.getQuantity();
        Long stockQuality = productInStock.getQuantity();

        List<CoreError> errors = new StockQuantityValidator().validate(quantityFromUser, stockQuality);

        return new CheckStockQuantityResponse(errors);
    }
}