package internet_store.web_ui.controller.cart;

import internet_store.core.core_error.CoreError;
import internet_store.core.domain.Client;
import internet_store.core.persistence.ClientRepository;
import internet_store.core.request.client.AddClientRequest;
import internet_store.core.request.session.SaveClientSesionRequest;
import internet_store.core.response.client.AddClientResponse;
import internet_store.core.service.cart.CartProductsCountService;
import internet_store.core.service.client.AddClientService;
import internet_store.core.service.session.SaveSessionService;
import internet_store.core.service.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartAddClientController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddClientService addClientService;
    @Autowired
    private CartProductsCountService cartCountService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private SaveSessionService saveSessionService;

    @GetMapping(value = "/estore/cart_add_client")
    public String getCartClient(ModelMap modelMap) {
        modelMap.addAttribute("client", sessionService.getSessionClient());
        modelMap.addAttribute("cartCount", cartCountService.getCartCount());
        modelMap.addAttribute("errors", "");
        modelMap.addAttribute("info", "");
        modelMap.addAttribute("disabled", "false");
        return "cart/cart_add_client";
    }

    @PostMapping(value = "/estore/order_add_client")
    public String addNewClient(@ModelAttribute(value = "client") Client client, ModelMap modelMap) {
        StringBuilder allErrors = new StringBuilder();

        AddClientRequest request = new AddClientRequest(client);
        AddClientResponse response = addClientService.execute(request);

        for (CoreError error : response.getErrors()) {
            allErrors.append(error.getField()).append(" ").append(error.getMessage()).append(" ");
        }
        if (!(response.hasErrors())) {
            clientRepository.save(client);
            sessionService.setSessionClient(client);
            saveSessionService.saveClientSession(new SaveClientSesionRequest(sessionService.getSessionId()));

            modelMap.addAttribute("info", "Client data successful created");
        } else {
            modelMap.addAttribute("info", "");
        }

        modelMap.addAttribute("cartCount", cartCountService.getCartCount());
        modelMap.addAttribute("errors", allErrors.toString());
        modelMap.addAttribute("client", client);
        return "cart/cart_add_client";
    }
}