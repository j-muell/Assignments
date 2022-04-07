public class TreeBuilder<T> {

    public LinkedBinaryTree<T> buildTree(T[] data) {
        LinkedQueue<T> dataQueue = new LinkedQueue<>();

        for (int i = 0; i < data.length; i++) { // go through the data and enqueue it
            dataQueue.enqueue(data[i]);
        }

        LinkedQueue<BinaryTreeNode<T>> parentQueue = new LinkedQueue<>();

        LinkedBinaryTree<T> dataTree = new LinkedBinaryTree<>(dataQueue.dequeue());
        
        parentQueue.enqueue(dataTree.getRoot());

        while (!dataQueue.isEmpty()) {

            // if getting children nodes you need to continue to dequeue until get a parent
            BinaryTreeNode<T> a = new BinaryTreeNode<T>(dataQueue.dequeue());
            BinaryTreeNode<T> b = new BinaryTreeNode<T>(dataQueue.dequeue());
            BinaryTreeNode<T> parent = parentQueue.dequeue();

            while (parent.getData() == null) { // check that the parent is not null
                parent = parentQueue.dequeue(); // if it is, find a new parent
            }

            if (a.getData() != null) { // check data of a for null
                parent.setLeft(a); // set the left parent as the a node
                parentQueue.enqueue(a); // enqueue it
            }


            if (b.getData() != null) { // same as prior if statement
                parent.setRight(b);
                parentQueue.enqueue(b);
            }

        }

        return dataTree; // return the tree

    }

}