package ru.intelinvest.career.util.filter;

import lombok.experimental.UtilityClass;
import ru.intelinvest.career.models.Stock;
import ru.intelinvest.career.models.StockFilter;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@UtilityClass
public class StockFilterUtil {

    public List<Stock> filter(List<Stock> stocks,
                              StockFilter stockFilter,
                              Integer from,
                              Integer size) {
        var listLevel = Optional.ofNullable(stockFilter.getListLevel()).orElse(Collections.emptyList());
        var secId = Optional.ofNullable(stockFilter.getSecId()).orElse(Collections.emptyList());
        var lotSize = Optional.ofNullable(stockFilter.getLotSize());
        return stocks.stream()
                .filter(s -> listLevel.isEmpty() || listLevel.contains(s.getListlevel()))
                .filter(s -> secId.isEmpty()     || secId.contains(s.getSecid()))
                .filter(s -> lotSize.isEmpty()   || lotSize.get().equals(s.getLotsize()))
                .skip(from)
                .limit(size)
                .toList();
    }

}
