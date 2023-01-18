/***
 * The class represent a player record
 *
 * @author Jun Gao - B00899189
 */

public class PlayerRecord {

    //Initialize fields
    private String playerName;
    private String position;
    private String teamName;
    private int gamesPlayed;
    private int goalsScored;
    private int assists;
    private int penaltiesMinutes;
    private int shotsGoal;
    private int winningGoals;

    //Constructor
    public PlayerRecord(String playerName, String position, String teamName, int gamesPlayed, int goalsScored, int assists, int penaltiesMinutes, int shotsGoal, int winningGoals) {
        this.playerName = playerName;
        this.position = position;
        this.teamName = teamName;
        this.gamesPlayed = gamesPlayed;
        this.goalsScored = goalsScored;
        this.assists = assists;
        this.penaltiesMinutes = penaltiesMinutes;
        this.shotsGoal = shotsGoal;
        this.winningGoals = winningGoals;
    }

    //Getters and setters
    public int getAssists() {
        return assists;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public int getPenaltiesMinutes() {
        return penaltiesMinutes;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getShotsGoal() {
        return shotsGoal;
    }

    public int getWinningGoals() {
        return winningGoals;
    }

    public String getPosition() {
        return position;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public void setPenaltiesMinutes(int penaltiesMinutes) {
        this.penaltiesMinutes = penaltiesMinutes;
    }

    public void setShotsGoal(int shotsGoal) {
        this.shotsGoal = shotsGoal;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setWinningGoals(int winningGoals) {
        this.winningGoals = winningGoals;
    }
}