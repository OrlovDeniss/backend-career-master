package ru.intelinvest.career.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;
import ru.intelinvest.career.util.jackson.SecuritiesDataDeserializer;

import java.util.List;

@Data
@Builder
@JsonDeserialize(using = SecuritiesDataDeserializer.class)
public class SecuritiesData {

    @JsonProperty("charsetinfo")
    private Charset charset;

    @JsonProperty("securities")
    private List<Stock> stocks;
}
