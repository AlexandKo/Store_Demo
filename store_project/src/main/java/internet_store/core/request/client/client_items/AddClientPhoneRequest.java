package internet_store.core.request.client.client_items;

import lombok.Getter;

public class AddClientPhoneRequest {
    @Getter
    private final String phoneNumber;

    public AddClientPhoneRequest(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}