package main;


import java.util.Comparator;

    public class PopularityComparator implements Comparator<String> {

        private final WordParser wordParser;
        public int startYear;
        public int endYear;

            public PopularityComparator(WordParser wordParser, int startYear, int endYear) {
                this.wordParser = wordParser;
                this.startYear = startYear;
                this.endYear = endYear;
            }

            @Override
            public int compare(String w1, String w2) {
                double popularity1 = wordParser.popularity(w1, startYear, endYear);
                double popularity2 = wordParser.popularity(w2, startYear, endYear);

               return (int) (popularity2 - popularity1);


        }
    }
