package internet_store.core.service.client.client_item;

import internet_store.core.request.client.client_items.AddClientPhoneRequest;
import internet_store.core.response.client.client_items.AddClientPhoneResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class AddClientPhoneServiceTest {
    private final AddClientPhoneService service = new AddClientPhoneService();

    @Test
    public void shouldReturnNoError_1() {
        AddClientPhoneRequest request = new AddClientPhoneRequest("+37122556688");
        AddClientPhoneResponse response = service.execute(request);
        assertFalse(response.hasErrors());
    }

    @Test
    public void shouldReturnNoError_2() {
        AddClientPhoneRequest request = new AddClientPhoneRequest("29546321");
        AddClientPhoneResponse response = service.execute(request);
        assertFalse(response.hasErrors());
    }

    @Test
    public void shouldReturnError_1() {
        AddClientPhoneRequest request = new AddClientPhoneRequest("1234567");
        AddClientPhoneResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("phone number unsupported format", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_2() {
        AddClientPhoneRequest request = new AddClientPhoneRequest("1");
        AddClientPhoneResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("phone number unsupported format", response.getErrors().get(1).getMessage());
    }

    @Test
    public void shouldReturnError_3() {
        AddClientPhoneRequest request = new AddClientPhoneRequest("no phone number");
        AddClientPhoneResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("phone number unsupported format", response.getErrors().get(1).getMessage());
    }
}