public class Word {
    //private this after
    private LinearNode<Letter> firstLetter = null; // head of list
    
    // Public main methods

    public Word(Letter[] letters) {
        for (int i = 0; i <= letters.length - 1; i++) {

            insert(letters[i]); // run the insert helper method for each part of the letters array

        }
    } 

    public String toString(){
        LinearNode<Letter> temp = firstLetter;

        int linkedListSize = getListSize(); // get the size of the linkedList 

        String[] letterStrings = new String[linkedListSize]; // create a new String array based on size of linked list

        

        for (int i = 0; i <= linkedListSize - 1; i++) // iterate until end of the size of the linked list
        {
            String letterStringRep = temp.getElement().toString(); // for each iteration, take the toString of each element
            letterStrings[i] = letterStringRep; // set respective toString to its place in the letterStrings
            temp = temp.getNext(); // getNext node
        }

        String s = "";

        // This will take the String Array created and turn it into a string itself

        for (int i = 0; i < letterStrings.length; i++) {
            s += letterStrings[i];
            if (i < letterStrings.length - 1) s+= " "; // keep a space if it is not the last element.
        }

        return "Word: " + s; // finally, return the string with the proper formatting

    }

    public boolean labelWord(Word trueWord) { // updates the labels of this word based on the trueWord

        int thisWordSize = this.getListSize();
        int trueWordSize = trueWord.getListSize();

        LinearNode<Letter> thisTemp = this.firstLetter;
        LinearNode<Letter> trueWordTemp = trueWord.firstLetter;


        boolean identical = false;

        // UPDATE TAGS
        
        for (int i = 0; i <= trueWordSize - 1; i++) // loop through thisWord nodes
        {
            thisTemp = this.firstLetter;
            for (int j = 0; j <= thisWordSize - 1; j++) // loop through true word nodes
            {
                // check if tag is used or correct already

                if (thisTemp.getElement().label == thisTemp.getElement().CORRECT) {
                    // if the tag is correct, just do nothing to it.
                    thisTemp = thisTemp.getNext();
                    continue;
                }

                if (thisTemp.getElement().label == thisTemp.getElement().USED){
                    // if the tag is used, check again for letter and index to see if its actually correct position
                    // this handles the possibility of double letters ex. test and totter.
                    if (thisTemp.getElement().letter == trueWordTemp.getElement().letter) {
                        thisTemp.getElement().setUsed();

                        if (i == j) {
                            thisTemp.getElement().setCorrect();
                        }
                    }
                    // if they are not the same tag, just go to the next node and continue iteration
                    thisTemp = thisTemp.getNext();
                    continue;                    
                }
                if (thisTemp.getElement().letter == trueWordTemp.getElement().letter) { 
                    // if the letter in thisWord node is equal to the letter of the trueWord node

                    thisTemp.getElement().setUsed(); // then set the tag to used

                    if (i == j) {
                        // if the exact index of thisWord and trueWord are the same, its the exact position index wise.
                        thisTemp.getElement().setCorrect(); // set to correct entirely
                    }
                } else {
                    thisTemp.getElement().setUnused();
                }

                thisTemp = thisTemp.getNext(); // after running the if statements, go to the next node in trueWord

            }

            trueWordTemp = trueWordTemp.getNext();
        }


        thisTemp = this.firstLetter;
        int correctLetters = 0;
        for (int k = 0; k <= thisWordSize - 1; k++) { // run through thisWord nodes
            if (thisTemp.getElement().label == thisTemp.getElement().CORRECT) { // if the label is correct
                correctLetters += 1;
            } else { // if label is ever not correct
                identical = false; // set to false
            }
            
            thisTemp = thisTemp.getNext(); // get next node

        }

        if (correctLetters == trueWordSize){
            identical = true;
        }

        if (identical == true) { // in the end, if the node is still true
            return true; // return true, as in the correct word was guessed
        }

        return false; // if it is not true, return false. full word not guessed

    }

    // Private Helper Methods

    private void insert(Letter data){ // insertion method for inside the linked list
        LinearNode<Letter> node = new LinearNode<>();
        node.setElement(data); // set the element of the node to the letter that is in the loop
        node.setNext(null); // set the next position to null
        
        if (firstLetter == null)
        {
            firstLetter = node;

        } else
        {
            LinearNode<Letter> n = firstLetter;
            while (n.getNext() != null) 
            {
                n = n.getNext();
            }

            n.setNext(node);
        }
    }

    private int getListSize() {
        int numElements = 0;

        LinearNode<Letter> temp = firstLetter;

        while (temp.getNext() != null)
        {
            numElements++;
            temp = temp.getNext();
        }

        numElements++;

        return numElements;
    }

}
