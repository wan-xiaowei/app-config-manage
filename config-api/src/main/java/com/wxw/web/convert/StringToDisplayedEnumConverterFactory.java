package com.wxw.web.convert;


import com.wxw.enums.DisplayedEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.util.StringUtils;

public class StringToDisplayedEnumConverterFactory implements ConverterFactory<String, DisplayedEnum> {

    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(StringToDisplayedEnumConverterFactory.class);

    @Override
    public <T extends DisplayedEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToEnumConverter(targetType);
    }

    private final class StringToEnumConverter<T extends DisplayedEnum> implements Converter<String, T> {

        private Class<T> enumType;

        public StringToEnumConverter(Class<T> enumType) {
            this.enumType = enumType;
        }

        public T convert(String source) {
            if (StringUtils.isEmpty(source)) {
                return null;
            }
            T displayedEnum = DisplayedEnum.valueOfEnum(this.enumType, source.trim() ,null);
            if (displayedEnum == null) {
                logger.warn("枚举类{} value为{}的枚举不存在", enumType.getSimpleName(), source);
            }
            return displayedEnum;
        }

    }

}
