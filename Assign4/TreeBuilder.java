public class TreeBuilder<T> {

    public LinkedBinaryTree<T> buildTree(T[] data) {
        LinkedQueue<T> dataQueue = new LinkedQueue<>();

        for (int i = 0; i < data.length; i++) {
            dataQueue.enqueue(data[i]);
        }

        LinkedQueue<BinaryTreeNode<T>> parentQueue = new LinkedQueue<>();

        LinkedBinaryTree<T> tree = new LinkedBinaryTree<>(dataQueue.first());
        
        parentQueue.enqueue(tree.getRoot());

        while (!dataQueue.isEmpty()) {

            // if getting children nodes you need to continue to dequeue until get a parent
            T a = dataQueue.dequeue();
            
            BinaryTreeNode<T> parent = parentQueue.dequeue();

            if (a != null) {
                BinaryTreeNode<T> newA = new BinaryTreeNode<T>(a);
                parent.setLeft(newA);
                parentQueue.enqueue(parent.getLeft());
            }

            if (dataQueue.isEmpty()) break;

            T b = dataQueue.dequeue();
            if (b != null) {
                BinaryTreeNode<T> newB = new BinaryTreeNode<T>(b);
                parent.setRight(newB);
                parentQueue.enqueue(parent.getRight());
            }

        }

        return tree;

    }

 // update
}