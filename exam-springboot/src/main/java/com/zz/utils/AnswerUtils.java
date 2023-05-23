package com.zz.utils;

import com.alibaba.fastjson.JSONObject;

public class AnswerUtils {
    public static String formatAnswer(int typeId, String answer) {
        JSONObject result = new JSONObject();
        result.put("typeId", typeId);
        result.put("answerContent", answer);
        return result.toString();
    }

}
