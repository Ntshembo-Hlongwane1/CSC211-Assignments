import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Stack;

public class T2_Prac2_3944789 {
  // first link
  BinarySearchTree bst = new BinarySearchTree();

  // Helper variable to get the target node when splitting etc
  int target = 0;

  public void insert() {
    ArrayList<Integer> dataSet = _readFileData();

    for (int num : dataSet) {
      bst.insert(num);
    }
  }

  public void display() {
    bst.root.printPreOrder();
  }

  public void start() {
    Scanner scanner = new Scanner(System.in);
    int choice = 1;
    do {
      System.out.println("\nWELCOME TO CSC211 PRAC2....");
      System.out.println("Choice 1: Build Binary Search Tree with default value in input.txt file.");
      System.out.println("Choice 2: Display Binary Search Tree.");
      System.out.println("Choice 3: Split Binary Search Tree.");
      System.out.println("Choice 4: Reset Binary Search Tree.");
      System.out.println("Choice 0: Stop program.");
      choice = scanner.nextInt();

      if (choice == 1) {
        System.out.println("\nBuilding Tree....");
        this.insert();
        System.out.println("Done building Binary Search Tree...");
      }

      if (choice == 2) {
        if (bst.root == null) {
          System.out.println("Binary Search Tree is empty... Build one to display");
          return;
        }
        this.display();
      }

      if (choice == 3) {
        if (bst.root == null) {
          System.out.println("Binary Search Tree is empty... Build one to split");
          return;
        }
        System.out.println("\n Where would you like to split the tree from? ");
        int targetKey = scanner.nextInt();
        this.splitTree(targetKey, bst.root);
      }

      if (choice == 4) {
        bst.root = null;
        System.out.println("Done resetting Tree...");
      }

    } while (choice != 0);
  }

  public void splitTree(int targetKey, BinaryNode root) {
    this.target = targetKey;
    BinaryNode secondTree = searchTargetNode(root, targetKey);

    this.afterSplitAction(secondTree);

  }

  // Helper Functions
  protected ArrayList<Integer> _readFileData() {
    ArrayList<Integer> dataSet = new ArrayList<Integer>();
    try {
      File file = new File("Input2.txt");
      Scanner scanner = new Scanner(file);

      while (scanner.hasNextLine()) {
        dataSet.add(scanner.nextInt());
      }

      scanner.close();
    } catch (FileNotFoundException error) {
      error.printStackTrace();
    }

    return dataSet;
  }

  protected BinaryNode searchTargetNode(BinaryNode root, int targetKey) {
    if (root.getElement() == targetKey) { // base case
      return root;
    }

    if (targetKey < root.getElement()) {
      return searchTargetNode(root.getLeft(), targetKey);
    } else {
      return searchTargetNode(root.getRight(), targetKey);
    }
  }

  protected void afterSplitAction(BinaryNode secondTree) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("\nPress 1 to display second tree: ");
    System.out.println("Press 2 to display original tree: ");

    int choice = scanner.nextInt();

    if (choice == 1) {
      System.out.println("Splited tree from target node: " + this.target);
      printPreOrderSecondTree(secondTree);
    } else {
      printPreOrderFirstTree(bst.root);
    }
  }

  protected void printPreOrderSecondTree(BinaryNode root) {

    if (root == null) {
      return;
    }

    System.out.print(root.getElement() + " ");
    printPreOrderSecondTree(root.getLeft());
    printPreOrderSecondTree(root.getRight());

  }

  protected void printPreOrderFirstTree(BinaryNode root) {
    System.out.println("Original Tree without splitted tree: ");
    if (root == null) {
      return;
    }

    Stack<BinaryNode> stack = new Stack<BinaryNode>();
    stack.push(root);

    while (!stack.isEmpty()) {
      BinaryNode temp = stack.pop();
      if (temp.getElement() == this.target) {
        return;
      }
      System.out.print(temp.getElement() + " ");
      if (temp.getRight() != null) {
        stack.push(temp.getRight());
      }
      if (temp.getLeft() != null) {
        stack.push(temp.getLeft());
      }
    }
    System.out.println("\n");
  }

  public static void main(String[] args) {
    T2_Prac2_3944789 prac2 = new T2_Prac2_3944789();
    prac2.start();
  }
}
