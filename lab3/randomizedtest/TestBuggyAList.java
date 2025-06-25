package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import timingtest.AList;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        BuggyAList<Integer> bug = new BuggyAList<Integer>();
        AListNoResizing<Integer> right = new AListNoResizing<>();
        bug.addLast(2);
        bug.addLast(3);
        bug.addLast(4);
        right.addLast(2);
        right.addLast(3);
        right.addLast(4);

        assertEquals(right.size(), bug.size());
        assertEquals(right.removeLast(), bug.removeLast());
        assertEquals(right.removeLast(), bug.removeLast());
        assertEquals(right.removeLast(), bug.removeLast());
    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> bug = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                bug.addLast(randVal);
//                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
//                System.out.println("size: " + size);
                assertEquals(L.size(), bug.size());
            } else if (L.size() > 0 && operationNumber == 2){
                assertEquals(L.removeLast(), bug.removeLast());
            } else if (L.size() > 0 && operationNumber == 3){
                assertEquals(bug.getLast(), L.getLast());
            }
        }
    }
}
