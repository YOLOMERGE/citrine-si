package com.dangerlibrary.citrine.lib.util;

import com.dangerlibrary.citrine.lib.model.SIUnit;
import com.google.common.base.Preconditions;

public class Functions {
    public static SIUnit multiply(SIUnit left, SIUnit right) {
        Preconditions.checkArgument(left!=null, "Cannot multiply SIUnit by null.");
        Preconditions.checkArgument(right!=null, "Cannot multiply SIUnit by null.");
        return SIUnit.newBuilder()
                .setUnitName(left.getUnitName() + '*' + right.getUnitName())
                .setMultiplicationFactor(left.getMultiplicationFactor() * right.getMultiplicationFactor())
                .build();
    }

    public static SIUnit divide(SIUnit left, SIUnit right) {
        Preconditions.checkArgument(left!=null, "Cannot divide SIUnit by null.");
        Preconditions.checkArgument(right!=null, "Cannot divide SIUnit by null.");
        Preconditions.checkArgument(right.getMultiplicationFactor().compareTo(0d)!=0, "Cannot divide by zero");
        return SIUnit.newBuilder()
                .setUnitName(left.getUnitName() + '/' + right.getUnitName())
                .setMultiplicationFactor(left.getMultiplicationFactor() / right.getMultiplicationFactor())
                .build();
    }
}
