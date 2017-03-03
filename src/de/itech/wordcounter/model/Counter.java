package de.itech.wordcounter.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Counter {

	public static String wordCount(String s, Boolean top) {

//		top = false;
		// regex to get rid of all the weird stuff
		String[] words = s.split("\\P{L}+");
		StringBuilder answer = new StringBuilder();
		Map<String, Integer> map = new HashMap<String, Integer>();

		int x;

		for (int i = 0; i < words.length; i++) {
			if (map.containsKey(words[i])) {
				x = map.get(words[i]);
				map.put(words[i], x + 1);
			} else {
				map.put(words[i], 1);
			}
		}
		
		answer.append("Total words: " + words.length + "\n\n");
		
		// if checkbox is selected remove all words with value 1
		if (top) {
			map.entrySet().removeIf(e -> e.getValue() == 1);
			if (map.isEmpty()) {
				answer.append("Sorry, nothing more to show here. All words occured only once.");
			}
		}

		// java8 magic
		Map<Object, Object> sorted = map.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		// iterate over the pairs
		for (Object key : sorted.keySet()) {
			answer.append(key + ": " + map.get(key) + "\n");
		}

		return answer.toString();
	}
}