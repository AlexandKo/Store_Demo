package internet_store.core.response.client;

import internet_store.core.domain.Client;
import lombok.Getter;

import java.util.List;

public class SearchClientResponse {
    @Getter
    private final List<Client> client;

    public SearchClientResponse(List<Client> client) {
        this.client = client;
    }
}