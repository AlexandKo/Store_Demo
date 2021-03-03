package internet_store.core.core_error;

import java.util.List;

public class CoreErrorResponse {

    private List<CoreError> errors;

    public CoreErrorResponse() {
    }

    public CoreErrorResponse(List<CoreError> errors) {
        this.errors = errors;
    }

    public List<CoreError> getErrors() {
        return errors;
    }

    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }
}