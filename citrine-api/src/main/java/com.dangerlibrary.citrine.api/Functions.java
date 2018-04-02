package com.dangerlibrary.citrine.api;

import com.google.common.base.Preconditions;

import java.math.BigDecimal;

import static com.dangerlibrary.citrine.api.Constants.CALC_CONTEXT;

public class Functions {
    public static SIUnit multiply(SIUnit left, SIUnit right) {
        Preconditions.checkArgument(left!=null, "Cannot multiply SIUnit by null.");
        Preconditions.checkArgument(right!=null, "Cannot multiply SIUnit by null.");
        return SIUnit.newBuilder()
                .setUnitName(left.getUnitName() + '*' + right.getUnitName())
                .setMultiplicationFactor(left.getExactFactor().multiply(right.getExactFactor()))
                .build();
    }

    public static SIUnit divide(SIUnit left, SIUnit right) {
        Preconditions.checkArgument(left!=null, "Cannot divide SIUnit by null.");
        Preconditions.checkArgument(right!=null, "Cannot divide SIUnit by null.");
        // BigDecimal.equals takes precision into account, so use compareTo instead
        Preconditions.checkArgument(right.getMultiplicationFactor().compareTo(BigDecimal.ZERO)!=0, "Cannot divide by zero");
        return SIUnit.newBuilder()
                .setUnitName(left.getUnitName() + '/' + right.getUnitName())
                .setMultiplicationFactor(left.getExactFactor().divide(right.getExactFactor(), CALC_CONTEXT))
                .build();
    }
}
