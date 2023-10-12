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

package ru.intelinvest.career.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.intelinvest.career.models.SecuritiesData;
import ru.intelinvest.career.models.Stock;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MoexService {
    private static final String GET_SECURITIES_URL = "https://iss.moex.com/iss/engines/stock/markets/shares/boards/TQBR/securities/.json?iss.meta=off&iss.only=securities&iss.json=extended";
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public List<Stock> getStocks() {
        String json = restTemplate.getForObject(GET_SECURITIES_URL, String.class);
        if (json == null || json.isEmpty()) {
            return Collections.emptyList();
        }
        return objectMapper.convertValue(json, SecuritiesData.class).getStocks();
    }
}
