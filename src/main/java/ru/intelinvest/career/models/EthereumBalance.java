package ru.intelinvest.career.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

import static com.fasterxml.jackson.annotation.JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES;

@Data
@JsonFormat(with = ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public class EthereumBalance {
    private static final BigDecimal TETHER_POW = BigDecimal.valueOf(Math.pow(10, 12));

    private Integer status;
    private String message;
    @JsonProperty("result")
    private BigDecimal ether;

    public BigDecimal getTether() {
        return ether.divide(TETHER_POW);
    }
}
