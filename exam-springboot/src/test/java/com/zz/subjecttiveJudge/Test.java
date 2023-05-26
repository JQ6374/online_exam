package com.zz.subjecttiveJudge;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.summary.TextRankKeyword;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.seg.common.Vertex;
import com.hankcs.hanlp.seg.common.WordNet;


import java.util.List;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
//        Tokenizer tokenizer = new Tokenizer();
//        String text1 = "你好美丽的祖国大地，你好美丽的大好河山";
////        String text1 ="表达了作者怀念当初在京城的美好时光";
////        分词序列
//        List<Word> words1 = tokenizer.segment(text1);
//        String wordStr1 = words1.stream().map(word -> word.getName()).collect(Collectors.joining(" "));
////        System.out.println(wordStr1);
//        //获取一个TextRankKeyword对象，调用其关键词提取方法，将待提取关键词的文本作为参数传入
//        TextRankKeyword textRankKeyword = new TextRankKeyword();
//        String content1 = wordStr1;
//        //获取text1的关键词词组序列
//        List<String> keywords1 = textRankKeyword.getKeywords(content1);
////        System.out.println(keywords1);
////        System.out.println("====================================");
//        String text2 ="表达了诗人对时光已逝，韶华难在故友凋零的无限悲情";
//        //        分词序列
//        List<Word> words2 = tokenizer.segment(text2);
//        String wordStr2 = words2.stream().map(word -> word.getName()).collect(Collectors.joining(" "));
////        System.out.println(wordStr2);
//        String content2 = wordStr2;
//        List<String> keywords2 = textRankKeyword.getKeywords(content2);
////        System.out.println(keywords2);
////        对关键词序列keywords1 keywords2进行关键词相似度计算
//        KeyWordsSim keyWordsSim = new KeyWordsSim();
//        //        得到句子A、B的关键词相似度KeySim AB
//        double keySimAandB = keyWordsSim.simAandB(keywords1, keywords2);
////        System.out.println("句子A、B的关键词相似度KeySim AB:");
////        System.out.println(keySimAandB);
//
//        System.out.println(wordStr1);
//        System.out.println(wordStr2);
//        System.out.print("句子余弦相似度：");
//        double similarityOfSegmentSentence = Cosine_Sim.getSimilarity(wordStr1, wordStr2);
//        System.out.println(similarityOfSegmentSentence);


        //1.分句
            String answerCase = "1.点明时节、地点。" +
                    "2.照应诗题。" +
                    "3.通过对巫山巫峡秋色秋气的形象描绘，烘托出萧瑟阴森的环境气氛。" +
                    "4.寄寓了老大伤悲、抑郁苍凉的情怀。" +
                    "5.为后文的写景抒情做铺垫。" +
                    "6.定了全诗沉郁感伤的情感基调。";
            String answer ="1.点明时节、地点。" +
                    "2.照应诗题。" +
//                    "3.通过对巫山巫峡秋色秋气的形象描绘，烘托出萧瑟阴森的环境气氛。" +
                    "4.寄寓了老大伤悲、抑郁苍凉的情怀。" +
                    "5.为后文的写景抒情做铺垫。" +
                    "6.定了全诗沉郁感伤的情感基调。";
        //2.得分点匹配相似度计算
        List<String> answerCaseList = SentenceSeparation.sentenceList(answerCase);
        List<String> answerList = SentenceSeparation.sentenceList(answer);
        double scorePointSim = ScorePointSim.getScorePointSim(answerCaseList, answerList);
        System.out.println(scorePointSim);
        int  maxScore = 5;
        double score = 0;
//        System.out.println((scorePointSim * maxScore)+0.5);
        score = Math.floor((scorePointSim * maxScore)+0.5);
//        if (scorePointSim>=0.5){//0.5的值为ε参数，参考论文设置的五个参数之一
//            score = maxScore;
//        }else{
//            score = (int) (scorePointSim*5)+1;
//        }

        System.out.println(score);
//        System.out.println(scorePointSim * maxScore);

    }

}
