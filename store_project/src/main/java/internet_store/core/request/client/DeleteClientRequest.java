package internet_store.core.request.client;

import internet_store.core.domain.Client;
import lombok.Getter;

public class DeleteClientRequest {
    @Getter
    private final Client client;

    public DeleteClientRequest(Client client) {
        this.client = client;
    }
}