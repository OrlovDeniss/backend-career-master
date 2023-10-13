package ru.intelinvest.career.util.jakson;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import ru.intelinvest.career.models.StockData;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JsonTest
class StockDataDeserializerTest {

    private final String shortJson = """
            [
                {"charsetinfo": {"name": "utf-8"}},
                {"securities": [
                                    {"SECID": "ABIO", "BOARDID": "TQBR"},
                                    {"SECID": "ABRD", "BOARDID": "TQBR"}
                               ]
                }
            ]
            """;

    @Autowired
    private JacksonTester<StockData> jacksonTester;

    @SneakyThrows
    @Test
    void deserializeMoexSecuritiesTest() {
        StockData stockData = jacksonTester.parse(shortJson.getBytes()).getObject();
        assertEquals("utf-8", stockData.getCharset().getName());
        assertEquals(2, stockData.getStocks().size());
        assertEquals("ABIO", stockData.getStocks().get(0).getSecid());
        assertEquals("TQBR", stockData.getStocks().get(0).getBoardid());
        assertEquals("ABRD", stockData.getStocks().get(1).getSecid());
        assertEquals("TQBR", stockData.getStocks().get(1).getBoardid());
    }
}
