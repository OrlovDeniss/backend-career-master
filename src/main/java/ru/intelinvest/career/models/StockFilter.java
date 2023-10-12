package ru.intelinvest.career.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class StockFilter {

    @JsonProperty("LISTLEVEL")
    private List<Integer> listLevel;

    @JsonProperty("SECID")
    private List<String> secId;

    @JsonProperty("LOTSIZE")
    private Integer lotSize;

}
