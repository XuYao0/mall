package org.example.mall.controller;

import org.example.mall.common.CommonResult;
import org.example.mall.service.SmsCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 短信验证码Controller
 * 
 * @author Mall
 * @version 1.6
 */
@RestController
@RequestMapping("/api/v1/sms")
public class SmsCodeController {

    @Autowired
    private SmsCodeService smsCodeService;

    /**
     * 发送验证码
     * 
     * @param phone 手机号
     * @return 发送结果
     */
    @PostMapping("/code/send")
    public CommonResult<String> sendCode(@RequestParam String phone) {
        try {
            String result = smsCodeService.generateAndSendCode(phone);
            return CommonResult.success(result);
        } catch (IllegalArgumentException e) {
            return CommonResult.failed(e.getMessage());
        } catch (IllegalStateException e) {
            return CommonResult.failed(e.getMessage());
        } catch (Exception e) {
            return CommonResult.failed("验证码发送失败，请稍后重试");
        }
    }

    /**
     * 校验验证码
     * 
     * @param phone 手机号
     * @param code 验证码
     * @return 校验结果
     */
    @PostMapping("/code/verify")
    public CommonResult<String> verifyCode(@RequestParam String phone, @RequestParam String code) {
        try {
            boolean isValid = smsCodeService.verifyCode(phone, code);
            if (isValid) {
                return CommonResult.success("验证码校验成功");
            } else {
                return CommonResult.failed("验证码错误或已过期");
            }
        } catch (Exception e) {
            return CommonResult.failed("验证码校验失败，请稍后重试");
        }
    }
}
