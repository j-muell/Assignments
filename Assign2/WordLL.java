public class WordLL {
    private Word mysteryWord;
    private LinearNode<Word> history;
   
    // PUBLIC MAIN METHODS

    public WordLL(Word mystery) {

        history = null;
        mysteryWord = mystery;

    }

    public boolean tryWord(Word guess) {
        boolean identical = guess.labelWord(mysteryWord); // Run label word

        insertAtStart(guess); // use insert function

        if (identical) {
            return true;
        }

        return false;
    }

    public String toString() {
        
        LinearNode<Word> temp = history; // temp node based on history

        int historyListSize = getListSize(); // size of the history linked list
        String[] wordStrings = new String[historyListSize];

        for (int i = 0; i <= historyListSize - 1; i++) {
            String wordStringRep = temp.getElement().toString(); // element is a word, which has toString
            wordStrings[i] = wordStringRep; // save to the wordStrings array
            temp = temp.getNext(); // get next node
        }

        String s = "";

        for (int j = 0; j < wordStrings.length; j++) {
            s += wordStrings[j]; // save each index of the array into a string
            if (j < wordStrings.length - 1) s += System.lineSeparator(); // this will print all of it on new lines
        }
        
        return s;

    }

   // PRIVATE HELPER METHODS

    private void insertAtStart(Word data) { // Insert function to 
        LinearNode<Word> node = new LinearNode<>();

        node.setElement(data);
        node.setNext(null);
        node.setNext(history);
        history = node;

    }

    private int getListSize() {
        int numElements = 0;

        LinearNode<Word> temp = history; // Get list size for the linked list

        while (temp.getNext() != null)
        {
            numElements++;
            temp = temp.getNext();
        }

        numElements++;

        return numElements;
    }

}


