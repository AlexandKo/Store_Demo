package internet_store.core.request.client.client_items;

import lombok.Getter;

public class AddClientSurnameRequest {
    @Getter
    private final String surname;

    public AddClientSurnameRequest(String surname) {
        this.surname = surname;
    }
}