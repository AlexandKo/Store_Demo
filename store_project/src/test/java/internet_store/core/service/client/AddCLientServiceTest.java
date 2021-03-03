package internet_store.core.service.client;

import internet_store.core.domain.Client;
import internet_store.core.request.client.AddClientRequest;
import internet_store.core.response.client.AddClientResponse;
import internet_store.core.persistence.ClientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AddCLientServiceTest {
    @Mock
    private ClientRepository clientRepository;
    @InjectMocks
    private AddClientService addClientService;

    @Test
    public void returnNoError() {
        Client client = new Client();
        client.setName("Name");
        client.setSurname("Surname");
        client.setPhoneNumber("29123456");
        client.setEmail("test@test.lv");

        Mockito.when(clientRepository.save(client)).thenReturn(client);
        AddClientResponse response = addClientService.execute(new AddClientRequest(client));

        assertFalse(response.hasErrors());
    }

    @Test
    public void returnError_AllErrors() {
        Client client = new Client();
        client.setName("");
        client.setSurname("");
        client.setPhoneNumber("test");
        client.setEmail("test_test_lv");

        AddClientResponse response = addClientService.execute(new AddClientRequest(client));

        assertTrue(response.hasErrors());
        assertEquals(5, response.getErrors().size());
        assertEquals("error", response.getErrors().get(0).getField());
        assertEquals("Empty field", response.getErrors().get(0).getMessage());
        assertEquals("error", response.getErrors().get(1).getField());
        assertEquals("Empty field", response.getErrors().get(1).getMessage());
        assertEquals("Phone number input error", response.getErrors().get(2).getField());
        assertEquals("phone number input error", response.getErrors().get(2).getMessage());
        assertEquals("Phone number input error", response.getErrors().get(3).getField());
        assertEquals("phone number unsupported format", response.getErrors().get(3).getMessage());
        assertEquals("Email input error", response.getErrors().get(4).getField());
        assertEquals("email unsupported format", response.getErrors().get(4).getMessage());
    }

    @Test
    public void returnError_NoName_Empty() {
        Client client = new Client();
        client.setName("");
        client.setSurname("Surname");
        client.setPhoneNumber("29123456");
        client.setEmail("test@test.lv");

        AddClientResponse response = addClientService.execute(new AddClientRequest(client));

        assertTrue(response.hasErrors());
        assertEquals("error", response.getErrors().get(0).getField());
        assertEquals("Empty field", response.getErrors().get(0).getMessage());
    }

    @Test
    public void returnError_NoName_NoExist() {
        Client client = new Client();
        client.setSurname("Surname");
        client.setPhoneNumber("29123456");
        client.setEmail("test@test.lv");

        AddClientResponse response = addClientService.execute(new AddClientRequest(client));

        assertTrue(response.hasErrors());
        assertEquals("error", response.getErrors().get(0).getField());
        assertEquals("Empty field", response.getErrors().get(0).getMessage());
    }

    @Test
    public void returnError_NoSurname_Empty() {
        Client client = new Client();
        client.setName("Name");
        client.setSurname("");
        client.setPhoneNumber("29123456");
        client.setEmail("test@test.lv");

        AddClientResponse response = addClientService.execute(new AddClientRequest(client));

        assertTrue(response.hasErrors());
        assertEquals("error", response.getErrors().get(0).getField());
        assertEquals("Empty field", response.getErrors().get(0).getMessage());
    }

    @Test
    public void returnError_NoSurname_NoExist() {
        Client client = new Client();
        client.setName("Name");
        client.setPhoneNumber("29123456");
        client.setEmail("test@test.lv");

        AddClientResponse response = addClientService.execute(new AddClientRequest(client));

        assertTrue(response.hasErrors());
        assertEquals("error", response.getErrors().get(0).getField());
        assertEquals("Empty field", response.getErrors().get(0).getMessage());
    }

    @Test
    public void returnError_Incorrect_Input_Phone_Number_Empty() {
        Client client = new Client();
        client.setName("Name");
        client.setSurname("Surname");
        client.setPhoneNumber("");
        client.setEmail("test@test.lv");

        AddClientResponse response = addClientService.execute(new AddClientRequest(client));

        assertTrue(response.hasErrors());
        assertEquals("Phone number input error", response.getErrors().get(0).getField());
        assertEquals("phone number input error", response.getErrors().get(0).getMessage());
    }

    @Test
    public void returnError_Incorrect_Input_Phone_Number() {
        Client client = new Client();
        client.setName("Name");
        client.setSurname("Surname");
        client.setPhoneNumber("This is a phone number");
        client.setEmail("test@test.lv");

        AddClientResponse response = addClientService.execute(new AddClientRequest(client));

        assertTrue(response.hasErrors());
        assertEquals("Phone number input error", response.getErrors().get(0).getField());
        assertEquals("phone number input error", response.getErrors().get(0).getMessage());
    }

    @Test
    public void returnError_Incorrect_Input_Phone_Number_Format_1() {
        Client client = new Client();
        client.setName("Name");
        client.setSurname("Surname");
        client.setPhoneNumber("2178951");
        client.setEmail("test@test.lv");

        AddClientResponse response = addClientService.execute(new AddClientRequest(client));

        assertTrue(response.hasErrors());
        assertEquals("Phone number input error", response.getErrors().get(0).getField());
        assertEquals("phone number unsupported format", response.getErrors().get(0).getMessage());
    }

    @Test
    public void returnError_Incorrect_Input_Phone_Number_Format_2() {
        Client client = new Client();
        client.setName("Name");
        client.setSurname("Surname");
        client.setPhoneNumber("2315974");
        client.setEmail("test@test.lv");

        AddClientResponse response = addClientService.execute(new AddClientRequest(client));

        assertTrue(response.hasErrors());
        assertEquals("Phone number input error", response.getErrors().get(0).getField());
        assertEquals("phone number unsupported format", response.getErrors().get(0).getMessage());
    }

    @Test
    public void returnError_Incorrect_Input_Phone_Number_Format_3() {
        Client client = new Client();
        client.setName("Name");
        client.setSurname("Surname");
        client.setPhoneNumber("2412398756");
        client.setEmail("test@test.lv");

        AddClientResponse response = addClientService.execute(new AddClientRequest(client));

        assertTrue(response.hasErrors());
        assertEquals("Phone number input error", response.getErrors().get(0).getField());
        assertEquals("phone number unsupported format", response.getErrors().get(0).getMessage());
    }

    @Test
    public void returnError_Correct_Input_Phone_Number_Format_1() {
        Client client = new Client();
        client.setName("Name");
        client.setSurname("Surname");
        client.setPhoneNumber("29123456");
        client.setEmail("test@test.lv");

        AddClientResponse response = addClientService.execute(new AddClientRequest(client));

        assertFalse(response.hasErrors());
    }

    @Test
    public void returnError_Correct_Input_Phone_Number_Format_2() {
        Client client = new Client();
        client.setName("Name");
        client.setSurname("Surname");
        client.setPhoneNumber("26123456");
        client.setEmail("test@test.lv");

        AddClientResponse response = addClientService.execute(new AddClientRequest(client));

        assertFalse(response.hasErrors());
    }

    @Test
    public void returnError_Correct_Input_Phone_Number_Format_3() {
        Client client = new Client();
        client.setName("Name");
        client.setSurname("Surname");
        client.setPhoneNumber("27123456");
        client.setEmail("test@test.lv");

        AddClientResponse response = addClientService.execute(new AddClientRequest(client));

        assertFalse(response.hasErrors());
    }

    @Test
    public void returnError_Incorrect_Email_Format_1() {
        Client client = new Client();
        client.setName("Name");
        client.setSurname("Surname");
        client.setPhoneNumber("29123456");
        client.setEmail("test@test");

        AddClientResponse response = addClientService.execute(new AddClientRequest(client));

        assertTrue(response.hasErrors());
        assertEquals("Email input error", response.getErrors().get(0).getField());
        assertEquals("email unsupported format", response.getErrors().get(0).getMessage());
    }

    @Test
    public void returnError_Incorrect_Email_Format_2() {
        Client client = new Client();
        client.setName("Name");
        client.setSurname("Surname");
        client.setPhoneNumber("29123456");
        client.setEmail("test_test.lv");

        AddClientResponse response = addClientService.execute(new AddClientRequest(client));

        assertTrue(response.hasErrors());
        assertEquals("Email input error", response.getErrors().get(0).getField());
        assertEquals("email unsupported format", response.getErrors().get(0).getMessage());
    }

    @Test
    public void returnError_Incorrect_Email_Format_3() {
        Client client = new Client();
        client.setName("Name");
        client.setSurname("Surname");
        client.setPhoneNumber("29123456");
        client.setEmail("test_test");

        AddClientResponse response = addClientService.execute(new AddClientRequest(client));

        assertTrue(response.hasErrors());
        assertEquals("Email input error", response.getErrors().get(0).getField());
        assertEquals("email unsupported format", response.getErrors().get(0).getMessage());
    }

    @Test
    public void returnError_Correct_Email_Format() {
        Client client = new Client();
        client.setName("Name");
        client.setSurname("Surname");
        client.setPhoneNumber("29123456");
        client.setEmail("test@test.lv");

        AddClientResponse response = addClientService.execute(new AddClientRequest(client));

        assertFalse(response.hasErrors());
    }
}