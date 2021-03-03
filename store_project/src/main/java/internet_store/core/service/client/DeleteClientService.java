package internet_store.core.service.client;

import internet_store.core.core_error.CoreError;
import internet_store.core.request.client.DeleteClientRequest;
import internet_store.core.response.client.DeleteClientResponse;
import internet_store.core.persistence.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DeleteClientService {
    @Autowired
    private ClientRepository clientRepository;

    public DeleteClientResponse execute(DeleteClientRequest request) {

        List<CoreError> errors = new ArrayList<>();

        boolean isExist = clientRepository.existsByName(request.getClient().getName());

        if (isExist) {
            clientRepository.delete(request.getClient());
        } else {
            errors.add(new CoreError("error", "Client not exist in database"));
        }
        return new DeleteClientResponse(errors);
    }
}