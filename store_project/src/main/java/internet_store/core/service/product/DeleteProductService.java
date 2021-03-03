package internet_store.core.service.product;

import internet_store.core.core_error.CoreError;
import internet_store.core.request.product.DeleteProductRequest;
import internet_store.core.response.product.DeleteProductResponse;
import internet_store.core.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DeleteProductService {
    @Autowired
    private ProductRepository productRepository;

    public DeleteProductResponse execute(DeleteProductRequest request) {

        List<CoreError> errors = new ArrayList<>();

        boolean isExist = productRepository.existsByTitle(request.getProduct().getTitle());

        if (isExist) {
            productRepository.delete(request.getProduct());
        } else {
            errors.add(new CoreError("error", "Product not exist in database"));
        }
        return new DeleteProductResponse(errors);
    }
}