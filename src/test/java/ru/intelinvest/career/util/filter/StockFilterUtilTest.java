package ru.intelinvest.career.util.filter;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.intelinvest.career.models.Stock;
import ru.intelinvest.career.models.StockFilter;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StockFilterUtilTest {
    private final EasyRandom generator = new EasyRandom();
    private final int defaultFrom = 0;
    private final int defaultSize = 10;
    private final int moexStocksSize = 25;
    private List<Stock> stocks;

    @BeforeEach
    void beforeEach() {
        stocks = generator.objects(Stock.class, moexStocksSize).toList();
    }

    @Test
    void whenStockFilterHasNullArguments_thenReturnAllStocksDefaultSize() {
        var stockFilter = StockFilter.builder().build();
        var filteredStocks = StockFilterUtil.filter(stocks, stockFilter, defaultFrom, defaultSize);
        var referenceStocks = stocks.stream().limit(defaultSize).toList();
        assertEquals(defaultSize, filteredStocks.size());
        assertArrayEquals(referenceStocks.toArray(), filteredStocks.toArray());
    }

    @Test
    void whenStockFilterByListLevel_thenReturnStocksByListLevel() {
        var reference1 = stocks.get(generator.nextInt(moexStocksSize - 2));
        var reference2 = stocks.get(moexStocksSize - 1);
        var stockFilter = StockFilter.builder()
                .listLevel(List.of(reference1.getListlevel(), reference2.getListlevel()))
                .build();
        var filteredStocks = StockFilterUtil.filter(stocks, stockFilter, defaultFrom, defaultSize);
        var referenceStocks = List.of(reference1, reference2);
        assertEquals(referenceStocks.size(), filteredStocks.size());
        assertArrayEquals(referenceStocks.toArray(), filteredStocks.toArray());
    }

    @Test
    void whenStockFilterBySecId_thenReturnStocksBySecId() {
        var reference1 = stocks.get(generator.nextInt(moexStocksSize - 2));
        var reference2 = stocks.get(moexStocksSize - 1);
        var stockFilter = StockFilter.builder()
                .secId(List.of(reference1.getSecid(), reference2.getSecid()))
                .build();
        var filteredStocks = StockFilterUtil.filter(stocks, stockFilter, defaultFrom, defaultSize);
        var referenceStocks = List.of(reference1, reference2);
        assertEquals(referenceStocks.size(), filteredStocks.size());
        assertArrayEquals(referenceStocks.toArray(), filteredStocks.toArray());
    }

    @Test
    void whenStockFilterByLotSize_thenReturnStocksByLotSize() {
        var randomLotSize = 5555555;
        var reference1 = stocks.get(generator.nextInt(moexStocksSize - 2));
        var reference2 = stocks.get(moexStocksSize - 1);
        reference1.setLotsize(randomLotSize);
        reference2.setLotsize(randomLotSize);
        var stockFilter = StockFilter.builder()
                .lotSize(randomLotSize)
                .build();
        var filteredStocks = StockFilterUtil.filter(stocks, stockFilter, defaultFrom, defaultSize);
        var referenceStocks = List.of(reference1, reference2);
        assertEquals(referenceStocks.size(), filteredStocks.size());
        assertArrayEquals(referenceStocks.toArray(), filteredStocks.toArray());
    }

    @Test
    void whenStockFilterByCombineArg_thenReturnStocksByCombineArg() {
        var reference = stocks.get(generator.nextInt(moexStocksSize - 1));
        var stockFilter = StockFilter.builder()
                .secId(List.of(reference.getSecid()))
                .listLevel(List.of(reference.getListlevel()))
                .lotSize(reference.getLotsize())
                .build();
        var filteredStocks = StockFilterUtil.filter(stocks, stockFilter, defaultFrom, defaultSize);
        var referenceStocks = List.of(reference);
        assertEquals(referenceStocks.size(), filteredStocks.size());
        assertArrayEquals(referenceStocks.toArray(), filteredStocks.toArray());
    }

}
