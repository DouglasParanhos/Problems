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