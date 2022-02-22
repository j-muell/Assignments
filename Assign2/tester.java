public class tester {
    public static void main(String[] args) {
        // Character charB = new Character('b');
        // Character charE = new Character('e');
        // Character charA = new Character('a');
        // Character charR = new Character('r');
        // Letter b = new Letter(charB);
        // Letter e = new Letter(charE);
        // Letter a = new Letter(charA);
        // Letter r = new Letter(charR);

        String bear = "bear";

        Letter[] word = Letter.fromString(bear);


        Word BEAR = new Word(word);

        System.out.println(BEAR);


    }
}
