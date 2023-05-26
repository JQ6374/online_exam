package com.zz.utils.subjecttiveJudge;

import com.hankcs.hanlp.summary.TextRankKeyword;

import java.util.List;
import java.util.stream.Collectors;

//对句子进行分词，提取关键词得到关键词序列
public class ParticipleWordListParticipleToStringListUtil {
    /**
     *
     * @param text 传入的句子
     * @return 返回 传入句子的关键词序列 一个List<Stirng>的列表
     */
    //获取tokenizer 分词对象
    private static Tokenizer tokenizer ;
    //获取一个TextRankKeyword对象，调用其关键词提取方法，将待提取关键词的文本作为参数传入
    private static TextRankKeyword textRankKeyword ;

    /**
     * @param text 对输入的文本进行分词、关键词提取 参考答案和待评分答案 均调用该函数
     * @return 返回关键词提取后的关键词序列
     */
    public static List<String> getList(String text){
        tokenizer = new Tokenizer();
        textRankKeyword = new TextRankKeyword();
        List<Word> words = tokenizer.segment(text);
        String wordStr = words.stream().map(word -> word.getName()).collect(Collectors.joining(" "));
        List<String> keywords = textRankKeyword.getKeywords(wordStr);
        return keywords;
    }
}
