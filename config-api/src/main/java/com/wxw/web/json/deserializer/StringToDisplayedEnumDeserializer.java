package com.wxw.web.json.deserializer;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.wxw.enums.DisplayedEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * 字符串转DisplayedEnum
 * @author heweiming
 *
 * @param <T> DisplayedEnum的子类并且是枚举
 */
public class StringToDisplayedEnumDeserializer<T extends DisplayedEnum> extends JsonDeserializer<T> {
    
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(StringToDisplayedEnumDeserializer.class);

    private Class<T> enumType;

    public StringToDisplayedEnumDeserializer() {
        super();
    }

    public StringToDisplayedEnumDeserializer(Class<T> enumType) {
        super();
        this.enumType = enumType;
    }

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String source = p.getText();
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        T displayedEnum = DisplayedEnum.valueOfEnum(this.enumType, source.trim(), null);
        if (displayedEnum == null) {
            logger.warn("枚举类{} value为{}的枚举不存在", enumType.getSimpleName(), source);
        }
        return displayedEnum ;
    }
    
}
