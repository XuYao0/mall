package org.example.mall.service;

/**
 * 验证码管理Service接口
 * 
 * @author Mall
 * @version 1.6
 */
public interface SmsCodeService {

    /**
     * 生成验证码并发送到指定手机号
     * 
     * @param phone 手机号
     * @return 生成结果
     */
    String generateAndSendCode(String phone);

    /**
     * 校验验证码
     * 
     * @param phone 手机号
     * @param code 验证码
     * @return 校验结果
     */
    boolean verifyCode(String phone, String code);
}
