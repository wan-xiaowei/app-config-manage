package com.wxw.admin.config;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers.BigDecimalDeserializer;
import com.wxw.config.ConfigConstant;
import com.wxw.enums.DisplayedEnum;
import com.wxw.enums.LogType;
import com.wxw.enums.OperateType;
import com.wxw.enums.Warn;
import com.wxw.web.convert.StringToDateConvert;
import com.wxw.web.convert.StringToDisplayedEnumConverterFactory;
import com.wxw.web.json.deserializer.StringToDateDeserializer;
import com.wxw.web.json.deserializer.StringToDisplayedEnumDeserializer;
import com.wxw.web.json.serializer.DisplayedEnumSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.env.Environment;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Configuration
@EnableWebMvc // 启用 Spring MVC
@ComponentScan(basePackages = {ConfigConstant.SCAN_BASE_PACKAGES}, useDefaultFilters = false, includeFilters = {
        @Filter(type = FilterType.ANNOTATION, value = Controller.class),
        @Filter(type = FilterType.ANNOTATION, value = RestController.class),
        @Filter(type = FilterType.ANNOTATION, value = ControllerAdvice.class)})
public class WebContextConfig extends WebMvcConfigurerAdapter {

    @SuppressWarnings("unused")
    @Autowired
    private Environment env;

    @Bean /* 文件上传配置 */
    public MultipartResolver multipartResolver() {
        StandardServletMultipartResolver multipartResolver = new StandardServletMultipartResolver();
        return multipartResolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable(); // 配置静态资源的处理
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new PermissionInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToDateConvert());
        registry.addConverterFactory(new StringToDisplayedEnumConverterFactory());
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.indentOutput(true)//
                // .modulesToInstall(new ParameterNamesModule())//
                .dateFormat(new SimpleDateFormat(ConfigConstant.DATE_TIME_FORMAT_PATTERN));
        // builder.serializationInclusion(JsonInclude.Include.NON_NULL);

        Map<Class<?>, JsonSerializer<?>> serializers = new HashMap<>();
        serializers.put(DisplayedEnum.class, new DisplayedEnumSerializer());
        builder.serializersByType(serializers);

        Map<Class<?>, JsonDeserializer<?>> deserializers = new LinkedHashMap<>();
        deserializers.put(BigDecimal.class, new BigDecimalDeserializer());
        deserializers.put(Date.class, new StringToDateDeserializer()); // json日期反序列化

        deserializers.put(LogType.class, new StringToDisplayedEnumDeserializer<LogType>(LogType.class));
        deserializers.put(OperateType.class, new StringToDisplayedEnumDeserializer<OperateType>(OperateType.class));
        deserializers.put(Warn.class, new StringToDisplayedEnumDeserializer<Warn>(Warn.class));

        builder.deserializersByType(deserializers);

        MappingJackson2HttpMessageConverter jsonHmc = new MappingJackson2HttpMessageConverter();
        jsonHmc.setSupportedMediaTypes(
                Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON_UTF8, MediaType.TEXT_HTML));
        jsonHmc.setObjectMapper(builder.build());
        converters.add(jsonHmc);

        MappingJackson2XmlHttpMessageConverter xmlHmc = new MappingJackson2XmlHttpMessageConverter();
        xmlHmc.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_XML, MediaType.APPLICATION_ATOM_XML,
                MediaType.APPLICATION_XHTML_XML, MediaType.TEXT_XML));
        xmlHmc.setObjectMapper(builder.createXmlMapper(Boolean.TRUE).build());
        converters.add(xmlHmc);
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.mediaType("json", MediaType.APPLICATION_JSON);
        configurer.mediaType("xml", MediaType.APPLICATION_XML);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }


}
