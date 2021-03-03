package internet_store.core.request.ordering;

import lombok.Getter;

public class CheckOrderIdRequest {
    @Getter
    private final long id;

    public CheckOrderIdRequest(long id) {
        this.id = id;
    }
}