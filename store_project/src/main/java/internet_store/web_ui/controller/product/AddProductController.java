package internet_store.web_ui.controller.product;

import internet_store.core.core_error.CoreError;
import internet_store.core.domain.Product;
import internet_store.core.request.product.AddProductRequest;
import internet_store.core.response.product.AddProductResponse;
import internet_store.core.service.product.AddProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddProductController {
    @Autowired
    private AddProductService addProductService;

    @GetMapping(value = "/service/add_product")
    public String showAddProduct(ModelMap modelMap) {
        modelMap.addAttribute("product", new Product());
        modelMap.addAttribute("errors", "");
        modelMap.addAttribute("info", "");
        return "product/add_product";
    }

    @GetMapping(value = "/back_product")
    public String backButtonProductFormPressed() {
        return "service/service";
    }

    @PostMapping(value = "/add_product")
    public String addNewProduct(@ModelAttribute(value = "product") Product product, ModelMap modelMap) {
        StringBuilder allErrors = new StringBuilder();

        AddProductRequest addProductRequest = new AddProductRequest(product);
        AddProductResponse response = addProductService.execute(addProductRequest);

        for (CoreError error : response.getErrors()) {
            allErrors.append(error.getField()).append(" ").append(error.getMessage()).append(" ");
        }
        if (!(response.hasErrors())) {
            modelMap.addAttribute("info", "Product successful add to database");
        } else {
            modelMap.addAttribute("info", "");
        }
        modelMap.addAttribute("errors", allErrors.toString());
        modelMap.addAttribute("product", product);
        return "product/add_product";
    }
}