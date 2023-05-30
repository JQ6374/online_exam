package com.zz.controller;

import com.alibaba.fastjson.JSONObject;
import com.zz.Service.MailService;
import com.zz.utils.Code;
import com.zz.utils.result.ApiResult;
import com.zz.utils.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @author liyuzhen
 */
@RestController
@RequestMapping("/api/auth")
public class EmailAuthController {

    /**
     * 引入业务层依赖
     */
    @Autowired
    private MailService mailService;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private EmailUtils emailUtils;

    @PostMapping("/send_email")
    public ApiResult send_email(@RequestBody JSONObject param) {
        String toEmail = param.getString("toEmail");
        ApiResult result = new ApiResult();
        String code = emailUtils.generateValidateCodeString(6);
        boolean flag = mailService.sendMail(toEmail, code);
        if (flag) {
            // 将生成的验证码保存到Redis中并设置有效期五分钟
            redisTemplate.opsForValue().set(toEmail, code,
                    5, TimeUnit.MINUTES);
        }
        result.setCode(flag ? Code.SAVA_OK : Code.SAVA_ERR);
        result.setMsg(flag ? "邮件发送成功！" : "邮箱发送失败，请检查是否输入正确!");
        return result;
    }
}