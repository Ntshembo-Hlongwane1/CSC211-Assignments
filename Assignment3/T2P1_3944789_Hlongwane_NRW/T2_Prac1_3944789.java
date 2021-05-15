import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Stack;

public class T2_Prac1_3944789 extends BinaryNode {

  BinaryNode root;

  /**
   * @return void
   * @description Method will call the _readFileData() to get the array data from
   *              the text file, with the received data set method iterates
   *              through each element while calling the recursive method
   *              _buildTree() to add the element to the binary tree
   **/
  public void buildBinaryTree() {
    int[] dataSet = _readFileData();
    for (int num : dataSet) {
      root = _buildTree(root, num);
    }
  }

  /**
   * @param BinaryNode root
   * @return int
   * @description Method will traverse the binary tree in in-order fashion, a
   *              stack is used to save node elements as we traverse through their
   *              left and right subtree. Each element from the stack will be
   *              popped and checked if odd so to increase odd number count
   **/
  public int oddEntries(BinaryNode root) {

    int oddCount = 0;
    if (root == null) {
      return oddCount;
    }

    Stack<BinaryNode> stack = new Stack<BinaryNode>();
    BinaryNode temp = root;

    while (!stack.isEmpty() || temp != null) {
      if (temp != null) {
        stack.push(temp);
        temp = temp.getLeft();
      } else {
        temp = stack.pop();

        if (temp.getElement() % 2 != 0) {
          oddCount++;
        }
        temp = temp.getRight();
      }
    }
    return oddCount;
  }

  // Helper Functions

  /***
   * @return int[]
   * @exception FileNotFoundException
   * @description Method will read file i.e input.txt dependent on _getDataSize()
   *              so to get size to give array that is to be returned
   **/
  private int[] _readFileData() {
    int arrSize = _getDataSize();
    int[] dataSet = new int[arrSize];

    File file = new File("input.txt");
    int idx = 0;
    try {
      Scanner scanner = new Scanner(file);

      while (scanner.hasNextLine()) {
        dataSet[idx] = Integer.parseInt(scanner.next());
        idx++;
      }
      scanner.close();

    } catch (FileNotFoundException error) {
      error.printStackTrace();
    }
    return dataSet;
  }

  /**
   * @return int
   * @exception FileNotFoundException
   * @description Method will run throw the number of lines to count the total and
   *              return it
   **/
  private int _getDataSize() {
    File file = new File("input.txt");
    int size = 0;
    try {
      Scanner scanner = new Scanner(file);

      while (scanner.hasNextLine()) {
        scanner.next();
        size++;
      }
      scanner.close();

    } catch (FileNotFoundException error) {
      error.printStackTrace();
    }
    return size;
  }

  /***
   * 
   * @param BinaryNode root
   * @param int        data
   * @return BinaryNode
   * @description Method will recursively append / add an element to the Binary
   *              Tree havaing Even elements to the left subtree and odd to the
   *              right subtree
   **/
  private BinaryNode _buildTree(BinaryNode root, int data) {
    if (root == null) {
      root = new BinaryNode();
      root.setElement(data);
      return root;
    }

    if (data % 2 == 0) {
      root.setLeft(_buildTree(root.getLeft(), data));
    } else {
      root.setRight(_buildTree(root.getRight(), data));
    }
    return root;
  }

  /**
   * @param BinaryNode root
   * @return void
   * @description Method will traverse the binary tree in an in-order fashion
   *              printing each element
   **/
  public void _inOrder(BinaryNode root) {

    if (root == null) {
      return;
    }

    _inOrder(root.getLeft());
    System.out.println(root.getElement());
    _inOrder(root.getRight());

  }

  /**
   * @param BinaryNode root
   * @return void
   * @description Method will traverse the binary tree in a post-order fashion
   *              printing each element
   **/
  public void _postOrder(BinaryNode root) {

    if (root == null) {
      return;
    }

    _postOrder(root.getLeft());
    _postOrder(root.getRight());
    System.out.println(root.getElement());

  }

  /**
   * @param BinaryNode root
   * @return void
   * @description Method will traverse the binary tree in a pre-order fashion
   *              printing each element
   **/
  public void _preOrder(BinaryNode root) {

    if (root == null) {
      return;
    }

    System.out.println(root.getElement());
    _inOrder(root.getLeft());
    _inOrder(root.getRight());

  }

  public static void main(String[] args) {
    T2_Prac1_3944789 tree = new T2_Prac1_3944789();

    tree.buildBinaryTree();

    tree._inOrder(tree.root);
    // System.out.println(tree.oddEntries(tree.root));
  }
}