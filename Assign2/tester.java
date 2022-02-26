import javax.swing.event.SwingPropertyChangeSupport;

public class tester {
    public static void main(String[] args) {

        String testWord = "object";
        String mysteryWord = "code";
        
        Word test_word = new Word(Letter.fromString(testWord));
        Word mystery_word = new Word(Letter.fromString(mysteryWord));

        System.out.println(test_word);

        boolean identical = test_word.labelWord(mystery_word);

        //System.out.println(evaluation);
        System.out.println(test_word);
        System.out.println(identical);



        Word word1 = new Word(Letter.fromString("OBJECT"));
		Word word2 = new Word(Letter.fromString("CLASS"));
		Word word3 = new Word(Letter.fromString("CODE"));


        word1.labelWord(word3); 

        System.out.println(word1);
        
        boolean test = word1.toString().equals("falseWord: +O+ -B- -J- !E! +C+ -T- ");

        System.out.println(test);


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
