package assignment3;
import java.util.ArrayList;
import java.util.List;
class SafeCollection<T> {
 private List<T> data = new ArrayList<>();
 public synchronized void add(T element) {
     data.add(element);
     System.out.println(Thread.currentThread().getName() + " added: " + element);
 }
 public synchronized void remove(T element) {
     if (data.remove(element)) {
         System.out.println(Thread.currentThread().getName() + " removed: " + element);
     } else {
         System.out.println(Thread.currentThread().getName() + " could not find: " + element);
     }
 }
 public synchronized void display() {
     System.out.println("Collection now: " + data);
 }
 public synchronized int size() {
     return data.size();
 }
}
class CollectionWorker extends Thread {
 private SafeCollection<Integer> collection;

 CollectionWorker(SafeCollection<Integer> collection, String name) {
     super(name);
     this.collection = collection;
 }

 public void run() {
     for (int i = 1; i <= 3; i++) {
         collection.add(i);
         try {
             Thread.sleep(500); // simulate processing delay
         } catch (InterruptedException e) {
             System.out.println(getName() + " interrupted");
         }
     }
     collection.remove(2);
     collection.display();
 }
}
public class ThreadSafeGenericCollection {
 public static void main(String[] args) {
     SafeCollection<Integer> collection = new SafeCollection<>();

     // create multiple threads
     CollectionWorker t1 = new CollectionWorker(collection, "Thread-1");
     CollectionWorker t2 = new CollectionWorker(collection, "Thread-2");

     t1.start();
     t2.start();

     try {
         t1.join();
         t2.join();
     } catch (InterruptedException e) {
         System.out.println("Main thread interrupted");
     }

     System.out.println("Final collection size: " + collection.size());
     collection.display();
 }
}
