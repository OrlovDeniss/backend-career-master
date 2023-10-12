package ru.intelinvest.career.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Charset {
    @JsonProperty("name")
    private String name;
}
