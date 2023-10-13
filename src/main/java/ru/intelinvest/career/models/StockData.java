package ru.intelinvest.career.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;
import ru.intelinvest.career.util.jackson.StocksDataDeserializer;

import java.util.List;

@Data
@Builder
@JsonDeserialize(using = StocksDataDeserializer.class)
public class StockData {

    @JsonProperty("charsetinfo")
    private Charset charset;

    @JsonProperty("securities")
    private List<Stock> stocks;
}
