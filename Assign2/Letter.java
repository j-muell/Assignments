public class Letter {
    char letter;
    int label;

    int UNSET, UNUSED, USED, CORRECT;

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

    }
}
