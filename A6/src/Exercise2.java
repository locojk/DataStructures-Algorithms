/***
 * Implement Topological Sorting algorithm.
 * Read description of graph from file "Exercise2Input.txt".
 * Can display Topological Sorting result.
 *
 * @author Jun Gao - B00899189
 */

import java.io.File;
import java.io.FileNotFoundException;
//Use ArrayList, Scanner, Queue, LinkedList and HashMap
import java.util.*;

public class Exercise2 {
    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("Exercise2Input.txt");

        Scanner inputFile = new Scanner(file);

        //Read number of vertices
        int n = inputFile.nextInt();

        //Create adjacency matrix representing the graph
        int[][] adj = new int[n][n];
        while (inputFile.hasNextLine()) {
            int v0 = inputFile.next().charAt(0)-65;
            int v1 = inputFile.next().charAt(0)-65;
            adj[v0][v1] = 1;
        }

        inputFile.close();

        //Initialize an empty queue
        Queue<Character> q = new LinkedList<Character>();

        //Compute the predecessor count for each vertex in the graph
        int[] pred = new int[n];
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (adj[i][j] == 1) {
                    pred[j]++;
                }
            }
        }

        //For each vertex, if pred(v)==0, add v to the queue.
        for (int i=0; i<n; i++) {
            if (pred[i] == 0) {
                q.add((char)(i + 65));
            }
        }

        int topNum = 1;

        //HashMap to assign topNum with each vertex
        HashMap<Integer,Character> topChar = new HashMap<Integer,Character>();

        while (!(q.isEmpty())) {
            //Dequeue vertex
            char w = q.poll();

            //Assign vertex with topNum
            topChar.put(topNum,w);

            topNum++;

            //Check each neighbour of vertex w
            for (int i=0; i<n; i++) {
                if (adj[i][w-65] == 1) {
                    pred[i]--;
                    if (pred[i] == 0) {
                        q.add((char)(i + 65));
                    }
                    adj[i][w-65] = 0;
                }
            }

            //Continuous check neighbour of vertex w
            for (int j=0; j<n; j++) {
                if (adj[w-65][j] == 1) {
                    pred[j]--;
                    if (pred[j] == 0) {
                        q.add((char)(j + 65));
                    }
                    adj[w-65][j] = 0;
                }
            }
        }

        //Display result
        for (int i=1; i<=n; i++) {
            System.out.printf("%d ",i);
        }

        System.out.println();

        for (int i=1; i<=n; i++) {
            System.out.print(topChar.get(i) + " ");
        }
    }
}
