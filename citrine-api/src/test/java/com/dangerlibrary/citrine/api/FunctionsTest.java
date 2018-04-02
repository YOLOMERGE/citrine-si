package com.dangerlibrary.citrine.api;

import com.dangerlibrary.citrine.api.model.SIUnit;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.dangerlibrary.citrine.api.util.Functions.divide;
import static com.dangerlibrary.citrine.api.util.Functions.multiply;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FunctionsTest {

    @Test
    public void testMult() {
        SIUnit expected = SIUnit.newBuilder()
                .setUnitName("foo*bar")
                .setMultiplicationFactor("2.00000000000000") // 15 digits
                .build();
        SIUnit left = SIUnit.newBuilder()
                .setUnitName("foo")
                .setMultiplicationFactor("1.00000000000000") // 15 digits
                .build();
        SIUnit right = SIUnit.newBuilder()
                .setUnitName("bar")
                .setMultiplicationFactor("2")
                .build();
        SIUnit result = multiply(left, right);
        assertEquals(expected, result);
        assertEquals(new BigDecimal("2.0000000000000"), result.getMultiplicationFactor()); // round to 14 sig figs
    }

    @Test
    public void testDiv() {
        SIUnit expected = SIUnit.newBuilder()
                .setUnitName("foo/bar")
                .setMultiplicationFactor("2.00000000000000") // 15 digits
                .build();
        SIUnit left = SIUnit.newBuilder()
                .setUnitName("foo")
                .setMultiplicationFactor("4.00000000000000") // 15 digits
                .build();
        SIUnit right = SIUnit.newBuilder()
                .setUnitName("bar")
                .setMultiplicationFactor("2")
                .build();
        SIUnit result = divide(left, right);
        assertEquals(expected, result);
        assertEquals(new BigDecimal("2.0000000000000"), result.getMultiplicationFactor()); // round to 14 sig figs
    }

    @Test
    public void testMultNull() {
        SIUnit left = SIUnit.newBuilder()
                .setUnitName("foo")
                .setMultiplicationFactor("4")
                .build();
        SIUnit right = null;
        assertThrows(IllegalArgumentException.class, () -> multiply(left, right));
    }

    @Test
    public void testDivNull() {
        SIUnit left = SIUnit.newBuilder()
                .setUnitName("foo")
                .setMultiplicationFactor("4")
                .build();
        SIUnit right = null;
        assertThrows(IllegalArgumentException.class, () -> divide(left, right));
    }

    @Test
    public void testDivByZero() {
        SIUnit left = SIUnit.newBuilder()
                .setUnitName("foo")
                .setMultiplicationFactor("4")
                .build();
        SIUnit right = SIUnit.newBuilder()
                .setUnitName("bar")
                .setMultiplicationFactor("0.0000")
                .build();
        assertThrows(IllegalArgumentException.class, () -> divide(left, right));
    }

}
