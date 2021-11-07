import java.util.HashSet;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> letters = new HashSet<Character>();
        int longestSequence = 0;
        
        for(int i = 0; i < s. length(); i++){

            Character letter = new Character(s.charAt(i));
            
            if(letters.contains(letter)){
                
                if(letters.size() > longestSequence){
                    longestSequence = letters.size();
                }
                
                letters.clear();
            }
            
            letters.add(letter);
        }
        
        return letters.size() > longestSequence ? letters.size() : longestSequence;
    }
}