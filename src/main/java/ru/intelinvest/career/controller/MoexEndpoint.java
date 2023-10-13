/*
 * STRICTLY CONFIDENTIAL
 * TRADE SECRET
 * PROPRIETARY:
 *       "Intelinvest" Ltd, TIN 1655386205
 *       420107, REPUBLIC OF TATARSTAN, KAZAN CITY, SPARTAKOVSKAYA STREET, HOUSE 2, ROOM 119
 * (c) "Intelinvest" Ltd, 2019
 *
 * СТРОГО КОНФИДЕНЦИАЛЬНО
 * КОММЕРЧЕСКАЯ ТАЙНА
 * СОБСТВЕННИК:
 *       ООО "Интеллектуальные инвестиции", ИНН 1655386205
 *       420107, РЕСПУБЛИКА ТАТАРСТАН, ГОРОД КАЗАНЬ, УЛИЦА СПАРТАКОВСКАЯ, ДОМ 2, ПОМЕЩЕНИЕ 119
 * (c) ООО "Интеллектуальные инвестиции", 2019
 */

package ru.intelinvest.career.controller;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.intelinvest.career.models.Stock;
import ru.intelinvest.career.models.StockFilter;
import ru.intelinvest.career.service.MoexService;
import ru.intelinvest.career.util.filter.StockFilterUtil;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/moex")
public class MoexEndpoint {

    private static final String X_TOTAL_STOCKS_COUNT = "X-Total-Stocks-Count";
    private static final String X_PAGE_STOCKS_COUNT = "X-Page-Stocks-Count";
    private static final String FROM = "0";
    private static final String SIZE = "10";

    private final MoexService moexService;

    @PostMapping("stocks")
    public ResponseEntity<List<Stock>> processIntegration(
            @RequestBody StockFilter stockFilter,
            @RequestParam(required = false, defaultValue = FROM) @PositiveOrZero Integer from,
            @RequestParam(required = false, defaultValue = SIZE) @Positive Integer size) {
        var stocks = moexService.getStocks();
        var filteredStocks = StockFilterUtil.filter(stocks, stockFilter, from, size);
        var headers = buildHeaders(stocks.size(), filteredStocks.size());
        return new ResponseEntity<>(filteredStocks, headers, HttpStatus.OK);
    }

    private HttpHeaders buildHeaders(Integer stocksSize, Integer filteredStocksSize) {
        var headers = new HttpHeaders();
        headers.add(X_TOTAL_STOCKS_COUNT, String.valueOf(stocksSize));
        headers.add(X_PAGE_STOCKS_COUNT, String.valueOf(filteredStocksSize));
        return headers;
    }
}
