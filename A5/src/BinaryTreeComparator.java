/***
 * Class as a comparator that can compare two BinaryTree<Pair> object
 * Used as parameter to construct PriorityQueue object
 *
 * @author Jun Gao - B00899189
 */

import java.util.Comparator;

public class BinaryTreeComparator implements Comparator<BinaryTree<Pair>> {

    //Method to compare BinaryTree<Pair> by compare their Pair object
    @Override
    public int compare(BinaryTree<Pair> t1, BinaryTree<Pair> t2) {
        return t1.getData().compareTo(t2.getData());
    }

}
