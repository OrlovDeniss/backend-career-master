package ru.intelinvest.career.util.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import ru.intelinvest.career.models.Charset;
import ru.intelinvest.career.models.SecuritiesData;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class SecuritiesDataDeserializer extends JsonDeserializer<SecuritiesData> {
    private static final String CHARSET_INFO = "charsetinfo";
    private static final String SECURITIES = "securities";

    private final ObjectMapper objectMapper;

    @Override
    public SecuritiesData deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode rootNode = jsonParser.getCodec().readTree(jsonParser);
        JsonNode jsonNode = objectMapper.readTree(rootNode.textValue());
        JsonNode charsetNode = jsonNode.get(0).get(CHARSET_INFO);
        JsonNode securitiesNode = jsonNode.get(1).get(SECURITIES);
        return SecuritiesData.builder()
                .charset(objectMapper.treeToValue(charsetNode, Charset.class))
                .stocks(objectMapper.treeToValue(securitiesNode, List.class))
                .build();
    }
}
