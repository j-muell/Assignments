public class Ski {
    
    private BinaryTreeNode<SkiSegment> root;

    public Ski(String[] data) {
        // takes in an array of possible ski types
        SkiSegment[] segments = new SkiSegment[data.length];

        for (int i = 0; i < data.length; i++) {

            if (data[i] == null) {
                segments[i] = null;
                continue;
            }

            if (data[i].contains("jump")) {
                segments[i] = new JumpSegment(String.valueOf(i), data[i]);
                continue;
            }

            else if (data[i].contains("slalom")) {
                segments[i] = new SlalomSegment(String.valueOf(i), data[i]);
                continue;
            }

            segments[i] = new SkiSegment(String.valueOf(i), data[i]);

        }




        TreeBuilder<SkiSegment> newTreeObj = new TreeBuilder<SkiSegment>();

        LinkedBinaryTree<SkiSegment> tree = newTreeObj.buildTree(segments);
        root = tree.getRoot();

    }

    public BinaryTreeNode<SkiSegment> getRoot() {
        return root;
    }

    public void skiNextSegment(BinaryTreeNode<SkiSegment> node, ArrayUnorderedList<SkiSegment> sequence) {
        
        sequence.addToRear(node.getData());
        if (node.getLeft() == null && node.getRight() == null) return; // base case is when the node is a leaf (left and right have nothing in them)

        skiNextSegment(testRightLeft(node), sequence);

    }
    
    private BinaryTreeNode<SkiSegment> testRightLeft(BinaryTreeNode<SkiSegment> node) {


        if (node.getLeft() == null) return node.getRight(); // if the only way to go is right, return right
        if (node.getRight() == null) return node.getLeft(); // if the only way to go is left, return left
        
        
        BinaryTreeNode<SkiSegment> leftNode = node.getLeft(); 
        BinaryTreeNode<SkiSegment> rightNode = node.getRight();

        // CASES WITH TWO CHILDREN

        String leftNodeString = leftNode.getData().getClass().toString().substring(6); // this will be used to identify which class is being used
        String rightNodeString = rightNode.getData().getClass().toString().substring(6);

        if (leftNodeString.equals("JumpSegment") && rightNodeString.equals("JumpSegment")) { // test for both jump case
            JumpSegment jumpSegLeft = (JumpSegment) node.getLeft().getData();
            JumpSegment jumpSegRight = (JumpSegment) node.getRight().getData();

            int leftHeight = jumpSegLeft.getHeight();
            int rightHeight = jumpSegRight.getHeight();

            if (leftHeight > rightHeight) return leftNode;
            return rightNode;
        } 
        
        if (leftNodeString.equals("JumpSegment") && !rightNodeString.equals("JumpSegment")) return leftNode; // if the left node contains jump and right node does not
        if (rightNodeString.equals("JumpSegment") && !leftNodeString.equals("JumpSegment")) return rightNode; // if the right node contains jump and left node does not

        if (rightNodeString.equals("SlalomSegment") && leftNodeString.equals("SlalomSegment")) {

            SlalomSegment slalomSegLeft = (SlalomSegment) leftNode.getData();
            SlalomSegment slalomSegRight = (SlalomSegment) rightNode.getData();

            if (slalomSegRight.getDirection() == "W") return leftNode; // if the right node contains W, return left 
            if (slalomSegLeft.getDirection() == "W") return rightNode; // if the left node contains W, return right
        }

        // If there is a slalom AND a regular

        if (rightNodeString.equals("SlalomSegment") && leftNodeString.equals("SkiSegment")) { // right child
            SlalomSegment slalomSegRight = (SlalomSegment) rightNode.getData();

            if (slalomSegRight.getDirection() == "W") return node.getLeft(); // test for windward 
            return rightNode;
        }

        if (leftNodeString.equals("SlalomSegment") && rightNodeString.equals("SkiSegment")) { // left child
            SlalomSegment slalomSegLeft = (SlalomSegment) leftNode.getData();

            if (slalomSegLeft.getDirection() == "W") return rightNode; // test for windward
            return leftNode;
        }

        if (leftNodeString.equals("SkiSegment") && rightNodeString.equals("SkiSegment")) return rightNode;

        return null;

    }


}

