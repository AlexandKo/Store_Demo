package internet_store.web_ui.controller.client;

import internet_store.core.core_error.CoreError;
import internet_store.core.domain.Client;
import internet_store.core.request.client.AddClientRequest;
import internet_store.core.response.client.AddClientResponse;
import internet_store.core.service.client.AddClientService;
import internet_store.core.persistence.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddClientController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddClientService addClientService;

    @GetMapping(value = "/service/add_client")
    public String showAddClient(ModelMap modelMap) {
        modelMap.addAttribute("client", new Client());
        modelMap.addAttribute("errors", "");
        modelMap.addAttribute("info", "");
        return "client/add_client";
    }

    @GetMapping(value = "/back_client")
    public String backButtonClientFormPressed() {
        return "service/service";
    }

    @PostMapping(value = "/add_client")
    public String addNewClient(@ModelAttribute(value = "client") Client client, ModelMap modelMap) {
        StringBuilder allErrors = new StringBuilder();

        AddClientRequest request = new AddClientRequest(client);
        AddClientResponse response = addClientService.execute(request);

        for (CoreError error : response.getErrors()) {
            allErrors.append(error.getField()).append(" ").append(error.getMessage()).append(" ");
        }
        if (!(response.hasErrors())) {
            modelMap.addAttribute("info", "Client data successful add to database");
        } else {
            modelMap.addAttribute("info", "");
        }
        modelMap.addAttribute("errors", allErrors.toString());
        modelMap.addAttribute("client", client);
        return "client/add_client";
    }
}