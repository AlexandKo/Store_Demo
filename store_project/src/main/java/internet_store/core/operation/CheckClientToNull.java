package internet_store.core.operation;

import internet_store.core.domain.Client;

import java.util.Objects;
import java.util.stream.Stream;

public class CheckClientToNull {

    public boolean isClientNull(Client client) {
        return Stream.of(client.getId(), client.getName(), client.getSurname(), client.getEmail(),
                client.getPhoneNumber()).allMatch(Objects::isNull);
    }
}