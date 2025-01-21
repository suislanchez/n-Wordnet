    package main;

    import browser.NgordnetQueryHandler;


    public class AutograderBuddy {
        /** Returns a HyponymHandler */
        public static NgordnetQueryHandler getHyponymHandler(
                String wordFile, String countFile,
                String synsetFile, String hyponymFile) {

            WordParser wordParser = new WordParser(synsetFile, hyponymFile, wordFile, countFile);
            return new HyponymsHandler(wordParser);
        }
    }
