package internet_store.core.service.product;

import internet_store.core.core_error.CoreError;
import internet_store.core.request.product.CheckDuplicateRecordRequest;
import internet_store.core.response.product.CheckDuplicateRecordResponse;

import java.util.ArrayList;
import java.util.List;

public class CheckDuplicateRecordService {

    public CheckDuplicateRecordResponse execute(CheckDuplicateRecordRequest request) {

        boolean isDuplicate = request.getProduct() != null;

        if (isDuplicate) {
            List<CoreError> errors = new ArrayList<>();
            errors.add(new CoreError("error", "Record exist in database"));
            return new CheckDuplicateRecordResponse(errors);
        }
        return new CheckDuplicateRecordResponse(new ArrayList<>());
    }
}