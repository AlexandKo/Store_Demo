package internet_store.core.request.ordering;

import lombok.Getter;

public class OrderRequest {
    @Getter
    private final long id;

    public OrderRequest(long id) {
        this.id = id;
    }
}