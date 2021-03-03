package internet_store.core.service.client.client_item;

import internet_store.core.request.client.client_items.AddClientEmailRequest;
import internet_store.core.response.client.client_items.AddClientEmailResponse;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddClientEmailServiceTest {

    private final AddClientEmailService service = new AddClientEmailService();

    @Test
    public void shouldReturn_NoError() {
        AddClientEmailRequest request = new AddClientEmailRequest("alex@alex.com");
        AddClientEmailResponse response = service.execute(request);
        assertFalse(response.hasErrors());
    }

    @Test
    public void shouldReturnError_1() {
        AddClientEmailRequest request = new AddClientEmailRequest("alex_alex.com");
        AddClientEmailResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("email unsupported format", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_2() {
        AddClientEmailRequest request = new AddClientEmailRequest("");
        AddClientEmailResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("email unsupported format", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_3() {
        AddClientEmailRequest request = new AddClientEmailRequest(null);
        AddClientEmailResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("email unsupported format", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_4() {
        AddClientEmailRequest request = new AddClientEmailRequest("alex@alex");
        AddClientEmailResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("email unsupported format", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_5() {
        AddClientEmailRequest request = new AddClientEmailRequest("no name");
        AddClientEmailResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("email unsupported format", response.getErrors().get(0).getMessage());
    }

}