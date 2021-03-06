package internet_store.web_ui.controller.search;

import internet_store.core.persistence.CartRepository;
import internet_store.core.request.cart.AddProductToCartRequest;
import internet_store.core.request.product.CheckStockQuantityRequest;
import internet_store.core.response.product.CheckStockQuantityResponse;
import internet_store.core.search.SearchByProductPricePagingService;
import internet_store.core.service.cart.AddToCartService;
import internet_store.core.service.cart.CartProductsCountService;
import internet_store.core.service.product.CheckStockQuantityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchByProductPriceController {
    @Autowired
    private CartProductsCountService cartCountService;
    @Autowired
    private CheckStockQuantityService quantityService;
    @Autowired
    private AddToCartService addToCartService;
    @Autowired
    private SearchByProductPricePagingService paging;
    @Autowired
    private CartRepository CartRepository;

    @PostMapping("/estore/search_product_price")
    public String startSearchByPrice(@RequestParam(value = "search") String search, ModelMap modelMap) {
        paging.startPaging(search);

        refreshData(modelMap);
        modelMap.addAttribute("info", "");
        modelMap.addAttribute("error", "");
        return "search/search_by_product_price";
    }

    @GetMapping(value = "service_login_search_price")
    public String loginFromSearchPrice() {
        return "service/service";
    }

    @PostMapping(value = "/estore/buy_product_search_price")
    public String productBuyFromSearchPrice(@RequestParam(value = "quantity") Long quantity,
                                            @RequestParam(value = "productTitle") String title, ModelMap modelMap) {

        CheckStockQuantityRequest request = new CheckStockQuantityRequest(quantity, title);
        CheckStockQuantityResponse response = quantityService.execute(request);

        if (response.hasErrors()) {
            String errorMessage = response.getErrors().get(0).getMessage();
            modelMap.addAttribute("error", errorMessage);
        } else {
            AddProductToCartRequest addRequest = new AddProductToCartRequest(quantity, title);
            addToCartService.execute(addRequest);
            modelMap.addAttribute("error", "");
        }
        refreshData(modelMap);
        modelMap.addAttribute("info", "");
        return "search/search_by_product_price";
    }

    @PostMapping(value = "/estore/search_price_next")
    public String nextPageSearchPrice(ModelMap modelMap) {
        if (paging.isLastPage()) {
            modelMap.addAttribute("info", "View control : Last page");
        } else {
            modelMap.addAttribute("info", "");
            paging.nextPage(true);
        }
        refreshData(modelMap);
        modelMap.addAttribute("error", "");
        return "search/search_by_product_price";
    }

    @PostMapping(value = "/estore/search_price_prev")
    public String prevPageSearchPrice(ModelMap modelMap) {
        if (paging.isFirstPage()) {
            modelMap.addAttribute("info", "View control : First page");
        } else {
            modelMap.addAttribute("info", "");
            paging.nextPage(false);
        }
        refreshData(modelMap);
        modelMap.addAttribute("error", "");
        return "search/search_by_product_price";
    }

    private void refreshData(ModelMap modelMap) {
        modelMap.addAttribute("products", paging.getListOnePage());
        modelMap.addAttribute("cartCount", cartCountService.getCartCount());
        modelMap.addAttribute("pages", "Page " + paging.getCurrentPage() + " of "
                + paging.getPagesQuantity());
    }
}