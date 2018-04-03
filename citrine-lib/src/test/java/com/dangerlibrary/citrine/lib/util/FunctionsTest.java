package com.dangerlibrary.citrine.lib.util;

import com.dangerlibrary.citrine.lib.model.SIUnit;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.dangerlibrary.citrine.lib.util.Constants.DISPLAY_CONTEXT;
import static com.dangerlibrary.citrine.lib.util.Functions.divide;
import static com.dangerlibrary.citrine.lib.util.Functions.multiply;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FunctionsTest {

    @Test
    public void testMult() {
        SIUnit expected = SIUnit.newBuilder()
                .setUnitName("foo*bar")
                .setMultiplicationFactor(2d) // 15 digits
                .build();
        SIUnit left = SIUnit.newBuilder()
                .setUnitName("foo")
                .setMultiplicationFactor(1d) // 15 digits
                .build();
        SIUnit right = SIUnit.newBuilder()
                .setUnitName("bar")
                .setMultiplicationFactor(2d)
                .build();
        SIUnit result = multiply(left, right);
        assertEquals(expected, result);
        assertEquals(new BigDecimal("2", DISPLAY_CONTEXT), result.getDisplayFactor()); // round to 14 sig figs
    }

    @Test
    public void testDiv() {
        SIUnit expected = SIUnit.newBuilder()
                .setUnitName("foo/bar")
                .setMultiplicationFactor(2d) // 15 digits
                .build();
        SIUnit left = SIUnit.newBuilder()
                .setUnitName("foo")
                .setMultiplicationFactor(4d) // 15 digits
                .build();
        SIUnit right = SIUnit.newBuilder()
                .setUnitName("bar")
                .setMultiplicationFactor(2d)
                .build();
        SIUnit result = divide(left, right);
        assertEquals(expected, result);
        assertEquals(new BigDecimal("2", DISPLAY_CONTEXT), result.getDisplayFactor()); // round to 14 sig figs
    }

    @Test
    public void testMultNull() {
        SIUnit left = SIUnit.newBuilder()
                .setUnitName("foo")
                .setMultiplicationFactor(4d)
                .build();
        SIUnit right = null;
        assertThrows(IllegalArgumentException.class, () -> multiply(left, right));
    }

    @Test
    public void testDivNull() {
        SIUnit left = SIUnit.newBuilder()
                .setUnitName("foo")
                .setMultiplicationFactor(4d)
                .build();
        SIUnit right = null;
        assertThrows(IllegalArgumentException.class, () -> divide(left, right));
    }

    @Test
    public void testDivByZero() {
        SIUnit left = SIUnit.newBuilder()
                .setUnitName("foo")
                .setMultiplicationFactor(4d)
                .build();
        SIUnit right = SIUnit.newBuilder()
                .setUnitName("bar")
                .setMultiplicationFactor(0d)
                .build();
        assertThrows(IllegalArgumentException.class, () -> divide(left, right));
    }

}
