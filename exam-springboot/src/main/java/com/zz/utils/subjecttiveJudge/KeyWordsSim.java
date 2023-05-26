package com.zz.utils.subjecttiveJudge;

import java.util.List;

public class KeyWordsSim {
    /**
     *
     * @param KAn 关键词序列A 如参考答案
     * @param KBm 关键词序列B 如待评分答案
     * @return 返回关键词相似度
     */
    public static double simAandB(List<String>KAn,List<String>KBm){
        //        对关键字进行相似度计算
        /**
         * 参考答案句子A的关键词序列Kan，n = len(Kan)  待评答案句子B的关键词序列KBm m =len(kbm)
         * 句子 A,B的关键词相似度为keySimAandB
         */
        int i=0;
        int j=0;
        double sumSim = 0;
        double MaxSim;
        double keySimAandB;
        int n = KAn.size();
        int m = KBm.size();
        while ( i++ < n){
            MaxSim = 0;
            while (j < m){
                MaxSim = Math.max(MaxSim, SimilarCalculate.wordSim(KAn.get(i), KBm.get(j)));
                j++;
            }
            sumSim = sumSim + MaxSim/n;
            i++;
        }
        keySimAandB = sumSim/n;
//        返回句子A、B的关键词相似度KeySim AB
        return keySimAandB;
    }
}
