package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;

import java.util.*;

public class HyponymsHandler extends NgordnetQueryHandler {

    private final WordParser wordParser;

    public HyponymsHandler(WordParser wordParser) {
        this.wordParser = wordParser;
    }


    @Override
    public String handle(NgordnetQuery q) {
        PriorityQueue<String> pq;
        List<String> words = q.words();
        List<String> empty = new ArrayList<>();
        if (q.k() == 0) {
            if (words.isEmpty()) {
                return words.toString();
            }
            if (words.size() == 1) {
                String word = words.get(0);
                return wordParser.getHyponyms(word).toString();
            } else if (words.size() >= 2) {
                return wordParser.getSharedHyponyms(words).toString();
                // get hyponyms of all word, but only return a list of
            }
        } else if (q.k() > 0) {
            pq = new PriorityQueue<>(new PopularityComparator(wordParser, q.startYear(), q.endYear()));
            if (words.size() == 1) {
                String word = words.get(0);
                Set<String> hyponyms = wordParser.getHyponyms(word);
                for (Iterator<String> iterator = hyponyms.iterator(); iterator.hasNext(); ) {
                    String r = iterator.next();
                    double pop = wordParser.popularity(r, q.startYear(), q.endYear());
                    if (pop <= 0) {
                        iterator.remove();
                    }
                }
                for (String hypo : hyponyms) {
                    pq.offer(hypo);
                }
                int k = q.k();
                TreeSet<String> result = new TreeSet<>();
                int count = 0;
                while (!pq.isEmpty() && count < k) {
                    result.add(pq.poll());
                    count++;
                }
                return result.toString();
            }
            if (words.size() >= 2) {
                Set<String> hyponyms = wordParser.getSharedHyponyms(words);
                for (Iterator<String> iterator = hyponyms.iterator(); iterator.hasNext(); ) {
                    String r = iterator.next();
                    double pop = wordParser.popularity(r, q.startYear(), q.endYear());
                    if (pop <= 0) {
                        iterator.remove();
                    }
                }
                for (String hypo : hyponyms) {
                    pq.offer(hypo);
                }
                int k = q.k();
                TreeSet<String> result = new TreeSet<>();
                int count = 0;
                while (!pq.isEmpty() && count < k) {
                    result.add(pq.poll());
                    count++;
                }
                return result.toString();
            }
            return empty.toString();
        }
        return empty.toString();
    }
}
