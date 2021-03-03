package internet_store.core.core_error;

import lombok.Getter;

public class CoreError {
    @Getter
    private final String field;
    @Getter
    private final String message;

    public CoreError(String field, String message) {
        this.field = field;
        this.message = message;
    }
}