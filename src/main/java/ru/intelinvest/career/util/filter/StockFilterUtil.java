package ru.intelinvest.career.util.filter;

import lombok.experimental.UtilityClass;
import ru.intelinvest.career.models.Stock;
import ru.intelinvest.career.models.StockFilter;

import java.util.List;
import java.util.Optional;

@UtilityClass
public class StockFilterUtil {

    public List<Stock> filter(List<Stock> stocks, StockFilter stockFilter) {
        var listLevel = stockFilter.getListLevel();
        var secId = stockFilter.getSecId();
        var lotSize = Optional.ofNullable(stockFilter.getLotSize());
        return stocks.stream()
                .filter(s -> listLevel.isEmpty() || listLevel.contains(s.getListlevel()))
                .filter(s -> secId.isEmpty()     || secId.contains(s.getSecid()))
                .filter(s -> lotSize.isEmpty()   || lotSize.get().equals(s.getLotsize()))
                .toList();
    }

}
