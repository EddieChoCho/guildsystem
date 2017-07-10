package com.eddie.controller;

import com.eddie.exception.AuthException;
import com.eddie.response.impl.GuildSystemExceptionResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;
import java.util.Properties;

/**
 * Created by EddieChoCho on 2017/7/10.
 */
public class ExceptionHandleControllerTests {

    private ExceptionHandleController exceptionHandleControllerFactory(Properties properties){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCommonMessages(properties);
        return new ExceptionHandleController(new GuildSystemExceptionResponse(messageSource, new ObjectMapper()));
    }

    @Test
    public void testHandleCustomException(){
        String key = "test.message";
        String value = "Test Message";
        Properties properties = new Properties();
        properties.setProperty(key,value);
        ExceptionHandleController controller = this.exceptionHandleControllerFactory(properties);
        JsonNode node = controller.handleCustomException(new AuthException(key), Locale.TRADITIONAL_CHINESE);
        assert (node.get("context").textValue().equals(value));
    }

}
