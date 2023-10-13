package ru.intelinvest.career.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES;

@Data
@Builder
@JsonFormat(with = ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public class StockFilter {
    private List<Integer> listLevel;
    private List<String> secId;
    private Integer lotSize;
}
