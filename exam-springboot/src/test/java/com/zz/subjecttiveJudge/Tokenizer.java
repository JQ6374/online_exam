package com.zz.subjecttiveJudge;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

import java.util.List;
import java.util.stream.Collectors;

public class Tokenizer {

    /**
     * 分词
     */
    public  List<Word> segment(String sentence) {

        //1、 采用HanLP中文自然语言处理中标准分词进行分词
        List<Term> termList = HanLP.segment(sentence);

        //打印分词结果。
//        System.out.println(termList.toString());

        //2、重新封装到Word对象中（term.word代表分词后的词语，term.nature代表改词的词性）
        return termList.stream().map(
                term -> new Word(term.word, term.nature.toString())).collect(Collectors.toList()
        );
    }
}
