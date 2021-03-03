package internet_store.core.request.client;

import lombok.Getter;

public class SearchClientRequest {

    @Getter
    private final String searchRequest;
    @Getter
    private final String searchBy;

    public SearchClientRequest(String searchRequest, String searchBy) {
        this.searchRequest = searchRequest;
        this.searchBy = searchBy;
    }
}