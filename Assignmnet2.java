package assignment;
import java.io.*;
import java.util.*;
import java.lang.reflect.*;
// custom exception
class DataEmptyException extends Exception {
    public DataEmptyException(String msg) { super(msg); }
}
// interface
interface Analyzer {
    void analyze(List<Integer> numbers) throws DataEmptyException;
}
// abstract class
abstract class BaseProcessor {
    abstract void displayType();
}
final class Utility {
    static int[] getArray() { return new int[]{12, 25, 30, 47, 50, 61}; }
}

// main processor
class AdvancedStreamProcessor extends BaseProcessor implements Analyzer {
    @Override void displayType() { System.out.println("Advanced Stream Processor"); }

    // inner class
    class Stats { void show(List<Integer> nums) { System.out.println("Total numbers: " + nums.size()); } }

    @Override
    public void analyze(List<Integer> nums) throws DataEmptyException {
        if (nums.isEmpty()) throw new DataEmptyException("No numbers to process!");
        int even = 0, sum = 0;
        for (int n : nums) { if (n % 2 == 0) even++; sum += n; }
        double avg = (double) sum / nums.size();
        System.out.println("Even count: " + even + "\nAverage: " + avg);
        new Stats().show(nums);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("results.txt"))) {
            bw.write("Even count = " + even + "\nAverage = " + avg + "\n");
        } catch (IOException e) { System.out.println("File error: " + e.getMessage()); }
    }
}
public class Assignmnet2 {

	public static void main(String[] args) {
		 try {
	            List<Integer> list = new ArrayList<>();
	            for (int x : Utility.getArray()) list.add(x);

	            Analyzer proc = new AdvancedStreamProcessor();
	            ((AdvancedStreamProcessor) proc).displayType();
	            proc.analyze(list);

	            System.out.println("\nMethods in AdvancedStreamProcessor:");
	            for (Method m : AdvancedStreamProcessor.class.getDeclaredMethods())
	                System.out.println("- " + m.getName());

	            System.out.println("\nResults saved to results.txt");
	        } catch (DataEmptyException e) {
	            System.out.println("Error: " + e.getMessage());
	        }

	}

}
