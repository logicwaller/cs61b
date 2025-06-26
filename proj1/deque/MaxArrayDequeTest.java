package deque;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Comparator;

public class MaxArrayDequeTest {
    private static class stringCompartor implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }
    private static class stringCompartor2 implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o2.compareTo(o1);
        }
    }

    @Test
    public void stringMax(){
        MaxArrayDeque<String> t = new MaxArrayDeque<>(new stringCompartor());
        t.addLast("a");
        t.addLast("c");
        t.addLast("b");
        assertEquals("c", t.max());
        assertEquals("a", t.max(new stringCompartor2()));
    }
}
