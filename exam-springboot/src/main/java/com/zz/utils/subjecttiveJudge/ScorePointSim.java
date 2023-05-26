package com.zz.utils.subjecttiveJudge;


import java.util.List;

/**
 *得分点匹配相似度计算
 */
public class ScorePointSim {
    /**
     *
     * @param sentenceA 句子集A
     * @param sentenceB 句子集B
     * @return
     *  Alpha  Beta Gamma Delta  为常系数，参考论文 α、β、γ、δ 的值分别为 0.91、0.90、0.80、
     * 0.50 时，平均评分准确率 ε最高
     */
    public static double getScorePointSim(List<String> sentenceA, List<String> sentenceB){
        int i=0;
        double scoSim = 0,scoSimOne = 0,scoSimTwo = 0;
        double Alpha=0.91,  Beta = 0.90, Gamma = 0.80 , Delta = 0.50;
        Tokenizer tokenizer = new Tokenizer();
        int n = sentenceA.size(); //句子集A的长度
        int m = sentenceB.size();  //句子集B的长度
            double scoSimOneTotal = 0;
        while (i < n) {
//            获取句子sentenceA.get(i)的关键词序列
            int j = 0;
            List<String> SaiKeyWordsList = ParticipleWordListParticipleToStringListUtil.getList(sentenceA.get(i));
            while (j < m) {
                //获取句子sentenceB.get(j)的关键词序列
                List<String> SbjKeyWordsList = ParticipleWordListParticipleToStringListUtil.getList(sentenceB.get(j));
                if (Cosine_Sim.getSimilarity(sentenceA.get(i), sentenceB.get(j)) >= Alpha ||
                        KeyWordsSim.simAandB(SaiKeyWordsList, SbjKeyWordsList) >= Beta &&
                                Cosine_Sim.getSimilarity(sentenceA.get(i), sentenceB.get(j)) >= Gamma) {
                    scoSimOne = 1;
                } else if (Cosine_Sim.getSimilarity(sentenceA.get(i), sentenceB.get(j)) >= Delta) {
                    scoSimOne = Math.max(scoSimOne, KeyWordsSim.simAandB(SaiKeyWordsList, SbjKeyWordsList));
                }
                scoSimOneTotal+=scoSimOne;
                scoSimOne = 0;
                j++;
            }
            i++;
        }
        scoSim = scoSimOneTotal/n;
        return scoSim;
            }
        }




