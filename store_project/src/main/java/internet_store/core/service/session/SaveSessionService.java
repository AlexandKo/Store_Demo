package internet_store.core.service.session;

import internet_store.core.domain.Client;
import internet_store.core.domain.Session;
import internet_store.core.operation.CheckClientToNull;
import internet_store.core.persistence.SessionRepository;
import internet_store.core.request.session.FindSessionRequest;
import internet_store.core.request.session.SaveClientSesionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaveSessionService {
    @Autowired
    private SessionService sessionService;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private FindSessionService findClientSessionService;

    public void saveClientSession(SaveClientSesionRequest request) {
        List<Session> sessionList = findClientSessionService.findSession(new FindSessionRequest(request.getSessionId()));
        Optional<List<Session>> optionalSessionsList = Optional.of(sessionList);

        optionalSessionsList.ifPresent(sessions -> sessions
                .forEach(session -> {
                    session.setClient(setClientForDatabase(sessionService.getSessionClient()));
                    sessionRepository.save(session);
                }));
    }

    private Client setClientForDatabase(Client sessionClient) {
        CheckClientToNull clientToNull = new CheckClientToNull();
        if (clientToNull.isClientNull(sessionClient)) {
            return null;
        }
        return sessionClient;
    }
}