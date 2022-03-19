public class DLStack<T> implements DLStackADT<T>{
    private DoubleLinkedNode<T> top;
    private int numItems;

    public DLStack() {
        top = null; // create empty stack
        numItems = 0;
    }

    public void push(T dataItem) {
        DoubleLinkedNode<T> temp = new DoubleLinkedNode<>(dataItem);
        
        if (top == null) {
            temp.setNext(null);
            temp.setPrevious(null);
            top = temp;
            numItems++;
        } else {
            temp.setNext(top);
            temp.setPrevious(null);
            top.setPrevious(temp);
            top = temp;
            numItems++;
        }

    }

    public T pop() throws EmptyStackException {
        if (isEmpty()) throw new EmptyStackException("Empty Stack");

        T result = top.getElement();
        top = top.getNext();
        top.setPrevious(null);
        numItems--;
        return result;
        
    }

    public T pop(int k) throws InvalidItemException {
        /**
         * Removes and returns the kth item from the stack.
         */

        DoubleLinkedNode<T> current = top;
        T result;


        if (k > size()) throw new InvalidItemException("Number not in range of the stack");
        else if (k <= 0) throw new InvalidItemException("Number not in range of the stack");

        for (int i = 1; i < k; i++) {
            current = current.getNext();
        }

        if (current == top) { // if the item to remove is the top of the stack
            result = current.getElement(); // get the result
            top = top.getNext(); // make the top the next element
            if (top != null) top.setPrevious(null); // if the top is not null, set the previous of he top to null
            numItems--; // take away 1 from the number of items.
            return result; 
        } else if (current.getNext() == null){ // if the item is at the very end of the stack
            result = current.getElement(); // take the result
            current = current.getPrevious(); // set the current to the previous
            if (current.getNext() != null) current.setNext(null); // if the next of the new current is not null, set it to null
            numItems--; // take away 1 from the number of items.
            return result;
        } else { // if the item is anywhere else
            result = current.getElement(); // take the result
            current.getPrevious().setNext(current.getNext()); // get previous of current and set it to the next of the current
            numItems--; // take away 1 from the number of items.
            return result; // return the result
        }

    }

    public T peek() throws EmptyStackException {
        if (top == null) throw new EmptyStackException("Empty Stack");
        return top.getElement();
    }

    public boolean isEmpty() {
        if (top == null) return true;
        else return false;
    }

    public int size() { 
        return numItems;
    }

    public DoubleLinkedNode<T> getTop() {
        return top;
    }

    public String toString() {
        String s = "";

        DoubleLinkedNode<T> temp = top;
        
        String[] elements = new String[size()];

        for (int i = 0; i <= size(); i++) {
            String stringRep = temp.getElement().toString();
            elements[i] = stringRep;
            temp = temp.getNext();
        }

        for (int i = 0; i < elements.length; i++) {
            s += elements[i];
            if (i < elements.length) s+= " "; // add spaces to each index
        }

        return "[" + s + "]";

    }



}
