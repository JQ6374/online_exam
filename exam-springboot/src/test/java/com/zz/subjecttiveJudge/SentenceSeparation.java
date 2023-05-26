package com.zz.subjecttiveJudge;

import java.util.ArrayList;
import java.util.List;

//对文本进行句子与句子层级间的分离
public class SentenceSeparation {
    /**
     *
     * @param text 待评分答案原始文本
     * @return 返回句子分离后的列表
     */
    public static List<String> sentenceList(String text){
        String[] fullStop = text.split("。");
        List<String> fullStopArr = new ArrayList<>();
        for (String node:
             fullStop) {
            fullStopArr.add(node);
        }

        System.out.println(fullStop.toString());
        return  fullStopArr;
    }
}
