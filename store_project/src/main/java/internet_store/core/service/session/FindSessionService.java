package internet_store.core.service.session;

import internet_store.core.domain.Session;
import internet_store.core.persistence.SessionRepository;
import internet_store.core.request.session.FindSessionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindSessionService {
    @Autowired
    private SessionRepository sessionRepository;

    public List<Session> findSession(FindSessionRequest request) {
        return sessionRepository.getSessionBySessionId(request.getSessionId());
    }
}