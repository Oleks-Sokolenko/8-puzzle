
package trees;

import java.util.ArrayList;
import trees.Position;
import trees.Tree;
import trees.BinaryTree;
import trees.AbstractTree;
import trees.LinkedBinaryTree;

public class TraversalExamples {

  public static <E> void printPreorder(AbstractTree<E> T) {
    for (Position<E> p : T.preorder())
      System.out.println(p.getElement());
  }

  /** Prints preorder representation of subtree of T rooted at p having depth d. */
  public static <E> void printPreorderIndent(Tree<E> T, Position<E> p, int d) {
    System.out.println(spaces(2*d) + p.getElement());   // indent based on d
    for (Position<E> c : T.children(p))
      printPreorderIndent(T, c, d+1);                   // child depth is d+1
  }


  /** Prints parenthesized representation of subtree of T rooted at p. */
  public static <E> void parenthesize(Tree<E> T, Position<E> p) {
    System.out.print(p.getElement());
    if (T.isInternal(p)) {
      boolean firstTime = true;
      for (Position<E> c : T.children(p)) {
        System.out.print( (firstTime ? " (" : ", ") ); // determine proper punctuation
        firstTime = false;                             // any future passes will get comma
        parenthesize(T, c);                            // recur on child
      }
      System.out.print(")");
    }
  }

    /** Returns a string containing n spaces. */
    public static String spaces(int n) {
        StringBuilder sb = new StringBuilder();
        for (int j=0; j < n; j++)
            sb.append(' ');
        return sb.toString();
    }

}
