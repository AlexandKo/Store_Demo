package internet_store.core.request.session;

import lombok.Getter;

public class FindSessionRequest {
    @Getter
    private final String sessionId;

    public FindSessionRequest(String sessionId) {
        this.sessionId = sessionId;
    }
}