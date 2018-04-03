package com.dangerlibrary.citrine.lib.util;

import com.google.common.collect.ImmutableMap;
import com.dangerlibrary.citrine.lib.model.SIUnit;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Constants {
    public static final MathContext CALC_CONTEXT = new MathContext(28, RoundingMode.HALF_UP);
    public static final MathContext DISPLAY_CONTEXT = new MathContext(14, RoundingMode.HALF_UP);

    public static final BigDecimal PI = new BigDecimal("3.141592653589793238462643383");
    public static final BigDecimal DEGREE = PI.divide(new BigDecimal(180), CALC_CONTEXT);
    public static final BigDecimal MINUTE = PI.divide(new BigDecimal(10800), CALC_CONTEXT);
    public static final BigDecimal SECOND = PI.divide(new BigDecimal(648000), CALC_CONTEXT);

    public static final ImmutableMap<String, SIUnit> UNIT_MAP = ImmutableMap.<String, SIUnit>builder()
            .put("min", SIUnit.newBuilder().setUnitName("s").setMultiplicationFactor("60").build())
            .put("minute", SIUnit.newBuilder().setUnitName("s").setMultiplicationFactor("60").build())
            .put("hour", SIUnit.newBuilder().setUnitName("s").setMultiplicationFactor("3600").build())
            .put("h", SIUnit.newBuilder().setUnitName("s").setMultiplicationFactor("3600").build())
            .put("degree", SIUnit.newBuilder().setUnitName("rad").setMultiplicationFactor(DEGREE).build()) // PI / 180
            .put("°", SIUnit.newBuilder().setUnitName("rad").setMultiplicationFactor(DEGREE).build())
            .put("'", SIUnit.newBuilder().setUnitName("rad").setMultiplicationFactor(MINUTE).build())
            .put("\"", SIUnit.newBuilder().setUnitName("rad").setMultiplicationFactor(SECOND).build())
            .put("second", SIUnit.newBuilder().setUnitName("rad").setMultiplicationFactor(SECOND).build())
            .put("hectare", SIUnit.newBuilder().setUnitName("m²").setMultiplicationFactor("10000").build())
            .put("ha", SIUnit.newBuilder().setUnitName("m²").setMultiplicationFactor("10000").build())
            .put("litre", SIUnit.newBuilder().setUnitName("m³").setMultiplicationFactor("0.001").build())
            .put("L", SIUnit.newBuilder().setUnitName("m³").setMultiplicationFactor("0.001").build())
            .put("tonne", SIUnit.newBuilder().setUnitName("kg").setMultiplicationFactor("1000").build())
            .put("t", SIUnit.newBuilder().setUnitName("kg").setMultiplicationFactor("1000").build())
            .build();

}
