package internet_store.core.service.product;

import internet_store.core.core_error.CoreError;
import internet_store.core.domain.Product;
import internet_store.core.request.product.AddProductRequest;
import internet_store.core.request.product.CheckDuplicateRecordRequest;
import internet_store.core.request.product.product_items.*;
import internet_store.core.response.product.AddProductResponse;
import internet_store.core.response.product.CheckDuplicateRecordResponse;
import internet_store.core.response.product.product_item.*;
import internet_store.core.service.product.product_item.*;
import internet_store.core.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class AddProductService {
    private final AddProductTitleService titleService = new AddProductTitleService();
    private final AddProductDescriptionService descriptionService = new AddProductDescriptionService();
    private final AddProductQuantityService quantityService = new AddProductQuantityService();
    private final AddProductPriceService priceService = new AddProductPriceService();
    private final AddProductCategoryService categoryService = new AddProductCategoryService();
    private final CheckDuplicateRecordService duplicateRecordService = new CheckDuplicateRecordService();
    @Autowired
    private ProductRepository productRepository;
    private List<CoreError> errors;

    public AddProductResponse execute(AddProductRequest request) {
        List<List<CoreError>> allErrors = new ArrayList<>();

        checkHaveInputDataErrors(request, allErrors);

        if (errors.isEmpty()) {
            productRepository.save(request.getProduct());
        }
        return new AddProductResponse(errors);
    }

    private void checkHaveInputDataErrors(AddProductRequest request, List<List<CoreError>> allErrors) {
        AddProductTitleResponse titleResponse = titleService.execute(new AddProductTitleRequest
                (request.getProduct().getTitle()));
        allErrors.add(titleResponse.getErrors());
        AddProductDescriptionResponse descriptionResponse = descriptionService.execute(new AddProductDescriptionRequest
                (request.getProduct().getDescription()));
        allErrors.add(descriptionResponse.getErrors());
        AddProductQuantityResponse quantityResponse = quantityService.execute(new AddProductQuantityRequest
                (request.getProduct().getQuantity()));
        allErrors.add(quantityResponse.getErrors());
        AddProductPriceResponse priceResponse = priceService.execute(new AddProductPriceRequest
                (request.getProduct().getPrice()));
        allErrors.add(priceResponse.getErrors());
        AddProductCategoryResponse categoryResponse = categoryService.execute(new AddProductCategoryRequest
                (request.getProduct().getCategory()));
        allErrors.add(categoryResponse.getErrors());
        Product product = productRepository.findByTitle(request.getProduct().getTitle());
        CheckDuplicateRecordResponse duplicateResponse = duplicateRecordService.execute(new
                CheckDuplicateRecordRequest(product));
        allErrors.add(duplicateResponse.getErrors());

        Stream<CoreError> coreErrorStream = Stream.of();
        for (List<CoreError> error : allErrors) {
            if (error != null) {
                coreErrorStream = Stream.concat(coreErrorStream, error.stream());
            }
        }
        errors = coreErrorStream.collect(Collectors.toList());
    }
}