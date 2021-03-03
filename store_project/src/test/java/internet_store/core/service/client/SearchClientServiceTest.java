package internet_store.core.service.client;

import internet_store.core.domain.Client;
import internet_store.core.request.client.SearchClientRequest;
import internet_store.core.response.client.SearchClientResponse;
import internet_store.core.persistence.ClientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class SearchClientServiceTest {
    @Mock
    private ClientRepository clientRepository;
    @InjectMocks
    private SearchClientService searchClientService;

    @Test
    public void returnClientByName() {
        List<Client> clientsIndatabase = new ArrayList<>();
        Client client = new Client();
        client.setName("Name#1");

        clientsIndatabase.add(client);

        Mockito.when(clientRepository.findByName("Name#1")).thenReturn(clientsIndatabase);
        SearchClientResponse response = searchClientService.execute(new SearchClientRequest("Name#1", "name"));

        assertEquals(1, response.getClient().size());
        assertEquals("Name#1", response.getClient().get(0).getName());
    }

    @Test
    public void returnClientByName_MoreOneRecordsFound() {
        List<Client> clientsIndatabase = new ArrayList<>();
        Client client_1 = new Client();
        client_1.setName("Name#1");

        Client client_2 = new Client();
        client_2.setName("Name#1");

        clientsIndatabase.add(client_1);
        clientsIndatabase.add(client_2);

        Mockito.when(clientRepository.findByName("Name#1")).thenReturn(clientsIndatabase);
        SearchClientResponse response = searchClientService.execute(new SearchClientRequest("Name#1", "name"));

        assertEquals(2, response.getClient().size());
        assertEquals("Name#1", response.getClient().get(0).getName());
        assertEquals("Name#1", response.getClient().get(1).getName());
    }

    @Test
    public void returnClientByName_RecordsNoFound() {
        List<Client> clientsIndatabase = new ArrayList<>();
        Client client = new Client();
        client.setName("Name#1");

        Mockito.when(clientRepository.findByName("Name#1")).thenReturn(clientsIndatabase);
        SearchClientResponse response = searchClientService.execute(new SearchClientRequest("Name#1", "name"));

        assertEquals(0, response.getClient().size());
    }

    @Test
    public void returnClientBySurname() {
        List<Client> clientsIndatabase = new ArrayList<>();
        Client client = new Client();
        client.setSurname("Surname#1");

        clientsIndatabase.add(client);

        Mockito.when(clientRepository.findBySurname("Surname#1")).thenReturn(clientsIndatabase);
        SearchClientResponse response = searchClientService.execute(new SearchClientRequest("Surname#1", "surname"));

        assertEquals(1, response.getClient().size());
        assertEquals("Surname#1", response.getClient().get(0).getSurname());
    }

    @Test
    public void returnClientBySurname_MoreOneRecordsFound() {
        List<Client> clientsIndatabase = new ArrayList<>();
        Client client_1 = new Client();
        client_1.setSurname("Surname#1");

        Client client_2 = new Client();
        client_2.setSurname("Surname#1");

        clientsIndatabase.add(client_1);
        clientsIndatabase.add(client_2);

        Mockito.when(clientRepository.findBySurname("Surname#1")).thenReturn(clientsIndatabase);
        SearchClientResponse response = searchClientService.execute(new SearchClientRequest("Surname#1", "surname"));

        assertEquals(2, response.getClient().size());
        assertEquals("Surname#1", response.getClient().get(0).getSurname());
        assertEquals("Surname#1", response.getClient().get(1).getSurname());
    }

    @Test
    public void returnClientBySurname_RecordsNoFound() {
        List<Client> clientsIndatabase = new ArrayList<>();
        Client client = new Client();
        client.setSurname("Surname#1");

        Mockito.when(clientRepository.findBySurname("Surname#1")).thenReturn(clientsIndatabase);
        SearchClientResponse response = searchClientService.execute(new SearchClientRequest("Surname#1", "surname"));

        assertEquals(0, response.getClient().size());
    }

    @Test
    public void returnClientByPhone() {
        List<Client> clientsIndatabase = new ArrayList<>();
        Client client = new Client();
        client.setPhoneNumber("29123456");

        clientsIndatabase.add(client);

        Mockito.when(clientRepository.findByPhoneNumber("29123456")).thenReturn(clientsIndatabase);
        SearchClientResponse response = searchClientService.execute(new SearchClientRequest("29123456", "phone"));

        assertEquals(1, response.getClient().size());
        assertEquals("29123456", response.getClient().get(0).getPhoneNumber());
    }

    @Test
    public void returnClientByPhone_MoreOneRecordsFound() {
        List<Client> clientsIndatabase = new ArrayList<>();
        Client client_1 = new Client();
        client_1.setPhoneNumber("29123456");

        Client client_2 = new Client();
        client_2.setPhoneNumber("29123456");

        clientsIndatabase.add(client_1);
        clientsIndatabase.add(client_2);

        Mockito.when(clientRepository.findByPhoneNumber("29123456")).thenReturn(clientsIndatabase);
        SearchClientResponse response = searchClientService.execute(new SearchClientRequest("29123456", "phone"));

        assertEquals(2, response.getClient().size());
        assertEquals("29123456", response.getClient().get(0).getPhoneNumber());
        assertEquals("29123456", response.getClient().get(1).getPhoneNumber());
    }

    @Test
    public void returnClientByPhone_RecordsNoFound() {
        List<Client> clientsIndatabase = new ArrayList<>();
        Client client = new Client();
        client.setPhoneNumber("29123456");

        Mockito.when(clientRepository.findByPhoneNumber("29123456")).thenReturn(clientsIndatabase);
        SearchClientResponse response = searchClientService.execute(new SearchClientRequest("29123456", "phone"));

        assertEquals(0, response.getClient().size());
    }

    @Test
    public void returnClientByEmail() {
        List<Client> clientsIndatabase = new ArrayList<>();
        Client client = new Client();
        client.setEmail("test@test.lv");

        clientsIndatabase.add(client);

        Mockito.when(clientRepository.findByEmail("test@test.lv")).thenReturn(clientsIndatabase);
        SearchClientResponse response = searchClientService.execute(new SearchClientRequest("test@test.lv", "email"));

        assertEquals(1, response.getClient().size());
        assertEquals("test@test.lv", response.getClient().get(0).getEmail());
    }

    @Test
    public void returnClientByEmail_MoreOneRecordsFound() {
        List<Client> clientsIndatabase = new ArrayList<>();
        Client client_1 = new Client();
        client_1.setEmail("test@test.lv");

        Client client_2 = new Client();
        client_2.setEmail("test@test.lv");

        clientsIndatabase.add(client_1);
        clientsIndatabase.add(client_2);

        Mockito.when(clientRepository.findByEmail("test@test.lv")).thenReturn(clientsIndatabase);
        SearchClientResponse response = searchClientService.execute(new SearchClientRequest("test@test.lv", "email"));

        assertEquals(2, response.getClient().size());
        assertEquals("test@test.lv", response.getClient().get(0).getEmail());
        assertEquals("test@test.lv", response.getClient().get(1).getEmail());
    }

    @Test
    public void returnClientByEmail_RecordsNoFound() {
        List<Client> clientsIndatabase = new ArrayList<>();
        Client client = new Client();
        client.setEmail("test@test.lv");

        Mockito.when(clientRepository.findByEmail("test@test.lv")).thenReturn(clientsIndatabase);
        SearchClientResponse response = searchClientService.execute(new SearchClientRequest("test@test.lv", "email"));

        assertEquals(0, response.getClient().size());
    }
}