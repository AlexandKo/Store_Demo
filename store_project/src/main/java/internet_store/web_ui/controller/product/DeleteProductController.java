package internet_store.web_ui.controller.product;

import internet_store.core.domain.Product;
import internet_store.core.request.product.DeleteProductRequest;
import internet_store.core.request.product.SearchProductByTitleRequest;
import internet_store.core.response.product.DeleteProductResponse;
import internet_store.core.response.product.SearchProductByTitleResponse;
import internet_store.core.service.product.DeleteProductService;
import internet_store.core.service.product.SearchProductByTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeleteProductController {
    @Autowired
    private SearchProductByTitleService service;
    @Autowired
    private DeleteProductService deletedProductService;
    private Product deletedProduct;

    @GetMapping(value = "/service/delete_product")
    public String deleteProduct(ModelMap modelMap) {
        modelMap.addAttribute("error", "");
        return "product/delete_product";
    }

    @PostMapping(value = "/search_deleting_product")
    public String productForDeleting(@RequestParam(value = "search") String title, ModelMap modelMap) {
        SearchProductByTitleRequest request = new SearchProductByTitleRequest(title);
        SearchProductByTitleResponse response = service.execute(request);

        deletedProduct = response.getProduct();

        if (deletedProduct != null) {
            modelMap.addAttribute("error", "");
            modelMap.addAttribute("product", deletedProduct);
        } else {
            modelMap.addAttribute("error", "Search command error : Product not find in database");
        }
        return "product/delete_product";
    }

    @PostMapping(value = "/delete_product")
    public String deleteProductAction(ModelMap modelMap) {
        DeleteProductResponse response = deletedProductService.execute(new DeleteProductRequest(deletedProduct));

        if (response.hasErrors()) {
            modelMap.addAttribute("error", response.getErrors().get(0).getMessage());
        } else {
            modelMap.addAttribute("error", "Product deleted successfully");

            deletedProduct = null;
        }
        return "product/delete_product";
    }
}