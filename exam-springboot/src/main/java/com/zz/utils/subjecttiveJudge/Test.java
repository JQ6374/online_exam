package com.zz.utils.subjecttiveJudge;

import java.util.List;

public class Test {

    public static void main(String[] args) {
        //1.分句
        String answerCase = "1.点明时节、地点。" +
                "2.照应诗题。" +
                "3.通过对巫山巫峡秋色秋气的形象描绘，烘托出萧瑟阴森的环境气氛。" +
                "4.寄寓了老大伤悲、抑郁苍凉的情怀。" +
                "6.定了全诗沉郁感伤的情感基调。";
        String answer = "1.点明时节、地点。" +
//                    "2.照应诗题。" +
                "3.通过对巫山巫峡秋色秋气的形象描绘，烘托出萧瑟阴森的环境气氛。" +
//                    "4.寄寓了老大伤悲、抑郁苍凉的情怀。" +
                "6.定了全诗沉郁感伤的情感基调。";
        //2.得分点匹配相似度计算
        List<String> answerCaseList = SentenceSeparation.sentenceList(answerCase);
        List<String> answerList = SentenceSeparation.sentenceList(answer);
        double scorePointSim = ScorePointSim.getScorePointSim(answerCaseList, answerList);
        System.out.println(scorePointSim);
        double maxScore = 5;
        double score = Math.floor((scorePointSim * maxScore) + 0.5);
        System.out.println(score);
    }
}
