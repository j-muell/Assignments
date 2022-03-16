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
            top.setPrevious(temp);
            top = temp;
            numItems++;
        }

    }

    public T pop() throws EmptyStackException {
        /**
         * Remove and return top element of the stack
         */
    }

    public T pop(int k) throws InvalidItemException {
        /**
         * Removes and returns the kth item from the stack.
         */
    }

    public T peek() throws EmptyStackException {

    }

    public boolean isEmpty() {

    }

    public int size() { 

    }

    public DoubleLinkedNode<T> getTop() {

    }

    public String toString() {
        
    }



}
