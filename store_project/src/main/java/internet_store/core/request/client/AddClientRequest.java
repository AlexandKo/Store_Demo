package internet_store.core.request.client;

import internet_store.core.domain.Client;
import lombok.Getter;

public class AddClientRequest {
    @Getter
    private final Client client;

    public AddClientRequest(Client client) {
        this.client = client;
    }
}