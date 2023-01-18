/***
 * The class that deal with NHL statistics contain several player records
 *
 * @author Jun Gao - B00899189
 */

import java.util.ArrayList;
import java.util.Collections;

public class NHLStats {

    //Initialize List
    private List<PlayerRecord> playerList;

    //Constructor
    public NHLStats() {
        playerList = new List<PlayerRecord>();
    }

    /**
     * Add a player record to the front of the list
     * @param player a PlayerRecord object
     */
    public void add(PlayerRecord player) {
        playerList.add(player);
    }

    /**
     * Display the name and team name for the player with the most points (Goals+Assists)
     * @return String include the name and team name for the player with the most points
     */
    public String mostPoints() {

        int points, maxPoints = 0;

        //Search List to find the max value of Goals + Assists
        PlayerRecord player = playerList.first();
        while (player!=null) {
            points = player.getGoalsScored() + player.getAssists();
            if (points > maxPoints) {
                maxPoints = points;
            }
            player = playerList.next();
        }

        //Find all players who have that max points, then put their name and team name in a String
        player = playerList.first();
        String result = "";
        while (player != null) {
            if (player.getAssists()+ player.getGoalsScored() == maxPoints) {
                result += (player.getPlayerName() + " " + player.getTeamName() + "\n");
            }
            player = playerList.next();
        }

        return result;
    }

    /**
     * Display the name, team name, and position for the player who was the most aggressive (had the most penalty minutes).
     * @return String include name, team name, and position for the player who was the most aggressive
     */
    public String mostAggressive() {
        int penalties, maxPenalties = 0;

        //Search List to find the max value of Goals + Assists
        PlayerRecord player = playerList.first();
        while (player!=null) {
            penalties = player.getPenaltiesMinutes();
            if (penalties > maxPenalties) {
                maxPenalties = penalties;
            }
            player = playerList.next();
        }

        //Find all players who have that max points, then put their name and team name in a String
        player = playerList.first();
        String result = "";
        while (player != null) {
            if (player.getPenaltiesMinutes() == maxPenalties) {
                result += (player.getPlayerName() + " " + player.getTeamName() + " " + player.getPosition() + "\n");
            }
            player = playerList.next();
        }
        return result;
    }

    /**
     * Display the name and team name for the player who was the MVP (scored the most game winning goals).
     * @return String include name and team name for the player who was the MVP
     */
    public String findMVP() {
        int winning, maxWinning = 0;

        //Search List to find the max value of Goals + Assists
        PlayerRecord player = playerList.first();
        while (player!=null) {
            winning = player.getWinningGoals();
            if (winning > maxWinning)
                maxWinning = winning;
            player = playerList.next();
        }

        //Find all players who have that max points, then put their name and team name in a String
        player = playerList.first();
        String result = "";
        while (player != null) {
            if (player.getWinningGoals() == maxWinning) {
                result += (player.getPlayerName() + " " + player.getTeamName() + "\n");
            }
            player = playerList.next();
        }
        return result;
    }

    /**
     * Display the name and team name for the most promising player (had the most shots on goal).
     * @return String include name and team name for the most promising player
     */
    public String greatestShots() {
        int shots, maxShots = 0;

        //Search List to find the max value of Goals + Assists
        PlayerRecord player = playerList.first();
        while (player!=null) {
            shots = player.getShotsGoal();
            if (shots > maxShots)
                maxShots = shots;
            player = playerList.next();
        }

        //Find all players who have that max points, then put their name and team name in a String
        player = playerList.first();
        String result = "";
        while (player != null) {
            if (player.getShotsGoal() == maxShots) {
                result += (player.getPlayerName() + " " + player.getTeamName() + "\n");
            }
            player = playerList.next();
        }
        return result;
    }

    /**
     * Display the team name for the team that had the most penalty minutes (sum of all penalty minutes for all players on a team).
     * @return String include team name for the team that had the most penalty minutes
     */
    public String mostPenaltiesTeam() {
        //Initialize ArrayLists to store sum of all penalty minutes for all players on a team and their team name
        ArrayList<Integer> penaltiesSums = new ArrayList<Integer>();
        ArrayList<String> teamNames = new ArrayList<String>();

        //Calculate the total sum of penalty minutes of each team in the list
        //After calculating the total penalty minutes of a team, delete it in the copy of the original list.
        //Then calculating the next team, util the list is empty (all teams are calculated)
        List<PlayerRecord> listCopy = copyList(playerList);
        while (!listCopy.isEmpty()) {
            int penaltiesSum = 0;
            PlayerRecord player = listCopy.first();
            String teamName = player.getTeamName();
            teamNames.add(teamName);

            while (player != null) {
                if (player.getTeamName().equals(teamName)) {
                    penaltiesSum += player.getPenaltiesMinutes();
                }
                player = listCopy.next();
            }

            penaltiesSums.add(penaltiesSum);

            listCopy.removeAll(listCopy.first());
        }

        //Call method to return String include team name for the team that had the most penalty minutes
        return teamOutPut(penaltiesSums, teamNames);
    }

    /**
     * Display the team name for the team that had the most game winning goals (sum of all game winning goals for all players on a team).
     * @return String include team name for the team that had the most game winning goals
     */
    public String mostWinningTeam() {
        //Initialize ArrayLists to store sum of all penalty minutes for all players on a team and their team name
        ArrayList<Integer> winningSums = new ArrayList<Integer>();
        ArrayList<String> teamNames = new ArrayList<String>();

        //Calculate the total sum of penalty minutes of each team in the list
        //After calculating the total penalty minutes of a team, delete it in the copy of the original list.
        //Then calculating the next team, util the list is empty (all teams are calculated)
        List<PlayerRecord> listCopy = copyList(playerList);
        while (!listCopy.isEmpty()) {
            int winningSum = 0;
            PlayerRecord player = listCopy.first();
            String teamName = player.getTeamName();
            teamNames.add(teamName);

            while (player != null) {
                if (player.getTeamName().equals(teamName)) {
                    winningSum += player.getWinningGoals();
                }
                player = listCopy.next();
            }

            winningSums.add(winningSum);

            listCopy.removeAll(listCopy.first());
        }

        //Call method to return String include team name for the team that had the most penalty minutes
        return teamOutPut(winningSums, teamNames);
    }

    /**
     * Get all index of an element occur in an ArrayList
     * @param max the element needs to be found
     * @param list the ArrayList needs to be searched
     * @return an ArrayList contains all index match max
     */
    private static ArrayList<Integer> indexOfAll(int max, ArrayList<Integer> list) {
        ArrayList<Integer> indexList = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++) {
            if (max == list.get(i)) {
                indexList.add(i);
            }
        }
        return indexList;
    }

    /**
     * Shallow copy a List
     * @param list the List needs to be copied
     * @return a new List that copy old one
     */
    private static List<PlayerRecord> copyList(List<PlayerRecord> list) {
        List<PlayerRecord> newList = new List<PlayerRecord>();

        PlayerRecord player = list.first();
        while (player != null) {
            newList.add(player);
            player = list.next();
        }

        return newList;
    }

    /**
     * Output team names match the max penalties sum.
     * @param penaltiesSums an ArrayList contain all penalties sums for each team
     * @param teamNames an ArrayList contain all team names.
     * @return return all team name required
     */
    private String teamOutPut(ArrayList<Integer> penaltiesSums, ArrayList<String> teamNames) {
        int maxPenaltiesSum = Collections.max(penaltiesSums);

        ArrayList<Integer> maxIndex = indexOfAll(maxPenaltiesSum, penaltiesSums);

        String result = "";

        for (Integer index : maxIndex) {
            result += (teamNames.get(index) + "\n");
        }
        return result;
    }

}
