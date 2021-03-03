package internet_store.core.request.session;

import lombok.Getter;

public class SaveClientSesionRequest {
    @Getter
    private final String sessionId;

    public SaveClientSesionRequest(String sessionId) {
        this.sessionId = sessionId;
    }
}