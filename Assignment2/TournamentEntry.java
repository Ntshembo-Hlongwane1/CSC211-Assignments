public class TournamentEntry {
  Node head;

  private class Team {
    String teamName;
    int teamNumber;
    double finalScore;
    long regYear;
    int firstScore;
    int secondScore;

    Team(String teamName, int teamNumber, long regYear, int firstScore, int secondYear) {
      this.teamName = teamName;
      this.teamNumber = teamNumber;
      this.regYear = regYear;
      this.firstScore = firstScore;
      this.secondScore = secondYear;
    }
  }

  private class Node {

    Team team;
    Node nextNode;

    Node(String teamName, int teamNumber, long regYear, int firstScore, int secondYear) {
      this.team = new Team(teamName, teamNumber, regYear, firstScore, secondYear);
      this.nextNode = null;
    }

  }

  /**
   * Part A --> LINKED LIST
   * 
   * @param teamName
   * @param teamNumber
   * @param regYear
   * @param firstScore
   * @param secondYear
   **/
  public void registerTeam(String teamName, int teamNumber, long regYear, int firstScore, int secondYear) {

    Node newTeam = createNode(teamName, teamNumber, regYear, firstScore, secondYear);
    if (head == null) {
      newTeam.nextNode = head;
      head = newTeam;
      return;
    }

    Node currentNode = head;

    while (currentNode.nextNode != null) {
      currentNode = currentNode.nextNode;
    }
    currentNode.nextNode = newTeam;
  }

  public void Display() {

    if (head == null) {
      return;
    }

    Node currentNode = head;

    while (currentNode != null) {
      System.out
          .print("{teamName: " + currentNode.team.teamName + ", teamNumber: " + currentNode.team.teamNumber + "} --> ");
      currentNode = currentNode.nextNode;
    }
    System.out.println("null");
  }

  public Node deregister_Last_team() {
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

  public int searchIndexByTeamNumber(int teamNumber) {
    int index = 0;
    boolean positionFound = false;

    if (head == null) {
      return -1;
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

  public void insertBefore(int teamNumber, Node newTeam) {
    int teamIndex = searchIndexByTeamNumber(teamNumber);

    if (teamIndex == 0 || head == null) {
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

    Node currentNode = head;

    while (currentNode.nextNode != null) {
      currentNode = currentNode.nextNode;
    }
    currentNode.nextNode = newTeam;
    return;
  }

  public Node createNode(String teamName, int teamNumber, long regYear, int firstScore, int secondYear) {
    Node newTeam = new Node(teamName, teamNumber, regYear, firstScore, secondYear);
    return newTeam;
  }

  public int tournamentEntrySize() {
    int tournamentSize = 0;
    Node currentNode = head;

    if (head == null) {
      return tournamentSize;
    }

    while (currentNode != null) {
      currentNode = currentNode.nextNode;
      tournamentSize++;
    }
    return tournamentSize;
  }
  // ================================================End of
  // PartA=====================================

  /**
   * Part B --> Sorting
   **/

  public void computeFinalScore() {
    Node currentNode = head;

    while (currentNode != null) {
      double finalScore = (currentNode.team.firstScore + currentNode.team.secondScore) / 2;
      currentNode.team.finalScore = finalScore;
      currentNode = currentNode.nextNode;
    }
  }

  public Node sortedMerge(Node left, Node right) {
    Node result = null;

    if (left == null) {
      return right;
    }
    if (right == null) {
      return left;
    }

    if (left.team.finalScore < right.team.finalScore) {
      result = left;
      result.nextNode = sortedMerge(left.nextNode, right);
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
      result = right;
      result.nextNode = sortedMerge(left, right.nextNode);
    }
    return result;
  }

  public Node getMiddle(Node head) {
    if (head == null) {
      return head;
    }

    Node slow = head;
    Node fast = head;

    while (fast.nextNode != null && fast.nextNode.nextNode != null) {
      slow = slow.nextNode;
      fast = fast.nextNode.nextNode;
    }
    return slow;
  }

  public Node mergeSort(Node head) {
    if (head == null || head.nextNode == null) {
      return head;
    }
    Node middle = getMiddle(head);
    Node nextofmiddle = middle.nextNode;
    middle.nextNode = null;
    Node left = mergeSort(head);
    Node right = mergeSort(nextofmiddle);
    Node sortedlist = sortedMerge(left, right);
    Node reversedList = reverse(sortedlist);
    return reversedList;
  }

  public Node reverse(Node LinkedList) {
    Node previous = null;
    Node current = LinkedList;
    Node next = null;

    while (current != null) {
      next = current.nextNode;
      current.nextNode = previous;
      previous = current;
      current = next;
    }
    return previous;
  }

  public static void main(String[] args) {
    TournamentEntry tourney = new TournamentEntry();
    tourney.registerTeam("DX", 123, 1876, 721, 1200);
    tourney.registerTeam("SBK", 222, 1976, 111, 1100);
    tourney.registerTeam("JKB", 122, 1996, 111, 1100);
    tourney.registerTeam("ACE", 122, 1996, 111, 1100);
    tourney.registerTeam("ACE", 100, 1996, 111, 1100);

    // tourney.Display();
    // Node removedTeam2 = tourney.deregister_Particular_team(222);
    // // System.out.println(removedTeam2);
    // System.out
    // .println("{teamName: " + removedTeam2.team.teamName + ", teamNumber: " +
    // removedTeam2.team.teamNumber + "}");
    // tourney.Display();
    // Node newTeam = tourney.createNode("Liquid", 321, 2000, 850, 1700);
    // tourney.insertBefore(500, newTeam);
    // tourney.Display();
    // System.out.println(tourney.tournamentEntrySize());
    tourney.computeFinalScore();
    Node result = tourney.mergeSort(tourney.head);

    while (result != null) {
      System.out.print("{teamName: " + result.team.teamName + ", teamNumber: " + result.team.teamNumber
          + ", finalScore: " + result.team.finalScore + "} --> ");
      result = result.nextNode;
    }
    System.out.println("null");
  }
}