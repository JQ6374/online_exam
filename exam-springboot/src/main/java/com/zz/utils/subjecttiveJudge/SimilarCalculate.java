package com.zz.utils.subjecttiveJudge;

import org.xm.Similarity;

//词语内容相似度 进行词语相似度的计算
public class SimilarCalculate {
    /**
     * @param KAi 关键词序列A中的词i
     * @param KBj 关键词序列B中的词j
     * @return 返回两个关键词i，j的相似度用于两个序列相似度的计算
     */
    public static double wordSim(String KAi,String KBj) {
//      使用哈工大词林
        double result = Similarity.cilinSimilarity(KAi,KBj);
        return result;
    }
}