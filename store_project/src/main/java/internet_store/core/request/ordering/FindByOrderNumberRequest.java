package internet_store.core.request.ordering;

import lombok.Getter;

public class FindByOrderNumberRequest {
    @Getter
    private final int orderNumber;

    public FindByOrderNumberRequest(int orderNumber) {
        this.orderNumber = orderNumber;
    }
}