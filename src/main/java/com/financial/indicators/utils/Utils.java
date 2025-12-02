package com.financial.indicators.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Utils {

    public static boolean isNullOrZero(BigDecimal v) {
        return v == null || v.compareTo(BigDecimal.ZERO) == 0;
    }

    public static BigDecimal bdDivide(BigDecimal a, BigDecimal b) {
        return a.divide(b, 12, RoundingMode.HALF_UP); 
    }

    public static BigDecimal bdMultiply(BigDecimal a, BigDecimal b) {
        return a.multiply(b);
    }
    
}
