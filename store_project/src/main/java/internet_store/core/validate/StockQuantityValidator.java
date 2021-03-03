package internet_store.core.validate;

import internet_store.core.core_error.CoreError;

import java.util.ArrayList;
import java.util.List;

public class StockQuantityValidator {

    public List<CoreError> validate(Long quantityFromUser, Long quantityInStock) {
        List<CoreError> errors = new ArrayList<>();

        if (quantityFromUser < 0) {
            errors.add(new CoreError("Quantity error", "Quantity can't less zero"));
        }

        if (quantityFromUser == 0) {
            errors.add(new CoreError("Quantity error", "Quantity can't equal zero"));
        }

        if (quantityFromUser > quantityInStock) {
            errors.add(new CoreError("Quantity error", "For this product available only "
                    + quantityInStock));
        }
        return errors;
    }
}