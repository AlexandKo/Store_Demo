package internet_store.core.request.session;

import lombok.Getter;

public class RestoreRequest {
    @Getter
    private final String sessionId;

    public RestoreRequest(String sessionId) {
        this.sessionId = sessionId;
    }
}