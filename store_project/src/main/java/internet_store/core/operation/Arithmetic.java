package internet_store.core.operation;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class Arithmetic {

    public BigDecimal multiplyBigDecimalAndRound(BigDecimal firstOperand, BigDecimal secondOperand, int roundValue) {
        return firstOperand.multiply(secondOperand).setScale(roundValue, RoundingMode.HALF_UP);
    }

    public BigDecimal divideBigDecimalAndRound(BigDecimal firstOperand, BigDecimal secondOperand, int roundValue) {
        final BigDecimal DIVIDE_TO_ZERO_ERROR = new BigDecimal("-1");
        if (secondOperand.compareTo(BigDecimal.ZERO) == 0) {
            return DIVIDE_TO_ZERO_ERROR;
        }
        return firstOperand.divide(secondOperand, roundValue, RoundingMode.HALF_UP);
    }
}