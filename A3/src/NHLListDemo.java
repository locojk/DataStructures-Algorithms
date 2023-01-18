/***
 * Class to run NHL statistics
 * Has the main method.
 *
 * @author Jun Gao - B00899189
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class NHLListDemo {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);

        //Set source file
        System.out.print("Enter the filename to read from: ");
        String filename = in.nextLine();
        File file = new File(filename);
        Scanner inputFile = new Scanner(file);

        //Initialize List
        NHLStats playerList = new NHLStats();
        PlayerRecord player;

        //Read file and generate NHLStats List
        while (inputFile.hasNextLine())
        {
            StringTokenizer token = new StringTokenizer(inputFile.nextLine(), "\t");
            player = new PlayerRecord(token.nextToken(),token.nextToken(),token.nextToken(),Integer.parseInt(token.nextToken()),Integer.parseInt(token.nextToken()),Integer.parseInt(token.nextToken()),Integer.parseInt(token.nextToken()),Integer.parseInt(token.nextToken()),Integer.parseInt(token.nextToken()));
            playerList.add(player);
        }
        inputFile.close();

        //Out put result in file nhlstatsoutput.txt
        FileWriter out = new FileWriter("nhlstatsoutput.txt");
        out.write("Enter the filename to read from: " + filename + "\n");
        out.write("\nNHL Results Summary");
        out.write("\nPlayers with highest points and their teams:\n");
        out.write(playerList.mostPoints());
        out.write("\nMost aggressive players, their teams and their positions:\n");
        out.write(playerList.mostAggressive());
        out.write("\nMost valuable players and their teams:\n");
        out.write(playerList.findMVP());
        out.write("\nMost promising players and their teams:\n");
        out.write(playerList.greatestShots());
        out.write("\nTeams that had the most number of penalty minutes:\n");
        out.write(playerList.mostPenaltiesTeam());
        out.write("\nTeams that had the most number of game winning goals:\n");
        out.write(playerList.mostWinningTeam());
        out.close();
    }
}
