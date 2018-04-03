package com.dangerlibrary.citrine.lib.model;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Objects;

import static com.dangerlibrary.citrine.lib.util.Constants.DISPLAY_CONTEXT;

public class SIUnit {
    private final String unitName;
    private final Double multiplicationFactor;

    private SIUnit(String unitName, Double multiplicationFactor) {
        this.unitName = unitName;
        this.multiplicationFactor = multiplicationFactor;
    }

    public String getUnitName() {
        return unitName;
    }

    public Double getMultiplicationFactor() {
        return multiplicationFactor;
    }

    @VisibleForTesting
    public BigDecimal getDisplayFactor() {
        return new BigDecimal(multiplicationFactor, DISPLAY_CONTEXT);
    }

    @Override
    public String toString() {
        return "SIUnit{" +
                "unitName='" + unitName + '\'' +
                ", multiplicationFactor=" + multiplicationFactor +
                '}';
    }

    public String toJson() {
        return "{" +
                "\"unit_name\":\"" + unitName + '\"' +
                ", \"multiplication_factor\":\"" + getDisplayFactor() + "\"" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SIUnit siUnit = (SIUnit) o;
        return Objects.equals(unitName, siUnit.unitName) &&
                Objects.equals(multiplicationFactor, siUnit.multiplicationFactor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitName, multiplicationFactor);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String unitName;
        private Double multiplicationFactor;

        public Builder setUnitName(String unitName) {
            this.unitName = unitName;
            return this;
        }

        public Builder setMultiplicationFactor(Double factor) {
            this.multiplicationFactor = factor;
            return this;
        }

        public SIUnit build() {
            Preconditions.checkNotNull(unitName, "Unit name cannot be null;");
            Preconditions.checkArgument(StringUtils.isNotBlank(unitName), "Unit name cannot be blank");
            Preconditions.checkNotNull(multiplicationFactor, "Multiplication Factor cannot be null");
            return new SIUnit(unitName, multiplicationFactor);
        }
    }
}
