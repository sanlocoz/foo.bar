public class Solution {
    private final static String BRAILLE = "100000110000100100100110100010110100110110110010010100010110101000111000101100101110101010111100111110111010011100011110101001111001010111101101101111101011000000000001";
    private final static int CAPITAL = 28;
    private final static int SPACE = 27;
    private final static int NUM_CHAR = 6;

    private static String braille(char a) {
        String answer="";
        if (Character.isUpperCase(a))
        {
            answer+=BRAILLE.substring((CAPITAL-1)*NUM_CHAR, CAPITAL*NUM_CHAR);
        }
        else if (a==' ')
        {
            answer+=BRAILLE.substring((SPACE-1)*NUM_CHAR, SPACE*NUM_CHAR);
            return answer;
        }

        int NUM = Character.toLowerCase(a)-'a';
        answer+=BRAILLE.substring((NUM)*NUM_CHAR, (NUM+1)*NUM_CHAR);
        
        return answer;
    }

    public static String solution(String s) {
        String answer="";
        for(int i = 0; i<s.length(); i++)
        {
            answer+=braille(s.charAt(i));
        }
        return answer;
    }

    public static void main(String[] args) {
        //String query="a";
        //System.out.println(query.charAt(0));
        System.out.println(solution("The quick brown fox jumps over the lazy dog"));
    }
}