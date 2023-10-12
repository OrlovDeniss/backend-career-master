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

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.intelinvest.career.models.Stock;
import ru.intelinvest.career.service.MoexService;
import ru.intelinvest.career.models.StockFilter;
import ru.intelinvest.career.util.filter.StockFilterUtil;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/moex")
public class MoexEndpoint {

    private final MoexService moexService;

    @PostMapping("stocks")
    public ResponseEntity<List<Stock>> processIntegration(@RequestBody StockFilter stockFilter) {
        var stocks = moexService.getStocks();
        // todo реализация задания № 2 и 3 здесь
        return ResponseEntity.ok(StockFilterUtil.filter(stocks, stockFilter));
    }
}
