package internet_store.core.service.client.client_item;

import internet_store.core.request.client.client_items.AddClientSurnameRequest;
import internet_store.core.response.client.client_items.AddClientSurnameResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddClientSurnameServiceTest {
    private final AddClientSurnameService service = new AddClientSurnameService();

    @Test
    public void shouldReturnCurrentString() {
        AddClientSurnameRequest request = new AddClientSurnameRequest("Surname");
        AddClientSurnameResponse response = service.execute(request);
        assertEquals("Surname", response.getSurname());
    }

    @Test
    public void shouldReturnError_1() {
        AddClientSurnameRequest request = new AddClientSurnameRequest("");
        AddClientSurnameResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("Empty field", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_2() {
        AddClientSurnameRequest request = new AddClientSurnameRequest(null);
        AddClientSurnameResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("Empty field", response.getErrors().get(0).getMessage());
    }
}