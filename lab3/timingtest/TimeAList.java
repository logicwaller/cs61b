package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        AList<Integer> test = new AList<Integer>();
        AList<Integer> Ns = new AList<Integer>();
        AList<Double> ops = new AList<Double>();
        Ns.addLast(1000);
        for(int i = 0; i < 7; i++){
            Ns.addLast(Ns.getLast() * 2);
        }
        Stopwatch sw = new Stopwatch();
        int j = 0;
        for(int i = 1; i <= Ns.getLast(); i++){
            test.addLast(i);
            if(i == Ns.get(j)){
                ops.addLast(sw.elapsedTime());
                j += 1;
            }
        }
        printTimingTable(Ns, ops, Ns);
    }
}
