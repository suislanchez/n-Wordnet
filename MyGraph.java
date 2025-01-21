package main;

import java.util.*;

public class MyGraph {
    MyGraph() {
        this.nodes = new HashMap<>();
        this.wordHyponyms = new HashMap<>();
        this.edges = new ArrayList<>();
    }
    private Map<Integer, Node> nodes;
    private Map<Integer, List<Integer>> wordHyponyms;
    private List<Edge> edges;

    public void addNode(Node node, int wordID) {
        nodes.put(wordID, node);
        wordHyponyms.put(wordID, new ArrayList<>());
    }

    public void addEdge(int wordID, int hyponymID) {
        edges.add(new Edge(wordID, hyponymID));
        wordHyponyms.get(wordID).add(hyponymID);
    }
    public class Node {
        private String[] word;
        private String definition;

        private List<Integer> hyponyms;
        public Node(String[] word, String definition) {
        this.word = word;
        this.definition = definition;
        }
        public Set<String> getWord() {
            HashSet<String> words = new HashSet<>(List.of((word)));
            return words;
        }
        public List<Integer> getHyponyms() {
            if (this.hyponyms == null) {
                List<Integer> empty = new ArrayList<>();
                return empty;
            }
            return this.hyponyms;
        }
        public void addHyponym(int id) {
            if (this.hyponyms == null) {
                this.hyponyms = new ArrayList<>();
                this.hyponyms.add(id);
            }
            this.hyponyms.add(id);
        }
    }
    public class Edge {
        private int source;
        private int target;

        public Edge(int hypernymID, int hyponymID) {
            this.source = hypernymID;
            this.target = hyponymID;
        }
    }


    public Node createNode(String[] word, String definition) {
        return new Node(word, definition);
    }

}
