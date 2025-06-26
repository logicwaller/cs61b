package tester;

import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

import java.util.ArrayList;

public class TestArrayDequeEC {
    @Test
    public void randomizedTest(){
        StudentArrayDeque<Integer> bug = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> L = new ArrayDequeSolution<>();
        int N = 1000000;
        String status = "";
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 6);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                status = status + "addLast(" + randVal + ")\n";
                bug.addLast(randVal);
                L.add(randVal);
            } else if (operationNumber == 1){
                // addFirst
                int randVal = StdRandom.uniform(0, 100);
                status = status + "addFirst(" + randVal + ")\n";
                bug.addFirst(randVal);
                L.add(0, randVal);
            } else if (operationNumber == 2) {
                // size
                status = status + "size()\n";
                assertEquals(status, L.size(), bug.size());
            } else if (L.size() > 0 && operationNumber == 3){
                // removeLast
                status = status + "removeLast()\n";
                assertEquals(status, L.removeLast(), bug.removeLast());
            } else if (L.size() > 0 && operationNumber == 4){
                // removeFirst
                status = status + "removeFirst()\n";
                assertEquals(status, L.removeFirst(), bug.removeFirst());
            } else if (L.size() > 0 && operationNumber == 5){
                // get
                int randVal = StdRandom.uniform(0, L.size());
                status = status + "get(" + randVal + ")\n";
                assertEquals(status, bug.get(randVal), L.get(randVal));
            }
        }
    }
}
