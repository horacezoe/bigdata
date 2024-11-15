package com.example.bda_homework.util;

import org.springframework.stereotype.Component;

@Component
public class DataMaskingUtil {

    // 邮箱脱敏
    public String maskEmail(String email) {
        int index = email.indexOf("@");
        if (index > 2) {
            return email.substring(0, 2) + "****" + email.substring(index);
        }
        return "****" + email.substring(index);
    }

    // 其他敏感数据的脱敏方法
    public String maskSensitiveData(String data) {
        return data.substring(0, 2) + "****";
    }
}
