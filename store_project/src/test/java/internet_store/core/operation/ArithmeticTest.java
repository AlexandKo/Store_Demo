package internet_store.core.operation;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ArithmeticTest {
    private final Arithmetic arithmetic = new Arithmetic();

    @Test
    public void multiply_round_4_digit() {
        BigDecimal result = arithmetic.multiplyBigDecimalAndRound(new BigDecimal("45.15"),
                new BigDecimal("10.05"), 4);
        assertEquals(new BigDecimal("453.7575"), result);
    }

    @Test
    public void multiply_round_3_digit() {
        BigDecimal result = arithmetic.multiplyBigDecimalAndRound(new BigDecimal("45.15"),
                new BigDecimal("10.05"), 3);
        assertEquals(new BigDecimal("453.758"), result);
    }

    @Test
    public void multiply_round_2_digit() {
        BigDecimal result = arithmetic.multiplyBigDecimalAndRound(new BigDecimal("45.15"),
                new BigDecimal("10.05"), 2);
        assertEquals(new BigDecimal("453.76"), result);
    }

    @Test
    public void divide_1() {
        BigDecimal result = arithmetic.divideBigDecimalAndRound(new BigDecimal("25.00"),
                new BigDecimal("5"), 2);

        assertEquals(new BigDecimal("5.00"), result);
    }

    @Test
    public void divide_2() {
        BigDecimal result = arithmetic.divideBigDecimalAndRound(new BigDecimal("135.40"),
                new BigDecimal("27.89"), 2);

        assertEquals(new BigDecimal("4.85"), result);
    }

    @Test
    public void divide_3() {
        BigDecimal result = arithmetic.divideBigDecimalAndRound(new BigDecimal("0"),
                new BigDecimal("77.15"), 2);

        assertEquals(new BigDecimal("0.00"), result);
    }

    @Test
    public void divide_4() {
        BigDecimal result = arithmetic.divideBigDecimalAndRound(new BigDecimal("58.47"),
                new BigDecimal("0"), 2);

        assertEquals(new BigDecimal("-1"), result);
    }
}