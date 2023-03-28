package application;
import java.io.*;
import java.util.*;

public class mod6v1 {
	public static void main(String[] args) throws IOException {
		// Read the file 
		File file = new File("Mod2.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
			
		// Create a map to store the words and their frequency
		Map<String, Integer> wordCounts = new HashMap<String, Integer>();
		
		// Read the file line by line
		String line;
		while ((line = br.readLine()) != null) {
			// Split the line into individual words
			String[] words = line.trim().split("\\s+");
				
			// For each word, add it to the map and increase its count by 1
			for (String word : words) {
				if (!word.isEmpty()) {
					if (wordCounts.containsKey(word)) {
						wordCounts.put(word, wordCounts.get(word) + 1);
					} else {
						wordCounts.put(word, 1);
					}
				}
			}
		}
		br.close();
		// Sort the map by value
		List<Map.Entry<String, Integer>> list = new LinkedList<>(wordCounts.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
		@Override
		public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
			return (o2.getValue()).compareTo(o1.getValue());
			}
		}
	);
	// Output the words in the sorted order
	System.out.println("Count for |  Word by   |  Word ");
	System.out.println(" top 20   |  FRQNCY    |  Count");
	System.out.println("===============================");
	int order = 1;
	for (Map.Entry<String, Integer> entry : list) {
		System.out.println("   " + order + "   " + "  |  " + "  " + entry.getKey() + "   " + "  |  " + entry.getValue());
		order++;
	}
}
}
