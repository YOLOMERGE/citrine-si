package com.dangerlibrary.citrine.lib.util;

import com.google.common.collect.ImmutableMap;
import com.dangerlibrary.citrine.lib.model.SIUnit;

import java.math.MathContext;
import java.math.RoundingMode;

import static java.lang.Math.PI;

public class Constants {
    public static final MathContext DISPLAY_CONTEXT = new MathContext(14, RoundingMode.HALF_UP);

    // cribbed from Java.lang.Math, where it is private
    private static final double DEGREES_TO_RADIANS = 0.017453292519943295;
    private static final double ARCMINUTE = PI / 10800;
    private static final double ARCSECOND = PI / 648000;

    public static final ImmutableMap<String, SIUnit> UNIT_MAP = ImmutableMap.<String, SIUnit>builder()
            .put("min", SIUnit.newBuilder().setUnitName("s").setMultiplicationFactor(60d).build())
            .put("minute", SIUnit.newBuilder().setUnitName("s").setMultiplicationFactor(60d).build())
            .put("hour", SIUnit.newBuilder().setUnitName("s").setMultiplicationFactor(3600d).build())
            .put("h", SIUnit.newBuilder().setUnitName("s").setMultiplicationFactor(3600d).build())
            .put("day", SIUnit.newBuilder().setUnitName("s").setMultiplicationFactor(86400d).build())
            .put("d", SIUnit.newBuilder().setUnitName("s").setMultiplicationFactor(86400d).build())
            .put("degree", SIUnit.newBuilder().setUnitName("rad").setMultiplicationFactor(DEGREES_TO_RADIANS).build())
            .put("°", SIUnit.newBuilder().setUnitName("rad").setMultiplicationFactor(DEGREES_TO_RADIANS).build())
            .put("'", SIUnit.newBuilder().setUnitName("rad").setMultiplicationFactor(ARCMINUTE).build())
            .put("\"", SIUnit.newBuilder().setUnitName("rad").setMultiplicationFactor(ARCSECOND).build())
            .put("second", SIUnit.newBuilder().setUnitName("rad").setMultiplicationFactor(ARCSECOND).build())
            .put("hectare", SIUnit.newBuilder().setUnitName("m²").setMultiplicationFactor(10000d).build())
            .put("ha", SIUnit.newBuilder().setUnitName("m²").setMultiplicationFactor(10000d).build())
            .put("litre", SIUnit.newBuilder().setUnitName("m³").setMultiplicationFactor(0.001d).build())
            .put("L", SIUnit.newBuilder().setUnitName("m³").setMultiplicationFactor(0.001d).build())
            .put("tonne", SIUnit.newBuilder().setUnitName("kg").setMultiplicationFactor(1000d).build())
            .put("t", SIUnit.newBuilder().setUnitName("kg").setMultiplicationFactor(1000d).build())
            .build();

}
