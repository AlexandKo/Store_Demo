package internet_store.core.service.client;

import internet_store.core.domain.Client;
import internet_store.core.request.client.DeleteClientRequest;
import internet_store.core.response.client.DeleteClientResponse;
import internet_store.core.persistence.ClientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class DeleteClientServiceTest {
    @Mock
    private ClientRepository clientRepository;
    @InjectMocks
    private DeleteClientService deleteClientService;

    @Test
    public void shouldReturnNoError_ClientDelete() {
        Client client = new Client();
        client.setName("Name#1");

        Mockito.when(clientRepository.existsByName("Name#1")).thenReturn(true);
        Mockito.doNothing().when(clientRepository).delete(client);
        DeleteClientResponse response = deleteClientService
                .execute(new DeleteClientRequest(client));

        assertFalse(response.hasErrors());
    }

    @Test
    public void shouldReturnError_ClientNoExist() {
        Client client = new Client();
        client.setName("Name#1");

        Mockito.when(clientRepository.existsByName("Name#1")).thenReturn(false);
        DeleteClientResponse response = deleteClientService
                .execute(new DeleteClientRequest(client));

        assertTrue(response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("error", response.getErrors().get(0).getField());
        assertEquals("Client not exist in database", response.getErrors().get(0).getMessage());
    }

}