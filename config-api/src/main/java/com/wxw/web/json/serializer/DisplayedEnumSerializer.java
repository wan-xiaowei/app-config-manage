package com.wxw.web.json.serializer;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.wxw.enums.DisplayedEnum;

import java.io.IOException;

/**
 * DisplayedEnum json序列化
 * @author heweiming 2017年5月22日 17:34:45
 */
public class DisplayedEnumSerializer extends JsonSerializer<DisplayedEnum> {

    @Override
    public void serialize(DisplayedEnum value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException, JsonProcessingException {
        gen.writeStartObject();
        gen.writeStringField("className", value.getClass().getSimpleName());
        gen.writeStringField("label", value.getLabel());
        gen.writeStringField("value", value.getValue());
        gen.writeEndObject();
        
    }

}
