/***
 * Class that builds the Huffman tree by given a list of symbols-probability pairs
 * Can encode and decode text according to the Huffman coding algorithm
 *
 * @author Jun Gao - B00899189
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Huffman {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner in = new Scanner(System.in);

        System.out.println("Huffman Coding");

        //Get filename form user's input
        System.out.print("Enter the name of the file with letters and probability: ");
        String fileName = in.nextLine();

        System.out.println("\nBuilding the Huffman tree ....");

        //Set source file
        File file = new File(fileName);
        Scanner inputFile = new Scanner(file);

        //Create two PriorityQueue object to store BinaryTree
        Queue<BinaryTree<Pair>> s = new PriorityQueue<>(new BinaryTreeComparator());
        Queue<BinaryTree<Pair>> t = new PriorityQueue<>(new BinaryTreeComparator());

        //Read the source file and store data in to BinaryTree, then store each BinaryTree into Queue s
        while (inputFile.hasNextLine()) {
            StringTokenizer token = new StringTokenizer(inputFile.nextLine(), "\t");
            Pair pair = new Pair(token.nextToken().charAt(0),Double.parseDouble(token.nextToken()));
            BinaryTree<Pair> pairBinaryTree = new BinaryTree<>();
            pairBinaryTree.makeRoot(pair);
            s.add(pairBinaryTree);
        }

        inputFile.close();

        //BinaryTrees for temporary storage
        BinaryTree<Pair> a = new BinaryTree<>();
        BinaryTree<Pair> b = new BinaryTree<>();

        //Generate Binary tree by Huffman algorithm and store that into Queue t
        while (!s.isEmpty()) {
            if (t.isEmpty()) {
                a = s.poll();
                b = s.poll();
            }
            else {

                BinaryTreeComparator comparator = new BinaryTreeComparator();

                //Find the smaller weight tree of the trees in front of s and in front of t
                //Dequeue and store in a
                if (comparator.compare(s.peek(),t.peek()) > 0) {
                    a = t.poll();
                } else if (comparator.compare(s.peek(),t.peek()) < 0) {
                    a = s.poll();
                } else {
                    a = s.poll();
                }

                if (!s.isEmpty() && !t.isEmpty()) {
                    //Find the smaller weight tree of the trees in front of s and in front of t
                    //Dequeue and store in b
                    if (comparator.compare(s.peek(),t.peek()) > 0) {
                        b = t.poll();
                    } else if (comparator.compare(s.peek(),t.peek()) < 0) {
                        b = s.poll();
                    } else {
                        b = s.poll();
                    }
                } else if (!s.isEmpty()) {
                    b = s.poll();
                } else if (!t.isEmpty()) {
                    b = t.poll();
                }
            }

            BinaryTree<Pair> p = new BinaryTree<>();

            if (b != null) {
                //Construct a new tree P by creating a root and attaching A and B as the subtrees of this root
                Pair root = new Pair('a', (a.getData().getProb() + b.getData().getProb()));
                p.makeRoot(root);
                p.attachLeft(a);
                p.attachRight(b);

            } else {
                p.attachLeft(a);
            }
            t.add(p);
        }

        //If the number of elements in queue T is greater than 1,
        //dequeue two nodes at a time, combine them and enqueue the combined tree
        //until queue T's size is 1
        while (t.size() > 1) {
            a = t.poll();
            b = t.poll();

            Pair root = new Pair('a', (a.getData().getProb() + b.getData().getProb()));
            BinaryTree<Pair> p = new BinaryTree<>();
            p.makeRoot(root);
            p.attachLeft(a);
            p.attachRight(b);

            t.add(p);
        }

        System.out.println("Huffman coding completed.");

        //Input the letters need to be encoded
        System.out.print("\nEnter a line of text (uppercase letters only): ");
        String input = in.nextLine();

        System.out.print("Here’s the encoded line: ");

        StringTokenizer token = new StringTokenizer(input, " ");

        ArrayList<Character> inputChar = new ArrayList<>();

        //Read each String parts seperated by space
        while (token.hasMoreTokens()) {
            String st = token.nextToken();
            for (int i=0; i<st.length(); i++) {
                //Add each character to an ArrayList
                inputChar.add(st.charAt(i));
            }
            inputChar.add(' ');
        }

        //Get encoding
        String[] encode = findEncoding(t.peek());

        ArrayList<String> encodedLine = new ArrayList<>();

        for (char element: inputChar) {
            if (element != ' ') {
                //Encode character
                encodedLine.add(encode[element-65]);
            } else {
                encodedLine.add(" ");
            }
        }

        //Output encode line
        System.out.print("Here’s the encoded line: ");
        for (String element: encodedLine) {
            System.out.print(element);
        }
        System.out.println();

        //Output decode line
        System.out.print("The decoded line is: ");
        for (String element: encodedLine) {
            if (element.equals(" ")){
                System.out.print(" ");
            } else {
                //Decode encode line
                for (int i=0; i<encode.length; i++) {
                    if (element.equals(encode[i])) {
                        System.out.print((char)(i+65));
                    }
                }
            }
        }
        System.out.println();
    }

    //Find encode for a BinaryTree<Pair>
    private static String[] findEncoding(BinaryTree<Pair> bt){

        String[] result = new String[26];

        findEncoding(bt, result, "");
        return result;
    }

    //Helper method to find encode for a BinaryTree<Pair>
    private static void findEncoding(BinaryTree<Pair> bt, String[] a, String prefix){
        // test is node/tree is a leaf
        if (bt.getLeft()==null && bt.getRight()==null){
            a[bt.getData().getValue() - 65] = prefix;
        }
        // recursive calls
        else{
        findEncoding(bt.getLeft(), a, prefix+"0");
        findEncoding(bt.getRight(), a, prefix+"1");
        }
    }
}