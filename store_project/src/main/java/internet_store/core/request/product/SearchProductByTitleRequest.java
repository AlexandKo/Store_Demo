package internet_store.core.request.product;

import lombok.Getter;

public class SearchProductByTitleRequest {
    @Getter
    private final String title;

    public SearchProductByTitleRequest(String title) {
        this.title = title;
    }
}