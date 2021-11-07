// Url: https://leetcode.com/problems/longest-substring-without-repeating-characters/submissions/
// Difficulty: Medium

/**
* Problem:
*
* Given a string s, find the length of the longest substring without repeating characters.
*/

/** 
* Runtime: 159 ms, faster than 10.64% of Java online submissions for Longest Substring Without Repeating Characters.
* Memory Usage: 39.9 MB, less than 45.56% of Java online submissions for Longest Substring Without Repeating Characters.
*/

import java.util.HashSet;

class Solution {
    
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> lettersPositions = new HashMap<Character, Integer>();
        int longestSequence = 0;
        
        for(int i = 0; i < s.length(); i++){

            Character letter = new Character(s.charAt(i));
            
            if (lettersPositions.get(letter) != null) lettersPositions = remove(lettersPositions, letter);
            
            lettersPositions.put(letter, i);
            
            if(lettersPositions.keySet().size() > longestSequence) longestSequence = lettersPositions.keySet().size();
        }
        
        return longestSequence;
    }
    
    private Map<Character, Integer> remove(Map<Character, Integer> lettersPositions, Character letter){
        Integer positionWanted = lettersPositions.get(letter);
        
        lettersPositions = lettersPositions.entrySet().stream()
            .filter(entry -> entry.getValue() > positionWanted)
            .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
        
        return lettersPositions;
    }
}