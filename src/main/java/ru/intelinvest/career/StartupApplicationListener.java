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

package ru.intelinvest.career;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import ru.intelinvest.career.service.EthereumService;
import ru.intelinvest.career.service.MoexService;

@Component
@Slf4j
@RequiredArgsConstructor
public class StartupApplicationListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final String ACCOUNT_ADDRESS = "0x77DDc987516abd90803b7e2A18F0F53a98438362";

    private final MoexService moexService;
    private final EthereumService ethereumService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("Загружено акций с Московской биржи: {}", moexService.getStocks());
        log.info("Получен баланс Ethereum кошелька: {} Tether", ethereumService.getBalance(ACCOUNT_ADDRESS));
    }
}