public class Word {
    
    private LinearNode<Letter> firstLetter = null; // head of list
    
    public Word(Letter[] letters) {
        for (int i = 0; i <= letters.length - 1; i++) {

            insert(letters[i]);

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
