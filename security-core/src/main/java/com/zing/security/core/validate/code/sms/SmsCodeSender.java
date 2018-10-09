package com.zing.security.core.validate.code.sms;

public interface SmsCodeSender {

    /**
     * 发送短信验证码
     *
     * @param mobile
     * @param code
     */
    void send(String mobile, String code);
}
