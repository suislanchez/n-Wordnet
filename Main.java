package main;

import browser.NgordnetServer;

public class Main {
        public static void main(String[] args) {
        NgordnetServer hns = new NgordnetServer();
        


        String wordFile = "src/data/ngrams/very_short.csv";
        String countFile = "src/data/ngrams/total_counts.csv";

        String synsetsFile = "/data/wordnet/synsets.txt";
        String hyponymsFile = "/data/wordnet/hyponyms.txt";
        WordParser parser = new WordParser(synsetsFile,hyponymsFile, wordFile, countFile);


        hns.startUp();
        hns.register("history", new DummyHistoryHandler());
        hns.register("historytext", new DummyHistoryTextHandler());
        hns.register("hyponyms", new HyponymsHandler(parser));

        System.out.println("Finished server startup! Visit http://localhost:4567/ngordnet.html");
    }
}
