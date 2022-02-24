public class tester {
    public static void main(String[] args) {

        String testWord = "grandfather";
        String mysteryWord = "grandfather";
        
        Word test_word = new Word(Letter.fromString(testWord));
        Word mystery_word = new Word(Letter.fromString(mysteryWord));

        System.out.println(test_word);

        boolean identical = test_word.labelWord(mystery_word);

        //System.out.println(evaluation);
        System.out.println(test_word);
        System.out.println(identical);

        String[] strings = {"Testing", "Line", "Separator", "Function"};
        String output = lineSeparating(strings);

        System.out.println(output);


    }

    public static String lineSeparating(String[] words) {

        String s = "";

        for (int j = 0; j < words.length; j++) {
            s += words[j];
            if (j < words.length - 1); s += System.lineSeparator(); 
        }
        
        return s;
    }

}
