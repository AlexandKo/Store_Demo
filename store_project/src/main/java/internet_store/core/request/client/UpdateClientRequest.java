package internet_store.core.request.client;

import lombok.Getter;

public class UpdateClientRequest {
    @Getter
    private final long id;

    public UpdateClientRequest(long id) {
        this.id = id;
    }
}