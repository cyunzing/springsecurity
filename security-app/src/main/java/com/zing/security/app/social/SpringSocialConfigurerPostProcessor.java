package com.zing.security.app.social;

import com.zing.security.core.properties.SecurityConstants;
import com.zing.security.core.social.support.SimpleSpringSocialConfigurer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class SpringSocialConfigurerPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (StringUtils.equals(beanName, "simpleSpringSocialConfigurer")) {
            SimpleSpringSocialConfigurer configurer = (SimpleSpringSocialConfigurer) bean;
            configurer.signupUrl(SecurityConstants.DEFAULT_SOCIAL_USER_INFO_URL);
            return configurer;
        }
        return bean;
    }

}
