
// BinaryNode class; stores a node in a tree.
//
// CONSTRUCTION: with (a) no parameters, or (b) an Object,
//     or (c) an Object, left child, and right child.
//
// *******************PUBLIC OPERATIONS**********************
// int size( )            --> Return size of subtree at node
// int height( )          --> Return height of subtree at node
// void printPostOrder( ) --> Print a postorder tree traversal
// void printInOrder( )   --> Print an inorder tree traversal
// void printPreOrder( )  --> Print a preorder tree traversal
// BinaryNode duplicate( )--> Return a duplicate tree

/**
 * Binary node class with recursive routines to compute size and height.
 */
class BinaryNode {
  public BinaryNode() {
    this(0, null, null);
  }

  public BinaryNode(int theElement, BinaryNode lt, BinaryNode rt) {
    element = theElement;
    left = lt;
    right = rt;
  }

  /**
   * Return the size of the binary tree rooted at t.
   */
  public static int size(BinaryNode t) {
    if (t == null)
      return 0;
    else
      return 1 + size(t.left) + size(t.right);
  }

  /**
   * Return the height from a node to the root node of the binary tree.
   */
  public static int height(BinaryNode root) {
    int lh = 0;
    int rh = 0;
    int dummy = 0;

    if (root != null) {
      // leaf node’s height is zero
      if ((root.left == null) && (root.right == null))
        return 0;
      else {
        lh = height(root.left);
        rh = height(root.right);
        if (lh > rh) {
          dummy = 1 + lh;
        } else {
          dummy = 1 + rh;
        }
        return dummy;
      }
    }
    return -1;
  }

  // Print tree rooted at current node using preorder traversal.
  public void printPreOrder() {
    System.out.println(element); // Node
    if (left != null)
      left.printPreOrder(); // Left
    if (right != null)
      right.printPreOrder(); // Right
  }

  // Print tree rooted at current node using postorder traversal.
  public void printPostOrder() {
    if (left != null)
      left.printPostOrder(); // Left
    if (right != null)
      right.printPostOrder(); // Right
    System.out.println(element); // Node
  }

  // Print tree rooted at current node using inorder traversal.
  public void printInOrder() {
    if (left != null)
      left.printInOrder(); // Left
    System.out.println(element); // Node
    if (right != null)
      right.printInOrder(); // Right
  }

  /**
   * Return a reference to a node that is the root of a duplicate of the binary
   * tree rooted at the current node.
   */
  public BinaryNode duplicate() {
    BinaryNode root = new BinaryNode(element, null, null);

    if (left != null) // If there's a left subtree
      root.left = left.duplicate(); // Duplicate; attach
    if (right != null) // If there's a right subtree
      root.right = right.duplicate(); // Duplicate; attach
    return root; // Return resulting tree
  }

  public int getElement() {
    return element;
  }

  public BinaryNode getLeft() {
    return left;
  }

  public BinaryNode getRight() {
    return right;
  }

  public void setElement(int x) {
    element = x;
  }

  public void setLeft(BinaryNode t) {
    left = t;
  }

  public void setRight(BinaryNode t) {
    right = t;
  }

  private int element;
  private BinaryNode left;
  private BinaryNode right;
}