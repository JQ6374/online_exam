package com.zz;

import java.util.*;

import org.ansj.splitWord.analysis.ToAnalysis;

public class SimilarityAnalysis {
    public static void main(String[] args) {
        System.out.println(calculateSimilarityScore(
                "这是一个样本段落。它包含几个句子。每个句子都是用句号分隔的。",
                "这是一个样本段落。它包含几个句子。每个句子都是用句号分隔的。", 100));
    }

    public static int calculateSimilarityScore(String correctAnswer, String studentAnswer, int score) {
        List<String> words = new ArrayList<>();
        for (org.ansj.domain.Term term : ToAnalysis.parse(correctAnswer)) {
            if (term.getNatureStr().startsWith("n")) { // 只保留名词
                words.add(term.getName());
            }
        }
        Set<String> wordSet = new TreeSet<>(words);

        List<List<Integer>> correctSentenceCodes = new ArrayList<>();
        String[] correctSentences = correctAnswer.split("。");
        for (String sentence : correctSentences) {
            List<String> sentenceWords = new ArrayList<>();
            for (org.ansj.domain.Term term : ToAnalysis.parse(sentence)) {
                if (term.getNatureStr().startsWith("n")) { // 只保留名词
                    sentenceWords.add(term.getName());
                }
            }
            List<Integer> sentenceCodesList = new ArrayList<>();
            for (String word : sentenceWords) {
                int code = Collections.binarySearch(new ArrayList<>(wordSet), word);
                sentenceCodesList.add(code);
            }
            correctSentenceCodes.add(sentenceCodesList);
        }

        List<List<Integer>> studentSentenceCodes = new ArrayList<>();
        String[] studentSentences = studentAnswer.split("。");
        for (String sentence : studentSentences) {
            List<String> sentenceWords = new ArrayList<>();
            for (org.ansj.domain.Term term : ToAnalysis.parse(sentence)) {
                if (term.getNatureStr().startsWith("n")) { // 只保留名词
                    sentenceWords.add(term.getName());
                }
            }
            List<Integer> sentenceCodesList = new ArrayList<>();
            for (String word : sentenceWords) {
                int code = Collections.binarySearch(new ArrayList<>(wordSet), word);
                sentenceCodesList.add(code);
            }
            studentSentenceCodes.add(sentenceCodesList);
        }

        double maxSimilarity = 0;
        for (List<Integer> correctSentence : correctSentenceCodes) {
            for (List<Integer> studentSentence : studentSentenceCodes) {
                double similarity = calculateSimilarity(correctSentence, studentSentence);
                if (similarity > maxSimilarity) {
                    maxSimilarity = similarity;
                }
            }
        }

        int studentScore = (int) (maxSimilarity * score);
        if (studentScore > score) {
            studentScore = score;
        }

        return studentScore;
    }

    private static double calculateSimilarity(List<Integer> sentence1, List<Integer> sentence2) {
        int commonCount = 0;
        for (Integer code : sentence1) {
            if (sentence2.contains(code)) {
                commonCount++;
            }
        }
        double similarity = (double) commonCount / (sentence1.size() + sentence2.size());
        return similarity;
    }
}