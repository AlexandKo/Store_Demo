package internet_store.core.service.client;

import internet_store.core.core_error.CoreError;
import internet_store.core.request.client.AddClientRequest;
import internet_store.core.request.client.client_items.AddClientEmailRequest;
import internet_store.core.request.client.client_items.AddClientNameRequest;
import internet_store.core.request.client.client_items.AddClientPhoneRequest;
import internet_store.core.request.client.client_items.AddClientSurnameRequest;
import internet_store.core.response.client.AddClientResponse;
import internet_store.core.response.client.client_items.AddClientEmailResponse;
import internet_store.core.response.client.client_items.AddClientNameResponse;
import internet_store.core.response.client.client_items.AddClientPhoneResponse;
import internet_store.core.response.client.client_items.AddClientSurnameResponse;
import internet_store.core.service.client.client_item.AddClientEmailService;
import internet_store.core.service.client.client_item.AddClientNameService;
import internet_store.core.service.client.client_item.AddClientPhoneService;
import internet_store.core.service.client.client_item.AddClientSurnameService;
import internet_store.core.persistence.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class AddClientService {
    private final AddClientNameService nameService = new AddClientNameService();
    private final AddClientSurnameService surnameService = new AddClientSurnameService();
    private final AddClientPhoneService phoneService = new AddClientPhoneService();
    private final AddClientEmailService emailService = new AddClientEmailService();
    @Autowired
    private ClientRepository clientRepository;
    private List<CoreError> errors;

    public AddClientResponse execute(AddClientRequest request) {
        List<List<CoreError>> allErrors = new ArrayList<>();

        checkHaveInputDataErrors(request, allErrors);

        if (errors.isEmpty()) {
            clientRepository.save(request.getClient());
        }
        return new AddClientResponse(errors);
    }

    private void checkHaveInputDataErrors(AddClientRequest request, List<List<CoreError>> allErrors) {
        AddClientNameResponse nameResponse = nameService.execute(new AddClientNameRequest
                (request.getClient().getName()));
        allErrors.add(nameResponse.getErrors());
        AddClientSurnameResponse surnameResponse = surnameService.execute(new AddClientSurnameRequest
                (request.getClient().getSurname()));
        allErrors.add(surnameResponse.getErrors());
        AddClientPhoneResponse phoneResponse = phoneService.execute(new AddClientPhoneRequest
                (request.getClient().getPhoneNumber()));
        allErrors.add(phoneResponse.getErrors());
        AddClientEmailResponse emailResponse = emailService.execute(new AddClientEmailRequest
                (request.getClient().getEmail()));
        allErrors.add(emailResponse.getErrors());

        Stream<CoreError> coreErrorStream = Stream.of();
        for (List<CoreError> error : allErrors) {
            if (error != null) {
                coreErrorStream = Stream.concat(coreErrorStream, error.stream());
            }
        }
        errors = coreErrorStream.collect(Collectors.toList());
    }
}