package internet_store.core.service.ordering;

import internet_store.core.service.date_formats.ShortDateFormatCreatorImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CreateOrderNumberServiceTest {
    private final CreateOrderNumberService numberService = new CreateOrderNumberService();
    private final ShortDateFormatCreatorImpl shortDateFormatCreator = new ShortDateFormatCreatorImpl();

    @Test
    public void createFullOrderNumber() {
        numberService.setOrderNumber(1);
        numberService.createOrderNumber();

        String result = shortDateFormatCreator.createShortDateFormat() + "/1";

        assertEquals(result, numberService.getFullOrderNumber());
    }
}