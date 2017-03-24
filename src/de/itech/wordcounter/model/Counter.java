package de.itech.wordcounter.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Counter {

	public static String wordCount(String text, Boolean topWordsOnly) {

        // topWordsOnly default = false;
		// regex to get rid of all the weird stuff
		String[] sortedInput = text.split("\\P{L}+");
		StringBuilder answer = new StringBuilder();
		Map<String, Integer> allWords = new HashMap<String, Integer>();

		int x;

		for (int i = 0; i < sortedInput.length; i++) {
			if (allWords.containsKey(sortedInput[i])) {
				x = allWords.get(sortedInput[i]);
				allWords.put(sortedInput[i], x + 1);
			} else {
				allWords.put(sortedInput[i], 1);
			}
		}
		
		answer.append("Total words: " + sortedInput.length + "\n\n");
		
		// if checkbox is selected remove all words with value 1
		if (topWordsOnly) {
			allWords.entrySet().removeIf(e -> e.getValue() == 1);
			if (allWords.isEmpty()) {
				answer.append("Sorry, nothing more to show here. All words occured only once.");
			}
		}

		// java8 magic
		Map<Object, Object> sortedWords = allWords.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		// iterate over the pairs
		for (Object key : sortedWords.keySet()) {
			answer.append(key + ": " + allWords.get(key) + "\n");
		}

		return answer.toString();
	}
}