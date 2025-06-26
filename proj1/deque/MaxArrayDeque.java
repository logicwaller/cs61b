package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        comparator = c;
    }

    public T max() {
        T res = this.get(0);
        for (int i = 1; i < this.size(); i++) {
            if (comparator.compare(res, this.get(i)) < 0) {
                res = this.get(i);
            }
        }
        return res;
    }

    public T max(Comparator<T> c) {
        T res = this.get(0);
        for (int i = 1; i < this.size(); i++) {
            if (c.compare(res, this.get(i)) < 0) {
                res = this.get(i);
            }
        }
        return res;
    }
}
