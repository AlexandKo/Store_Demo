package internet_store.core.service.client;

import internet_store.core.domain.Client;
import internet_store.core.request.client.SearchClientRequest;
import internet_store.core.response.client.SearchClientResponse;
import internet_store.core.persistence.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SearchClientService {
    @Autowired
    private ClientRepository clientRepository;

    public SearchClientResponse execute(SearchClientRequest request) {
        List<Client> client = new ArrayList<>();
        String searchClient = request.getSearchRequest();
        String searchBy = request.getSearchBy();

        switch (searchBy) {
            case "name" -> client = clientRepository.findByName(searchClient);
            case "surname" -> client = clientRepository.findBySurname(searchClient);
            case "phone" -> client = clientRepository.findByPhoneNumber(searchClient);
            case "email" -> client = clientRepository.findByEmail(searchClient);
        }
        return new SearchClientResponse(client);
    }
}