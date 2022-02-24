public class Letter {
    char letter;
    int label;

    int UNSET=1, UNUSED=2, USED=3, CORRECT=4;

    public Letter(char c){
        label = UNSET;
        letter = c;
    }

    public boolean equals(Object otherObj){
        if (otherObj instanceof Letter){ //check if the object is instance of Letter class
            Letter obj = (Letter) otherObj; // if it is we know that the otherObj is of type Letter and can use it as a cast
            if (obj.letter == this.letter){ // check the other obj letter and if it is the same as this.letter
                return true; // if it is, return true
            }
        }

        return false; // in the event none of it is true, return false

    }

    public String decorator(){
        
        if (this.label == UNUSED) {
            return "-";
        } 

        if (this.label == USED) {
            return "+";
        }
        
        if (this.label == UNSET) {
            return " ";
        }

        return "!"; // if the label is none of the above, it has to be CORRECT, therefore return !
    }

    public String toString() {
        return decorator() + letter + decorator();
    }

    public void setUnused() {
        label = UNUSED;
    }

    public void setUsed() {
        label = USED;
    }

    public void setCorrect() { 
        label = CORRECT;
    }

    public boolean isUnused() {
        boolean distinction = this.label == UNUSED ? true : false;
        return distinction;
    }

    public static Letter[] fromString(String s) {
        Letter[] letters;
        String givenWord = s;

        letters = new Letter[givenWord.length()]; // initialize the letters array with the legnth of the given word

        for (int i = 0; i <= givenWord.length() - 1; i++ ){   
            letters[i] = new Letter(givenWord.charAt(i)); // each index of the letter array is given a new letter with 
                                                         // the character at that respective index
        }

        return letters; // in the end return letters array

    }

}
