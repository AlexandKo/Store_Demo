package internet_store.core.request.client.client_items;

import lombok.Getter;

public class AddClientEmailRequest {
    @Getter
    private final String email;

    public AddClientEmailRequest(String email) {
        this.email = email;
    }
}