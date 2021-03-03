package internet_store.core.operation;

import internet_store.core.domain.Client;
import org.junit.Test;

import static org.junit.Assert.*;

public class CheckClientToNullTest {

    @Test
    public void clientObjectIsNull() {
        Client client = new Client();
        CheckClientToNull clientToNull = new CheckClientToNull();
        boolean result = clientToNull.isClientNull(client);

        assertTrue(result);
    }

    @Test
    public void clientObjectIsNotNull_SetId() {
        Client client = new Client();
        client.setId(1L);
        CheckClientToNull clientToNull = new CheckClientToNull();
        boolean result = clientToNull.isClientNull(client);

        assertFalse(result);
    }

    @Test
    public void clientObjectIsNotNull_SetName() {
        Client client = new Client();
        client.setName("Name");
        CheckClientToNull clientToNull = new CheckClientToNull();
        boolean result = clientToNull.isClientNull(client);

        assertFalse(result);
    }

    @Test
    public void clientObjectIsNotNull_SetSurname() {
        Client client = new Client();
        client.setSurname("Surname");
        CheckClientToNull clientToNull = new CheckClientToNull();
        boolean result = clientToNull.isClientNull(client);

        assertFalse(result);
    }

    @Test
    public void clientObjectIsNotNull_SetEmail() {
        Client client = new Client();
        client.setEmail("email");
        CheckClientToNull clientToNull = new CheckClientToNull();
        boolean result = clientToNull.isClientNull(client);

        assertFalse(result);
    }

    @Test
    public void clientObjectIsNotNull_SetPhoneNumber() {
        Client client = new Client();
        client.setPhoneNumber("phone number");
        CheckClientToNull clientToNull = new CheckClientToNull();
        boolean result = clientToNull.isClientNull(client);

        assertFalse(result);
    }

    @Test
    public void clientObjectIsNotNull_SetAllFields() {
        Client client = new Client();
        client.setId(1L);
        client.setName("Name");
        client.setEmail("email");
        client.setPhoneNumber("phone number");
        CheckClientToNull clientToNull = new CheckClientToNull();
        boolean result = clientToNull.isClientNull(client);

        assertFalse(result);
    }
}