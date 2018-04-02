package com.dangerlibrary.citrine.api;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Objects;

import static com.dangerlibrary.citrine.api.Constants.DISPLAY_CONTEXT;

public class SIUnit {
    private final String unitName;
    private final BigDecimal multiplicationFactor;

    private SIUnit(String unitName, BigDecimal multiplicationFactor) {
        this.unitName = unitName;
        this.multiplicationFactor = multiplicationFactor;
    }

    public String getUnitName() {
        return unitName;
    }

    public BigDecimal getMultiplicationFactor() {
        return multiplicationFactor.round(DISPLAY_CONTEXT);
    }

    public BigDecimal getExactFactor() {
        return multiplicationFactor;
    }

    @Override
    public String toString() {
        return "SIUnit{" +
                "unitName='" + unitName + '\'' +
                ", multiplicationFactor=" + getMultiplicationFactor() +
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
        private BigDecimal multiplicationFactor;

        public Builder setUnitName(String unitName) {
            this.unitName = unitName;
            return this;
        }

        public Builder setMultiplicationFactor(String factor) {
            this.multiplicationFactor = new BigDecimal(factor);
            return this;
        }

        public Builder setMultiplicationFactor(BigDecimal factor) {
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
