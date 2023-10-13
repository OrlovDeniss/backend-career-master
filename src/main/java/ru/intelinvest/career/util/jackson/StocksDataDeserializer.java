package ru.intelinvest.career.util.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import ru.intelinvest.career.models.Charset;
import ru.intelinvest.career.models.Stock;
import ru.intelinvest.career.models.StockData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class StocksDataDeserializer extends JsonDeserializer<StockData> {
    private static final String CHARSET_INFO = "charsetinfo";
    private static final String SECURITIES = "securities";

    private final ObjectMapper objectMapper;

    @Override
    public StockData deserialize(JsonParser jsonParser,
                                 DeserializationContext deserializationContext) throws IOException {
        JsonNode rootNode = jsonParser.getCodec().readTree(jsonParser);
        return StockData.builder()
                .charset(getCharset(rootNode))
                .stocks(getStocks(rootNode))
                .build();
    }

    private Charset getCharset(JsonNode rootNode) throws JsonProcessingException {
        return objectMapper.treeToValue(rootNode.findValue(CHARSET_INFO), Charset.class);
    }

    private List<Stock> getStocks(JsonNode rootNode) throws JsonProcessingException {
        JsonNode stocksNode = rootNode.findValue(SECURITIES);
        List<Stock> stocks = new ArrayList<>(stocksNode.size());
        for (JsonNode s : stocksNode) {
            stocks.add(objectMapper.treeToValue(s, Stock.class));
        }
        return stocks;
    }
}
