package com.wex.transaction.utils.handler;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ArroundAmount {
    public static BigDecimal arroundUp(BigDecimal valor) {
        if (valor == null) {
            return null;
        }
        return valor.setScale(2, RoundingMode.HALF_UP);
    }
}
