package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class ArrayDequeTest {
    // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        ArrayDeque<Integer> bug = new ArrayDeque<Integer>();
        bug.addLast(2);
        bug.addLast(3);
        bug.addLast(4);
        int test = 2;
        for(int i : bug){
            assertEquals(test, i);
            test += 1;
        }
        assertEquals(bug, bug);
        assertEquals(3, bug.size());
        assertEquals(4, (int)bug.removeLast());
        assertEquals(3, (int)bug.removeLast());
        assertEquals(2, (int)bug.removeLast());
        assertEquals(true, bug.isEmpty());
    }

    @Test
    public void randomizedTest(){
        ArrayDeque<Integer> bug = new ArrayDeque<>();
        ArrayList<Integer> L = new ArrayList<>();
        int N = 1000000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 6);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                bug.addLast(randVal);
                L.add(randVal);
//                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1){
                // addFirst
                int randVal = StdRandom.uniform(0, 100);
                bug.addFirst(randVal);
                L.add(0, randVal);
//                System.out.println("addFirst(" + randVal + ")");
            } else if (operationNumber == 2) {
                // size
//                System.out.println("size: " + L.size());
                assertEquals(L.size(), bug.size());
            } else if (L.size() > 0 && operationNumber == 3){
                // removeLast
                assertEquals(L.remove(L.size() - 1), bug.removeLast());
            } else if (L.size() > 0 && operationNumber == 4){
                // removeFirst
                assertEquals(L.remove(0), bug.removeFirst());
            } else if (L.size() > 0 && operationNumber == 5){
                // get
                assertEquals(bug.get(L.size() - 1), L.get(L.size() - 1));
            }
        }
    }
}
