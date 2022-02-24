public class tester {
    public static void main(String[] args) {

        String testWord = "test";
        String mysteryWord = "totter";
        
        Word test_word = new Word(Letter.fromString(testWord));
        Word mystery_word = new Word(Letter.fromString(mysteryWord));

        System.out.println(test_word);

        test_word.labelWord(mystery_word);

        //System.out.println(evaluation);
        System.out.println(test_word);




    }
}
