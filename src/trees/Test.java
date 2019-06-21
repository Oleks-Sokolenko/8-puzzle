package trees;

public class Test {
    public static void main(String[] args) {
        LinkedBinaryTree MExpression = new LinkedBinaryTree();

        //Adding expression 4 * 3 + 2
        Position root = MExpression.addRoot("+");
        Position product = MExpression.addLeft(MExpression.root,"*");
        MExpression.addLeft(product,"4");
        MExpression.addRight(product,"3");
        MExpression.addRight(root, "2");

        //Printing nodes in preorder
        System.out.println("Printing nodes in preorder:");
        TraversalExamples.printPreorder(MExpression);
        System.out.println("");

        //Parenthesized representation
        System.out.println("Printing parenthesized representation of the tree:");
        TraversalExamples.parenthesize(MExpression,root);


    }
}
