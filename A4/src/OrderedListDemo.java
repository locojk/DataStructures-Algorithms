/***
 * Class to implement the two-finger walking algorithm.
 * Has small extensions on the algorithm to perform various merging operations on ordered lists.
 *
 * @author Jun Gao - B00899189
 */

//Classes used to read and write file, from Java built-in package
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
//Classes used to read input from user, from Java built-in package
import java.util.Scanner;

public class OrderedListDemo {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);

        //Input first file name
        System.out.print("Enter the first filename to read from: ");
        String fileName1 = in.nextLine();

        //Input second file name
        System.out.print("Enter the second filename to read from: ");
        String fileName2 = in.nextLine();

        //Create two ordered lists consisting of names
        OrderedList<String> list1 = new OrderedList<String>();
        OrderedList<String> list2 = new OrderedList<String>();
        //Insert data into OrderedLists by call createOrderedList method
        createOrderedList(fileName1,list1);
        createOrderedList(fileName2,list2);

        //Merge ordered lists in different ways by call different methods
        OrderedList<String> list3 = merge(list1,list2);
        OrderedList<String> list4 = difference(list1,list2);
        OrderedList<String> list5 = common(list1,list2);

        //Write ordered list to three text files by call writeIntoFile method
        writeIntoFile("merged.txt",list3);
        writeIntoFile("difference.txt",list4);
        writeIntoFile("common.txt",list5);

        //Output final status
        System.out.println("The merge operations are complete and the results are in merged.txt, difference.txt and common.txt");
    }

    /**
     * Merge two ordered lists, no other modification
     * @param list1 one ordered list needed to be merged
     * @param list2 another ordered list needed to be merged
     * @return newList a third list that is a merger of the two ordered lists
     */
    public static <T extends Comparable<T>> OrderedList<T> merge(OrderedList<T> list1, OrderedList<T> list2) {
        OrderedList<T> newList = new OrderedList<>();

        //Mark the index of two lists
        int f1 = 0;
        int f2 = 0;

        //Search two OrderedList list1 and list2, insert and sort the items of them
        while (f1 < list1.size() && f2 < list2.size()) {
            if (list1.get(f1).compareTo(list2.get(f2)) < 0) {
                newList.add(list1.get(f1));
                f1++;
            } else if (list1.get(f1).compareTo(list2.get(f2)) > 0) {
                newList.add(list2.get(f2));
                f2++;
            } else {
                newList.add(list1.get(f1));
                f1++;
                f2++;
            }
        }

        //Appending remain items of list2 to newList if list2 is longer
        if (f1 == list1.size()) {
            while (f2 < list2.size()) {
                newList.add(list2.get(f2));
                f2++;
            }
        }

        //Appending remain items of list1 to newList if list1 is longer
        if (f2 == list1.size()) {
            while (f1 < list1.size()) {
                newList.add(list1.get(f1));
                f1++;
            }
        }

        return newList;
    }

    /**
     * Merge two ordered lists but only contain the items in list1 that are not in list2
     * @param list1 one ordered list needed to be merged
     * @param list2 another ordered list needed to be merged
     * @return newList a third list that is a merger of the two ordered lists, only contain the items in list1 that are not in list2
     */
    public static <T extends Comparable<T>> OrderedList<T> difference(OrderedList<T> list1, OrderedList<T> list2) {
        OrderedList<T> newList = new OrderedList<>();

        //Mark the index of two lists
        int f1 = 0;
        int f2 = 0;

        //Search two OrderedList list1 and list2, insert and sort the items of them
        while (f1 < list1.size()) {
            if (list1.get(f1).compareTo(list2.get(f2)) < 0) {
                newList.add(list1.get(f1));
                f1++;
            } else if (list1.get(f1).compareTo(list2.get(f2)) > 0) {
                f2++;
            } else {
                f1++;
                f2++;
            }
        }

        //Appending remain items of list1 to newList if list1 is longer
        if (f2 == list1.size()) {
            while (f1 < list1.size()) {
                newList.add(list1.get(f1));
                f1++;
            }
        }

        return newList;
    }

    /**
     * Merge two ordered lists but only contain the items that are common in list1 and list2
     * @param list1 one ordered list needed to be merged
     * @param list2 list2 another ordered list needed to be merged
     * @return newList a third list that is a merger of the two ordered lists,  only contain the items that are common in list1 and list2
     */
    public static <T extends Comparable<T>> OrderedList<T> common(OrderedList<T> list1, OrderedList<T> list2) {
        OrderedList<T> newList = new OrderedList<>();

        //Mark the index of two lists
        int f1 = 0;
        int f2 = 0;

        //Search two OrderedList list1 and list2, insert and sort the items of them
        while (f1 < list1.size() && f2 < list2.size()) {
            if (list1.get(f1).compareTo(list2.get(f2)) < 0) {
                f1++;
            } else if (list1.get(f1).compareTo(list2.get(f2)) > 0) {
                f2++;
            } else {
                newList.add(list1.get(f1));
                f1++;
                f2++;
            }
        }

        return newList;
    }

    /**
     * Write data into a OrderedList
     * @param fileName the name of the file needed to read
     * @param list a OrderedList wait to be modified
     */
    public static void createOrderedList(String fileName,OrderedList<String> list) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner inputFile = new Scanner(file);

        while (inputFile.hasNextLine()) {
            String name = inputFile.nextLine();
            list.insert(name);
        }

        inputFile.close();
    }

    /**
     * Write data in a OrderedList into a file
     * @param filename the name of the file needed to write
     * @param list a OrderedList wait to be read
     */
    public static void writeIntoFile(String filename, OrderedList<String> list) throws IOException {
        FileWriter out = new FileWriter(filename);

        for (int i=0; i<list.size(); i++) {
            out.write(list.get(i) + "\n");
        }

        out.close();
    }
}