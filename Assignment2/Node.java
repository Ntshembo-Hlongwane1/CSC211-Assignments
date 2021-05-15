public class Node {

  Team team;
  Node nextNode;

  Node(String teamName, int teamNumber, long regYear, int firstScore, int secondScore) {
    this.team = new Team(teamName, teamNumber, regYear, firstScore, secondScore);
    this.nextNode = null;
  }

}
