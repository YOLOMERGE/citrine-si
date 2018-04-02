package com.dangerlibrary.citrine.api;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;
import com.dangerlibrary.citrine.UnitLexer;
import com.dangerlibrary.citrine.UnitParser;


import static com.dangerlibrary.citrine.api.Constants.UNIT_MAP;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParserTest {

    @Test
    public void testParser() {
        String input = "min/'*ha/(L*h)";
        String expectedName = "s/rad*m²/(m³*s)";
        SIUnit result = runTest(input);
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
        runTest(input);
    }

    @Test
    public void testDiv() {
        String input = "L/min";
        runTest(input);
    }

    @Test
    public void testLeftAssoc() {
        String input = "L/min/ha";
        runTest(input);
    }

    @Test
    public void testParens() {
        String input = "L/(min/ha)";
        runTest(input);
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
