# n-Wordnet: Semantic Analysis and Word Frequency Tool

n-Wordnet is an open-source project inspired by Google Ngrams, designed to analyze and visualize the frequency and relationships of words and phrases in literature and other text databases. By leveraging semantic relationships and graph-based representations, n-Wordnet helps users explore word frequencies, synonyms, hyponyms, and their temporal trends.

---

## Features

### Semantic Relationship Exploration
- **WordNet Integration**: Utilize a semantic lexicon to explore relationships like synonyms, hypernyms, and hyponyms.
- **Graph-Based Representation**: Visualize semantic relationships as a directed graph.

### Word Frequency Analysis
- **Historical Trends**: Analyze word and phrase occurrences over time using literature datasets.
- **Custom Time Ranges**: Specify start and end years for granular analysis.

### Dynamic Querying
- **Single or Multi-Word Queries**: Analyze words individually or in groups to identify intersecting hyponyms or trends.
- **Contextual Filtering**: Limit results by frequency thresholds or semantic relationships.

### Customizable Output
- **Top k Results**: Retrieve the most frequently occurring words or phrases, sorted by usage over a specified period.
- **Alphabetical or Frequency Sorting**: Results can be ordered based on user preference.

---

## How It Works

### WordNet Dataset
- **Synsets**: Groups of words with similar meanings, connected via semantic relationships.
- **Hyponyms and Hypernyms**: Hierarchical connections (e.g., "change" → "modification" → "alteration").

### Frequency Data
- Historical literature datasets track the usage of words and phrases over specified timeframes.

---

## Example Usage

### Query 1: Semantic Exploration
- **Input**: `"change"`  
- **Output**: `[alteration, modification, adjustment, transition]`

### Query 2: Word Frequency Over Time
- **Input**: `"food, cake"` (1950–1990, top 5 results)  
- **Output**: `[biscuit, cake, kiss, snap, wafer]`

---

## Getting Started

### Prerequisites
- **Java 8+** or equivalent runtime environment.
- Basic understanding of graph structures for customization.

### Installation
1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/n-Wordnet.git
   cd n-Wordnet
