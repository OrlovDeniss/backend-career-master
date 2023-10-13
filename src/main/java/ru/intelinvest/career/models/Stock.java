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

package ru.intelinvest.career.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES;

@Data
@Builder
@JsonFormat(with = ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public class Stock {
    private String secid;
    private String boardid;
    private String shortname;
    private Float prevprice;
    private Integer lotsize;
    private Float facevalue;
    private String status;
    private String boardname;
    private Integer decimals;
    private String secname;
    private String remarks;
    private String marketcode;
    private String instrid;
    private String sectorid;
    private Float minstep;
    private Float prevwaprice;
    private String faceunit;
    private String prevdate;
    private Long issuesize;
    private String isin;
    private String latname;
    private String regnumber;
    private Float prevlegalcloseprice;
    private String currencyid;
    private String sectype;
    private Integer listlevel;
    private String settledate;
}
