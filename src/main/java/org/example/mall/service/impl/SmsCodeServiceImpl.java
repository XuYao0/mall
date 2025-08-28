package org.example.mall.service.impl;

import org.example.mall.service.SmsCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 验证码管理Service实现类
 * 
 * @author Mall
 * @version 1.6
 */
@Service
public class SmsCodeServiceImpl implements SmsCodeService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String SMS_CODE_PREFIX = "sms:code:";
    private static final int CODE_LENGTH = 6;
    private static final int CODE_EXPIRE_MINUTES = 5;

    @Override
    public String generateAndSendCode(String phone) {
        // 校验手机号格式
        if (!isValidPhone(phone)) {
            throw new IllegalArgumentException("手机号格式不正确");
        }

        // 检查是否频繁发送（1分钟内只能发送一次）
        String lastSendKey = SMS_CODE_PREFIX + "last:" + phone;
        String lastSendTime = stringRedisTemplate.opsForValue().get(lastSendKey);
        if (lastSendTime != null) {
            throw new IllegalStateException("验证码发送过于频繁，请稍后再试");
        }

        // 生成6位随机验证码
        String code = generateRandomCode();

        // 存储验证码到Redis，设置5分钟过期
        String codeKey = SMS_CODE_PREFIX + phone;
        stringRedisTemplate.opsForValue().set(codeKey, code, CODE_EXPIRE_MINUTES, TimeUnit.MINUTES);

        // 设置1分钟发送间隔限制
        stringRedisTemplate.opsForValue().set(lastSendKey, String.valueOf(System.currentTimeMillis()), 1, TimeUnit.MINUTES);

        // 模拟发送短信（实际项目中这里调用短信服务API）
        simulateSendSms(phone, code);

        return "验证码已发送";
    }

    @Override
    public boolean verifyCode(String phone, String code) {
        if (!isValidPhone(phone) || code == null || code.trim().isEmpty()) {
            return false;
        }

        String codeKey = SMS_CODE_PREFIX + phone;
        String storedCode = stringRedisTemplate.opsForValue().get(codeKey);

        if (storedCode != null && storedCode.equals(code.trim())) {
            // 验证成功后删除验证码
            stringRedisTemplate.delete(codeKey);
            return true;
        }

        return false;
    }

    /**
     * 生成随机验证码
     * 
     * @return 6位数字验证码
     */
    private String generateRandomCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }

    /**
     * 验证手机号格式
     * 
     * @param phone 手机号
     * @return 是否有效
     */
    private boolean isValidPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return false;
        }
        // 简单的手机号格式验证（以1开头的11位数字）
        return phone.matches("^1[3-9]\\d{9}$");
    }

    /**
     * 模拟发送短信
     * 
     * @param phone 手机号
     * @param code 验证码
     */
    private void simulateSendSms(String phone, String code) {
        // 在实际项目中，这里应该调用短信服务提供商的API
        // 比如阿里云、腾讯云等的短信服务
        System.out.println("【模拟短信】发送验证码到手机号: " + phone + "，验证码: " + code + "，5分钟内有效。");
    }
}
