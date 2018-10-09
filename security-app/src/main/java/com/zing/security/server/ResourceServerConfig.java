package com.zing.security.server;

import com.zing.security.app.authentication.openid.OpenIdAuthenticationSecurityConfig;
import com.zing.security.core.authentication.FormAuthenticationConfig;
import com.zing.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.zing.security.core.authorize.AuthorizeConfigManager;
import com.zing.security.core.properties.SecurityConstants;
import com.zing.security.core.properties.SecurityProperties;
import com.zing.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * 资源服务器配置
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SpringSocialConfigurer simpleSpringSocialConfigurer;

    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Autowired
    private FormAuthenticationConfig formAuthenticationConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        formAuthenticationConfig.configure(http);

        http.apply(validateCodeSecurityConfig)
                .and()
            .apply(smsCodeAuthenticationSecurityConfig)
                .and()
            .apply(simpleSpringSocialConfigurer)
                .and()
            .apply(openIdAuthenticationSecurityConfig)
                .and()
            .csrf().disable();

        authorizeConfigManager.config(http.authorizeRequests());
    }
}
