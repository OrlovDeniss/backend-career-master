package ru.intelinvest.career.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class StockFilter {

    @JsonProperty("LISTLEVEL")
    private List<Integer> listLevel = Collections.emptyList();

    @JsonProperty("SECID")
    private List<String> secId = Collections.emptyList();

    @JsonProperty("LOTSIZE")
    private Integer lotSize;

}
