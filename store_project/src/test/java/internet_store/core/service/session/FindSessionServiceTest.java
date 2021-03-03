package internet_store.core.service.session;

import internet_store.core.domain.Session;
import internet_store.core.persistence.SessionRepository;
import internet_store.core.request.session.FindSessionRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class FindSessionServiceTest {
    @Mock
    private SessionRepository sessionRepository;
    @InjectMocks
    private FindSessionService findSessionService;

    @Test
    public void serviceReturn_EmptyList() {
        Mockito.when(sessionRepository.getSessionBySessionId("sessionId")).thenReturn(new ArrayList<>());
        List<Session> sessionList = findSessionService.findSession(new FindSessionRequest("sessionId"));

        assertEquals(0, sessionList.size());
    }

    @Test
    public void findOneSession() {
        List<Session> sessions = new ArrayList<>();
        Session session = new Session();
        session.setId(1L);
        session.setSessionId("sessionId");
        sessions.add(session);

        Mockito.when(sessionRepository.getSessionBySessionId("sessionId")).thenReturn(sessions);
        List<Session> sessionList = findSessionService.findSession(new FindSessionRequest("sessionId"));

        assertEquals(1, sessionList.size());
        assertEquals("sessionId", sessionList.get(0).getSessionId());
    }

    @Test
    public void findTwoSessions() {
        List<Session> sessions = new ArrayList<>();
        Session session_1 = new Session();
        session_1.setId(1L);
        session_1.setSessionId("sessionId");
        sessions.add(session_1);
        Session session_2 = new Session();
        session_2.setId(1L);
        session_2.setSessionId("sessionId");
        sessions.add(session_2);

        Mockito.when(sessionRepository.getSessionBySessionId("sessionId")).thenReturn(sessions);
        List<Session> sessionList = findSessionService.findSession(new FindSessionRequest("sessionId"));

        assertEquals(2, sessionList.size());
        assertEquals("sessionId", sessionList.get(0).getSessionId());
        assertEquals("sessionId", sessionList.get(1).getSessionId());
    }
}