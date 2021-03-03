package internet_store.core.service.product;

import internet_store.core.domain.Product;
import internet_store.core.request.product.SearchProductByTitleRequest;
import internet_store.core.response.product.SearchProductByTitleResponse;
import internet_store.core.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SearchProductByTitleService {
    @Autowired
    private ProductRepository productRepository;

    public SearchProductByTitleResponse execute(SearchProductByTitleRequest request) {
        Product product = productRepository.findByTitle(request.getTitle());

        return new SearchProductByTitleResponse(product);
    }
}