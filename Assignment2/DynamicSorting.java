import java.util.Scanner;

/**
 * Dyanamic sorting
 **/
public class DynamicSorting {

  public void dynamicSorting(Node head) {
    Scanner scanner = new Scanner(System.in);

    if (head == null) {
      return;
    }

    System.out.println("Enter what you want to sort by, choice number below: ");
    System.out.println("1. Sort by finalScore");
    System.out.println("2. Sort by teamName");
    System.out.println("3. Sort by teamNumber");
    System.out.println("4. Sort by regYear");
    System.out.println("5. Sort by firstScore");
    System.out.println("6. Sort by secondScore");
    System.out.println("\n");

    int choice = Integer.parseInt(scanner.next());
    Node sortedList = null;

    try {

      if (choice == 1) {
        computeFinalScore(head);
        sortedList = sortByFinalScore(head);
        lazyDisplay(sortedList);
      }

      if (choice == 2) {
        sortedList = sortByTeamName(head);
        lazyDisplay(sortedList);

      }

      if (choice == 3) {
        sortedList = sortByTeamNumber(head);
        lazyDisplay(sortedList);
      }

      if (choice == 4) {
        sortedList = sortByRegYear(head);
        lazyDisplay(sortedList);
      }

      if (choice == 5) {
        sortedList = sortByFirstScore(head);
        lazyDisplay(sortedList);
      }

      if (choice == 6) {
        sortedList = sortBySecondScore(head);
        lazyDisplay(sortedList);
      }

    } catch (Exception e) {
      System.out.println("Enter a valid number...");
      e.printStackTrace();
    }
  }

  private Node sortByFinalScore(Node head) {
    if (head == null || head.nextNode == null) {
      return head;
    }

    Node middle = getMiddle(head);
    Node nextofmiddle = middle.nextNode;

    middle.nextNode = null;

    Node left = sortByFinalScore(head);

    Node right = sortByFinalScore(nextofmiddle);

    Node sortedlist = sortByFinalScoreHelper(left, right);
    return sortedlist;
  }

  private Node sortByFinalScoreHelper(Node left, Node right) {
    Node result = null;

    if (left == null)
      return right;
    if (right == null)
      return left;

    if (left.team.finalScore <= right.team.finalScore) {
      result = left;
      result.nextNode = sortByFinalScoreHelper(left.nextNode, right);
    } else {
      result = right;
      result.nextNode = sortByFinalScoreHelper(left, right.nextNode);
    }
    return result;
  }

  private Node sortByTeamName(Node head) {
    if (head == null || head.nextNode == null) {
      return head;
    }

    Node middle = getMiddle(head);
    Node nextofmiddle = middle.nextNode;

    middle.nextNode = null;

    Node left = sortByTeamName(head);

    Node right = sortByTeamName(nextofmiddle);

    Node sortedlist = sortByTeamNameHelper(left, right);
    return sortedlist;
  }

  private Node sortByTeamNameHelper(Node left, Node right) {
    Node result = null;

    if (left == null)
      return right;
    if (right == null)
      return left;

    if (left.team.teamName.compareTo(right.team.teamName) < 0) {
      result = left;
      result.nextNode = sortByTeamNameHelper(left.nextNode, right);
    } else {
      result = right;
      result.nextNode = sortByTeamNameHelper(left, right.nextNode);
    }
    return result;
  }

  private Node sortByTeamNumber(Node head) {
    if (head == null || head.nextNode == null) {
      return head;
    }

    Node middle = getMiddle(head);
    Node nextofmiddle = middle.nextNode;

    middle.nextNode = null;

    Node left = sortByTeamNumber(head);

    Node right = sortByTeamNumber(nextofmiddle);

    Node sortedlist = sortByTeamNumberHelper(left, right);
    return sortedlist;
  }

  private Node sortByTeamNumberHelper(Node left, Node right) {
    Node result = null;

    if (left == null)
      return right;
    if (right == null)
      return left;

    if (left.team.teamNumber < right.team.teamNumber) {
      result = left;
      result.nextNode = sortByTeamNumberHelper(left.nextNode, right);
    } else {
      result = right;
      result.nextNode = sortByTeamNumberHelper(left, right.nextNode);
    }
    return result;
  }

  private Node sortByRegYear(Node head) {
    if (head == null || head.nextNode == null) {
      return head;
    }

    Node middle = getMiddle(head);
    Node nextofmiddle = middle.nextNode;

    middle.nextNode = null;

    Node left = sortByRegYear(head);

    Node right = sortByRegYear(nextofmiddle);

    Node sortedlist = sortByRegYearHelper(left, right);
    return sortedlist;
  }

  private Node sortByRegYearHelper(Node left, Node right) {
    Node result = null;

    if (left == null)
      return right;
    if (right == null)
      return left;

    if (left.team.regYear < right.team.regYear) {
      result = left;
      result.nextNode = sortByRegYearHelper(left.nextNode, right);
    } else {
      result = right;
      result.nextNode = sortByRegYearHelper(left, right.nextNode);
    }
    return result;
  }

  private Node sortByFirstScore(Node head) {
    if (head == null || head.nextNode == null) {
      return head;
    }

    Node middle = getMiddle(head);
    Node nextofmiddle = middle.nextNode;

    middle.nextNode = null;

    Node left = sortByFirstScore(head);

    Node right = sortByFirstScore(nextofmiddle);

    Node sortedlist = sortByFirstScoreHelper(left, right);
    return sortedlist;
  }

  private Node sortByFirstScoreHelper(Node left, Node right) {
    Node result = null;

    if (left == null)
      return right;
    if (right == null)
      return left;

    if (left.team.firstScore < right.team.firstScore) {
      result = left;
      result.nextNode = sortByFirstScoreHelper(left.nextNode, right);
    } else {
      result = right;
      result.nextNode = sortByFirstScoreHelper(left, right.nextNode);
    }
    return result;
  }

  private Node sortBySecondScore(Node head) {
    if (head == null || head.nextNode == null) {
      return head;
    }

    Node middle = getMiddle(head);
    Node nextofmiddle = middle.nextNode;

    middle.nextNode = null;

    Node left = sortBySecondScore(head);

    Node right = sortBySecondScore(nextofmiddle);

    Node sortedlist = sortBySecondScoreHelper(left, right);
    return sortedlist;
  }

  private Node sortBySecondScoreHelper(Node left, Node right) {
    Node result = null;

    if (left == null)
      return right;
    if (right == null)
      return left;

    if (left.team.secondScore < right.team.secondScore) {
      result = left;
      result.nextNode = sortBySecondScoreHelper(left.nextNode, right);
    } else {
      result = right;
      result.nextNode = sortBySecondScoreHelper(left, right.nextNode);
    }
    return result;
  }

  private Node getMiddle(Node head) {
    if (head == null)
      return head;

    Node slow = head, fast = head;

    while (fast.nextNode != null && fast.nextNode.nextNode != null) {
      slow = slow.nextNode;
      fast = fast.nextNode.nextNode;
    }
    return slow;
  };

  private void lazyDisplay(Node head) {
    if (head == null) {
      return;
    }

    Node currentNode = head;
    System.out.println("\n");
    while (currentNode != null) {
      System.out.println(currentNode.team.teamName + " " + currentNode.team.teamNumber + " " + currentNode.team.regYear
          + " " + currentNode.team.firstScore + " " + currentNode.team.secondScore + " " + currentNode.team.finalScore);
      currentNode = currentNode.nextNode;
    }
  }

  private void computeFinalScore(Node head) {
    Node currentNode = head;

    while (currentNode != null) {
      double finalScore = (currentNode.team.firstScore + currentNode.team.secondScore) / 2;
      currentNode.team.finalScore = finalScore;
      currentNode = currentNode.nextNode;
    }
  }
}
