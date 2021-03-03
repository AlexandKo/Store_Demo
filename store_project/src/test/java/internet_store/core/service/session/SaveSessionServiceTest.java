package internet_store.core.service.session;

import internet_store.core.domain.Client;
import internet_store.core.domain.Session;
import internet_store.core.persistence.SessionRepository;
import internet_store.core.request.session.FindSessionRequest;
import internet_store.core.request.session.SaveClientSesionRequest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class SaveSessionServiceTest {
    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();
    @Spy
    private FindSessionService findSessionService;
    @Mock
    private SessionRepository sessionRepository;
    @Mock
    private SessionService sessionService;
    @InjectMocks
    private SaveSessionService sessionClientService;

    @Test(expected = NullPointerException.class)
    public void saveService_1() {
        List<Session> sessions = new ArrayList<>();
        Client client = new Client();
        client.setId(1L);
        Session session = new Session();
        session.setId(1L);
        session.setSessionId("sessionId");
        sessions.add(session);

        Mockito.when(findSessionService.findSession(new FindSessionRequest("sessionId"))).thenReturn(sessions);
        Mockito.when(sessionService.getSessionClient()).thenReturn(client);
        Mockito.when(sessionRepository.save(session)).thenReturn(session);
        sessionClientService.saveClientSession(new SaveClientSesionRequest("sessionId"));

        Mockito.verify(sessionRepository, Mockito.times(1)).save(session);
        Optional<Long> optionalResult = Optional.of(1L);
        optionalResult.ifPresent(r -> assertEquals(r, session.getClient().getId()));
    }
}