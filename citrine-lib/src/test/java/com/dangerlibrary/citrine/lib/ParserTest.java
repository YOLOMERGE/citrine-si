package com.dangerlibrary.citrine.lib;

import com.dangerlibrary.citrine.lib.model.SIUnit;
import com.dangerlibrary.citrine.lib.parse.SIUnitVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;
import com.dangerlibrary.citrine.UnitLexer;
import com.dangerlibrary.citrine.UnitParser;


import static com.dangerlibrary.citrine.lib.util.Constants.UNIT_MAP;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ParserTest {

    @Test
    public void testParens() {
        String input = "min/(min/h)";
        String expectedName = "s/(s/s)";
        SIUnit result = runTest(input);
        assertTrue(result.getMultiplicationFactor().equals(3600d));
        assertEquals(expectedName, result.getUnitName());
    }

    @Test
    public void exhaustiveSimpleTest() {
        UNIT_MAP.forEach((input, expected) -> {
            SIUnit result = runTest(input);
            assertEquals(expected, result);
        });
    }

    @Test
    public void testMult() {
        String input = "L*min";
        String expectedName = "m³*s";
        SIUnit result = runTest(input);
        assertTrue(result.getMultiplicationFactor().equals(0.06));
        assertEquals(expectedName, result.getUnitName());
    }

    @Test
    public void testDiv() {
        String input = "L/h";
        String expectedName = "m³/s";
        SIUnit result = runTest(input);
        assertEquals("2.7777777777778E-7", result.getDisplayFactor().toString());
        assertEquals(expectedName, result.getUnitName());
    }

    @Test
    public void testLeftAssoc() {
        String input = "L/min/ha";
        String expectedName = "m³/s/m²";
        SIUnit result = runTest(input);
        assertEquals("1.6666666666667E-9", result.getDisplayFactor().toString());
        assertEquals(expectedName, result.getUnitName());
    }

    private static SIUnit runTest(String input) {
        CodePointCharStream stream = CharStreams.fromString(input);
        UnitLexer lexer = new UnitLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        UnitParser parser = new UnitParser(tokens);
        SIUnit unit = new SIUnitVisitor().visitInput(parser.input());
        System.out.println("Input: " + input);
        System.out.println("Result: " + unit.toString());
        return unit;

    }
}
