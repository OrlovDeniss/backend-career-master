package ru.intelinvest.career.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.intelinvest.career.models.EthereumBalance;
import ru.intelinvest.career.util.exception.EthereumApiException;

import java.math.RoundingMode;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EthereumService {
    private static final String BALANCE_URL =
            "https://api.etherscan.io/api?" +
                    "module=account" +
                    "&action=balance" +
                    "&address={address}" +
                    "&tag=latest";
    private static final int BALANCE_SCALE = 2;
    private final RestTemplate restTemplate;

    public String getBalance(String accountAddress) {
        var ethereumBalance = getEthereumBalance(accountAddress);
        if (Objects.isNull(ethereumBalance) || Objects.isNull(ethereumBalance.getTether())) {
            throw new EthereumApiException("NULL from " + BALANCE_URL);
        }
        return ethereumBalance.getTether()
                .setScale(BALANCE_SCALE, RoundingMode.CEILING)
                .toString();
    }

    private EthereumBalance getEthereumBalance(String accountAddress) {
        return restTemplate.getForObject(
                BALANCE_URL,
                EthereumBalance.class,
                Map.of("address", accountAddress));
    }
}
