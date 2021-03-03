package internet_store.web_ui.controller.client;

import internet_store.core.domain.Client;
import internet_store.core.request.client.DeleteClientRequest;
import internet_store.core.request.client.SearchClientRequest;
import internet_store.core.response.client.SearchClientResponse;
import internet_store.core.service.client.DeleteClientService;
import internet_store.core.service.client.SearchClientService;
import internet_store.core.persistence.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DeleteClientController {
    @Autowired
    private SearchClientService service;
    @Autowired
    private DeleteClientService deletedClientService;
    @Autowired
    private ClientRepository clientRepository;
    private Client deletedClient;

    @GetMapping(value = "/service/delete_client")
    public String deleteClient(ModelMap modelMap) {
        modelMap.addAttribute("error", "");
        return "client/delete_client";
    }

    @PostMapping(value = "/search_deleting_client")
    public String clientForDeleting(@RequestParam(value = "search") String searchClient,
                                    @RequestParam(value = "clientName") String searchBy, ModelMap modelMap) {

        SearchClientRequest request = new SearchClientRequest(searchClient, searchBy);
        SearchClientResponse response = service.execute(request);

        List<Client> clientsResultList = response.getClient();

        if (clientsResultList.size() > 1) {
            modelMap.addAttribute("error", "Search criterion "
                    + searchClient + " heve more one result. Please change search criterion and try again.");
            return "client/delete_client";
        }

        if (clientsResultList.size() == 0) {
            modelMap.addAttribute("error", "Search command error : Client not find in database");
            return "client/delete_client";
        }

        deletedClient = clientsResultList.get(0);
        modelMap.addAttribute("error", "");
        modelMap.addAttribute("client", deletedClient);
        return "client/delete_client";
    }

    @PostMapping(value = "/delete_client")
    public String deleteClientAction(ModelMap modelMap) {
        if (deletedClient == null) {
            modelMap.addAttribute("error", "Delete command error : Client's data not exist in database");
        } else {
            modelMap.addAttribute("error", "Product deleted successfully");
            deletedClientService.execute(new DeleteClientRequest(deletedClient));
            deletedClient = null;
        }
        return "client/delete_client";
    }
}