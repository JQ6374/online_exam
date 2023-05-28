package com.zz.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SplitObjectAndSubject {
    /**
     * 题型固定为五种，故遍历五 Objects 客观 subject 主观
     * @param paper
     * @return 返回主观题客观题分离的列表，索引0为客观题，索引1为主观题
     */
    public static List<JSONObject> splitMethod(JSONObject paper){
        JSONObject objective = new JSONObject();
//        分离客观题与主观题 选择判断客观题，简答题为主观题
        for (int i = 1; i <=4; i++) {
            objective.put(Integer.toString(i),paper.get(i));
        }

        JSONObject subjective = new JSONObject();
        subjective.put("5",paper.get(5));
        ArrayList<JSONObject> jsonObjects = new ArrayList<>();
        jsonObjects.add(objective);
        jsonObjects.add(subjective);
        return jsonObjects;
    }
}
