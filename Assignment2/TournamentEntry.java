import java.util.Scanner;

public class TournamentEntry extends DynamicSorting {
  Node head;

  public void registerTeam() {

    Scanner scanner = new Scanner(System.in);

    System.out.println("How many elements do you want add? ");
    int numberOfElements = Integer.parseInt(scanner.next());
    int iteration = 1;

    while (iteration <= numberOfElements) {

      System.out.println("Enter the team name you want to register: ");
      String teamName = scanner.next();

      System.out.println("Enter the team number for the team: ");
      int teamNumber = Integer.parseInt(scanner.next());

      System.out.println("Enter the team year of registeration: ");
      long regYear = scanner.nextLong();

      System.out.println("Enter the team first score: ");
      int firstScore = Integer.parseInt(scanner.next());

      System.out.println("Enter the team second score: ");
      int secondScore = Integer.parseInt(scanner.next());

      Node newTeam = createNode(teamName, teamNumber, regYear, firstScore, secondScore);
      System.out.println("New team was created, adding to List Now....");

      insertionAtEnd(newTeam);
      System.out.println("New team added to list...");

      iteration++;

    }
    // scanner.close();
  }

  public void insertBefore(int teamNumber, Node newTeam) {
    int teamIndex = searchIndexByTeamNumber(teamNumber);

    if (teamIndex == 0) {
      newTeam.nextNode = head;
      head = newTeam;
      return;
    }

    if (teamIndex != -1) {
      int count = 0;
      Node previousNode = head;
      while (count < teamIndex - 1) {
        count++;
        previousNode = previousNode.nextNode;
      }
      Node currentNode = previousNode.nextNode;
      newTeam.nextNode = currentNode;
      previousNode.nextNode = newTeam;
      return;
    }
  }

  public Node deregister_last_team() {
    if (head == null) {
      return head;
    }

    Node currentNode = head;

    while (currentNode.nextNode.nextNode != null) {
      currentNode = currentNode.nextNode;
    }
    Node removedTeam = currentNode.nextNode;
    currentNode.nextNode = null;
    return removedTeam;
  }

  public Node deregister_Particular_team(int teamNumber) {
    int teamIndex = searchIndexByTeamNumber(teamNumber);
    if (teamIndex == 0) {
      Node currentNode = head;
      head = head.nextNode;
      return currentNode;
    }

    if (teamIndex == -1) {
      System.out.println("Team with this team number: " + "'" + teamNumber + "'" + " is not registered...");
      return head;
    }

    Node previousNode = head;
    int count = 0;

    while (count < teamIndex - 1) {
      previousNode = previousNode.nextNode;
      count++;
    }

    Node currentNode = previousNode.nextNode;
    previousNode.nextNode = currentNode.nextNode;
    currentNode.nextNode = null;
    return currentNode;

  }

  public int tournamentEntrySize() {
    int tournamentSize = 0;

    Node currentNode = head;
    while (currentNode != null) {
      tournamentSize++;
      currentNode = currentNode.nextNode;
    }
    return tournamentSize;
  }

  public void Display() {

    if (head == null) {
      return;
    }

    Node currentNode = head;
    System.out.println("\n");
    while (currentNode != null) {
      System.out.println(currentNode.team.teamName + " " + currentNode.team.teamNumber + " " + currentNode.team.regYear
          + " " + currentNode.team.firstScore + " " + currentNode.team.secondScore);
      currentNode = currentNode.nextNode;
    }

  }

  public void sort(Node head) {
    Node sortedlist = mergeSort(head);
    lazyDisplay(sortedlist);
  }

  // HELPER FUNCTIONS

  public int searchIndexByTeamNumber(int teamNumber) {
    int index = 0;
    boolean positionFound = false;

    if (head == null || head.nextNode == null) {
      return index;
    }

    Node currentNode = head;

    while (currentNode != null) {
      if (currentNode.team.teamNumber == teamNumber) {
        positionFound = true;
        break;
      }
      index++;
      currentNode = currentNode.nextNode;
    }

    return positionFound ? index : -1;
  }

  public Node createNode(String teamName, int teamNumber, long regYear, int firstScore, int secondScore) {
    Node newTeam = new Node(teamName, teamNumber, regYear, firstScore, secondScore);

    return newTeam;
  }

  private void insertionAtEnd(Node newTeam) {

    if (head == null) {
      newTeam.nextNode = head;
      head = newTeam;
      return;
    }

    Node curreNode = head;

    while (curreNode.nextNode != null) {
      curreNode = curreNode.nextNode;
    }
    curreNode.nextNode = newTeam;
  }

  private void computeFinalScore() {
    Node currentNode = head;

    while (currentNode != null) {
      double finalScore = (currentNode.team.firstScore + currentNode.team.secondScore) / 2;
      currentNode.team.finalScore = finalScore;
      currentNode = currentNode.nextNode;
    }
  }

  public void lazyRegister(String teamName, int teamNumber, long regYear, int firstScore, int secondScore) {

    Node newTeam = createNode(teamName, teamNumber, regYear, firstScore, secondScore);
    insertionAtEnd(newTeam);
  }

  private void insertionAtHead(Node team) {
    team.nextNode = head;
    head = team;
  }

  private Node mergeSort(Node head) {
    computeFinalScore();
    if (head == null || head.nextNode == null) {
      return head;
    }

    Node middle = getMiddle(head);
    Node nextofmiddle = middle.nextNode;

    middle.nextNode = null;

    Node left = mergeSort(head);

    Node right = mergeSort(nextofmiddle);

    Node sortedlist = sortedMerge(left, right);
    return sortedlist;
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

  private Node sortedMerge(Node left, Node right) {
    Node result = null;
    if (left == null)
      return right;
    if (right == null)
      return left;

    if (left.team.finalScore < right.team.finalScore) {
      result = right;
      result.nextNode = sortedMerge(left, right.nextNode);
    } else if (left.team.finalScore == right.team.finalScore) {
      if (left.team.teamName.compareTo(right.team.teamName) < 0) {
        result = left;
        result.nextNode = sortedMerge(left.nextNode, right);
      } else if (right.team.teamName.compareTo(right.team.teamName) < 0) {
        result = right;
        result.nextNode = sortedMerge(left, right.nextNode);
      } else {
        if (left.team.teamNumber < right.team.teamNumber) {
          result = left;
          result.nextNode = sortedMerge(left.nextNode, right);
        } else {
          result = right;
          result.nextNode = sortedMerge(left, right.nextNode);
        }
      }
    } else {
      result = left;
      result.nextNode = sortedMerge(left.nextNode, right);
    }
    return result;
  }

  public void lazyDisplay(Node head) {
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

  // MAIN FUNCTION
  public static void main(String[] args) {
    // TournamentEntry tourney = new TournamentEntry();

    // // Default teams for when lazy to type register team helper function
    // // lazyRegister
    // tourney.lazyRegister("Able", 8, 2001, 48, 55);
    // tourney.lazyRegister("dragon", 9, 2005, 80, 70);
    // tourney.lazyRegister("Chelsea", 6, 1978, 88, 95);
    // tourney.lazyRegister("Able", 5, 1999, 48, 55);
    // // tourney.registerTeam();
    // tourney.Display();

    // // tourney.dynamicSorting(tourney.head);

    // // Helper function createNode used when insertionBefore method is needed
    // // Node newTeam = tourney.createNode("KFC", 12, 2021, 66, 122);
    // // tourney.insertBefore(6, newTeam);
    // // tourney.deregister_Particular_team(6);
    // tourney.deregister_last_team();
    // // tourney.Display();
    // // tourney.sort(tourney.head);
    // tourney.Display();
  }
}