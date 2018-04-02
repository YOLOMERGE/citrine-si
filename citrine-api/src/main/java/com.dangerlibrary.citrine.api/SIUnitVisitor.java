package com.dangerlibrary.citrine.api;

import com.dangerlibrary.citrine.UnitBaseVisitor;
import com.dangerlibrary.citrine.UnitParser;

import static com.dangerlibrary.citrine.api.Constants.UNIT_MAP;
import static com.dangerlibrary.citrine.api.Functions.divide;
import static com.dangerlibrary.citrine.api.Functions.multiply;

public class SIUnitVisitor extends UnitBaseVisitor<SIUnit> {

    @Override
    public SIUnit visitUnit(UnitParser.UnitContext ctx) {
        if(!UNIT_MAP.containsKey(ctx.WORD().getText())) {
            throw new IllegalArgumentException(String.format("%s is not a valid unit", ctx.WORD().getText()));
        }
        return UNIT_MAP.get(ctx.WORD().getText());
    }

    @Override
    public SIUnit visitOperation(UnitParser.OperationContext ctx) {
        SIUnit left = visit(ctx.left);
        SIUnit right = visit(ctx.right);
        String op = ctx.op.getText();
        switch (op.charAt(0)) {
            case '*':
                return multiply(left, right);
            case '/':
                return divide(left, right);
            default:
                throw new IllegalArgumentException("Invalid operation " + op);
        }
    }

    @Override
    public SIUnit visitParen(UnitParser.ParenContext ctx) {
        SIUnit unit = visit(ctx.expr());
        return SIUnit.newBuilder()
                .setMultiplicationFactor(unit.getMultiplicationFactor())
                .setUnitName('(' + unit.getUnitName() + ')')
                .build();
    }

    @Override
    public SIUnit visitInput(UnitParser.InputContext ctx) {
        return visit(ctx.expr());
    }
}
