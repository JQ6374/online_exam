package com.zz.subjecttiveJudge;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class Cosine_Sim {
    /**
     *
     * @param doc1 句子A 标准答案
     * @param doc2 句子B 带待评分答案
     * @return 返回句子间的余弦相似度。
     */
    public static double getSimilarity(String doc1, String doc2) {
//        isBlank 判断字符串是否为空 为null 有空格
        if (StringUtils.isBlank(doc1) || StringUtils.isBlank(doc2)) {
        //        if (StringUtils.isBlank(doc1) or StringUtils.isBlank(doc2)) {
            return 0L;
        }
        Map<Character,int[]> algMap=new HashMap<>();
        for (int i = 0; i<doc1.length(); i++) {
            char d1 = doc1.charAt(i);
            int[] fq = algMap.get(d1);
            if (fq != null && fq.length == 2) {
                fq[0]++;
            } else {
                fq = new int[2];
                fq[0] = 1;
                fq[1] = 0;
                algMap.put(d1, fq);
            }
        }
        for (int i = 0; i<doc2.length(); i++) {
            char d2 = doc2.charAt(i);
            int[] fq = algMap.get(d2);
            if (fq != null && fq.length == 2) {
                fq[1]++;
            } else {
                fq = new int[2];
                fq[0] = 0;
                fq[1] = 1;
                algMap.put(d2, fq);
            }
        }
        double sqdoc1 = 0;
        double sqdoc2 = 0;
        double denuminator = 0;
        for (Map.Entry entry : algMap.entrySet()) {
            int[] c = (int[]) entry.getValue();
            denuminator += c[0] * c[1];
            sqdoc1 += c[0] * c[0];
            sqdoc2 += c[1] * c[1];
        }
        return denuminator / Math.sqrt(sqdoc1 * sqdoc2);
    }
}
