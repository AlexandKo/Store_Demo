package internet_store.core.service.session;

import internet_store.core.domain.Client;
import internet_store.core.domain.Session;
import internet_store.core.persistence.SessionRepository;
import internet_store.core.request.session.FindSessionRequest;
import internet_store.core.request.session.RestoreRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private FindSessionService findSessionService;
    @Setter
    @Getter
    private String sessionId;
    @Getter
    @Setter
    private Client sessionClient;

    public void restoreClientLastSession(RestoreRequest request) {
        boolean isSessionExists = sessionRepository.existsBySessionId(request.getSessionId());

        if (!(isSessionExists)) {
            Session session = new Session();
            session.setSessionId(request.getSessionId());
            session.setClient(null);
            sessionRepository.save(session);
        } else {
            List<Session> sessions = findSessionService.findSession(new FindSessionRequest(request.getSessionId()));
            setNewSessionClient(sessions);
        }
    }

    private void setNewSessionClient(List<Session> sessions) {
        sessionClient = sessions.get(0).getClient();
        if (sessionClient == null) {
            sessionClient = new Client();
        }
    }
}