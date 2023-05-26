//package com.zz.subjecttiveJudge;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.List;
//
//import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
//import org.deeplearning4j.models.word2vec.Word2Vec;
//
//public class TextVectorizer {
//    private Word2Vec word2vec;
//
//    public TextVectorizer(String modelPath) throws IOException {
//        // 加载预训练词向量模型
//        Path path = Paths.get(modelPath);
//        List<String> lines = Files.readAllLines(path);
//        word2vec = WordVectorSerializer.readWord2VecModel(path.toFile());
//    }
//
//    // 将中文文本转换为向量
//    public double[] vectorize(String text) {
//        String[] words = text.split("\\s+");
//        double[] vector = new double[word2vec.getLayerSize()];
//        for (String word : words) {
//            if (word2vec.hasWord(word)) {
//                double[] wordVector = word2vec.getWordVector(word);
//                for (int i = 0; i < vector.length; i++) {
//                    vector[i] += wordVector[i];
//                }
//            }
//        }
//        return vector;
//    }
//}
