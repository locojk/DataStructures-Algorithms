/***
 * Class representative a pair of data with a letter of alphabet and its probability of occurrence
 * Have a method to compare two Pairs
 *
 * @author Jun Gao - B00899189
 */

public class Pair implements Comparable<Pair>{ // declare all required fields
    private char value;
    private double prob;

    //constructor
    public Pair(char value, double prob) {
        this.value = value;
        this.prob = prob;
    }

    // getters
    public char getValue() {
        return value;
    }
    public double getProb() {
        return prob;
    }

    // setters
    public void setValue(char value) {
        this.value = value;
    }
    public void setProb(double prob) {
        this.prob = prob;
    }

    // toString
    @Override
    public String toString() {
        return value + "    " + prob;
    }

    /** The compareTo method overrides the compareTo method of the Comparable interface.
     */
    @Override public int compareTo(Pair p){
        return Double.compare(this.getProb(), p.getProb());
    }

}
