package main;

import edu.princeton.cs.algs4.In;
import ngrams.NGramMap;
import ngrams.TimeSeries;

import java.util.*;


public class WordParser {

    NGramMap ngm;
    private String synsetsFilePath;
    private String hyponymsFilePath;

    private String wordFile;

    private String countFile;


    private Map<String, Set<Integer>> wordAppearances;

    private Map<Integer, MyGraph.Node> idToNode;

    public WordParser(String synsetsFilePath, String hyponymsFilePath, String wordFile, String countFile) {
        MyGraph graph = new MyGraph();
        NGramMap ngm = new NGramMap(wordFile, countFile);
        In synsets = new In(synsetsFilePath);
        In hyponyms = new In(hyponymsFilePath);
        Map<String, Set<Integer>> wordAppearances = new HashMap<>();
        String line;
        Map<Integer, MyGraph.Node> idToNode = new HashMap<>();
        while (!synsets.isEmpty()) {
            line = synsets.readLine();
            String[] parts = line.split(",", 3); // Split into three parts: id, word, definition
            int id = Integer.parseInt(parts[0]);
            String[] words = parts[1].split(" ");
            String definition = parts[2];
            TreeSet<String> uniqueWords = new TreeSet<>(List.of(words));

            if (uniqueWords.size() == 1) {
                String word = uniqueWords.first();
                MyGraph.Node node = graph.createNode(words, definition);
                idToNode.put(id, node);
                graph.addNode(node, id);
                if (wordAppearances.containsKey(word)) {
                    wordAppearances.get(word).add(id);
                }
                if (!wordAppearances.containsKey(word)) {
                    Set<Integer> wordAppearance = new HashSet<>();
                    wordAppearances.put(word, wordAppearance);
                    wordAppearances.get(word).add(id);
                }
            } else if (uniqueWords.size() > 1) {
                MyGraph.Node node = graph.createNode(words, definition);
                idToNode.put(id, node);
                for (String word : uniqueWords) {
                    if (wordAppearances.containsKey(word)) {
                        wordAppearances.get(word).add(id);
                    }
                    if (!wordAppearances.containsKey(word)) {
                        Set<Integer> wordAppearance = new HashSet<>();
                        wordAppearances.put(word, wordAppearance);
                        wordAppearances.get(word).add(id);
                    }
                }
            }
        }
        while (!hyponyms.isEmpty()) {
            String hline = hyponyms.readLine();
            String[] parts = hline.split(",");

            if (parts.length > 1) {
                int wordID = Integer.parseInt(parts[0]);
                for (int i = 1; i < parts.length; i++) {
                    int hyponymID = Integer.parseInt(parts[i]);
                    MyGraph.Node node = idToNode.get(wordID);
                    node.addHyponym(hyponymID);
                }

            }
        }
        this.wordAppearances = wordAppearances;
        this.idToNode = idToNode;
        this.ngm = ngm;
    }


    public Set<String> getHyponyms(String word) {
        if (!wordAppearances.containsKey(word)) {
            return new TreeSet<String>();
        }
        Set<String> result = new TreeSet<>();
        Set<Integer> visited = new HashSet<>();
        getHyponymsRecursive(word, result, visited);
        result.add(word);

        return result;
    }

    private void getHyponymsRecursive(String word, Set<String> result, Set<Integer> visited) {
        Set<Integer> wordAppearancesSet = wordAppearances.get(word);
        for (int wordAppearance : wordAppearancesSet) {
            if (!visited.contains(wordAppearance)) {
                visited.add(wordAppearance);
                MyGraph.Node node = idToNode.get(wordAppearance);
                addHyponymsOfNode(node, result, visited);
                for (String w : node.getWord()) {
                    result.add(w);
                }
            }
        }
    }

    private void addHyponymsOfNode(MyGraph.Node node, Set<String> result, Set<Integer> visited) {
        List<Integer> hyponyms = node.getHyponyms();
        for (int hyponymID : hyponyms) {
            MyGraph.Node hyponymNode = idToNode.get(hyponymID);
            if (!hyponymNode.getHyponyms().isEmpty()) {
                addHyponymsOfNode(hyponymNode, result, visited);
            }
            result.addAll(hyponymNode.getWord());
        }
    }

    public Set<String> getSharedHyponyms(List<String> words) {
        Set<String> sharedHyponyms = new TreeSet<>();
        String firstWord = words.get(0);
        Set<String> firstWordHyponyms = getHyponyms(firstWord);
        for (int i = 1; i < words.size(); i++) {
            String currentWord = words.get(i);
            Set<String> hyponymsCurrentWord = getHyponyms(currentWord);

            // Find the intersection of hyponyms with the previous word
            firstWordHyponyms.retainAll(hyponymsCurrentWord);
        }
        sharedHyponyms.addAll(firstWordHyponyms);
        return sharedHyponyms;
    }
    public double popularity(String word, int startYear, int endYear) {
        TimeSeries t = ngm.countHistory(word, startYear, endYear);
        double result = 0.0;
        for (double v: t.values()) {
            result += v;
        }
        return result;
    }

}

