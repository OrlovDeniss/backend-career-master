package ru.intelinvest.career.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class EthereumBalance {
    private static final BigDecimal TETHER_POW = BigDecimal.valueOf(Math.pow(10, 12));

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("message")
    private String message;

    @JsonProperty("result")
    private BigDecimal ether;

    public BigDecimal getTether() {
        return ether.divide(TETHER_POW);
    }
}
