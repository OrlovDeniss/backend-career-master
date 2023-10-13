package ru.intelinvest.career.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.intelinvest.career.models.Stock;
import ru.intelinvest.career.models.StockFilter;
import ru.intelinvest.career.service.MoexService;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = MoexEndpoint.class)
class MoexEndpointTest {
    private final String requestMapping = "/moex/stocks";
    private final String xTotalStocksCount = "X-Total-Stocks-Count";
    private final String xPageStocksCount = "X-Page-Stocks-Count";
    private final int moexStocksSize = 22;
    private final int defaultPageSize = 10;
    private final EasyRandom generator = new EasyRandom();
    private final StockFilter stockFilter = StockFilter.builder().build();

    @MockBean
    private MoexService moexService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @SneakyThrows
    @Test
    void post_processIntegration_headersTest() {
        var stocks = generator.objects(Stock.class, moexStocksSize).toList();
        when(moexService.getStocks())
                .thenReturn(stocks);
        mockMvc.perform(post(requestMapping)
                        .content(mapper.writeValueAsString(stockFilter))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(defaultPageSize)))
                .andExpect(header().longValue(xTotalStocksCount, moexStocksSize))
                .andExpect(header().longValue(xPageStocksCount, defaultPageSize));
    }

}
