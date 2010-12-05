public class BinaryHeapTester {
  public static void main(String args[]){
    BinaryHeap h = new BinaryHeap(10);
    BinaryHeap.Tracker[] trackers = new BinaryHeap.Tracker[10];
	
    System.out.println("The isEmpty method returns " + h.isEmpty());
    trackers[0] = h.put(5.25,"a");
    trackers[1] = h.put(3.5,"b");
    trackers[2] = h.put(4.5,"c");
    System.out.println("After inserting 5.25 (data a), 3.5 (data b), 4.5 (data c), the heap is:\n" + h);
    trackers[3] = h.put(3.14,"d");
    System.out.println("After inserting 3.14 (data d), the heap is:\n" + h);
    trackers[4] = h.put(7.3,"e");
    trackers[5] = h.put(20.0,"f");
    trackers[6] = h.put(4.75,"g");
    System.out.println("After inserting 7.3 (data e), 20 (data f), 4.75 (data g), the heap is:\n" + h);
    trackers[7] = h.put(1.5,"h");
    System.out.println("After inserting 1.5 (data h), the heap is:\n" + h);
    trackers[8] = h.put(8.8,"i");
    trackers[9] = h.put(9.0,"j");
    System.out.println("After inserting 8.8 (data i), 9.0 (data j), the heap is:\n" + h);

    System.out.println("There are " +  h.size() + " elements in the heap.");
    System.out.println("The isEmpty method returns " + h.isEmpty() + "\n");

    System.out.println("decreasing key for trackers[0] (which is node with key 5.25) to 1.0");
    if (trackers[0].decreaseKey(1.0))
      System.out.println("The key was decreased");
    else
      System.out.println("The key was not decreased");
    System.out.println("The heap is:\n" + h);

    System.out.println("decreasing key for trackers[2] (which is node with key 4.5) to 1.25");
    if (trackers[2].decreaseKey(1.25))
      System.out.println("The key was decreased");
    else
      System.out.println("The key was not decreased");
    System.out.println("The heap is:\n" + h);

    System.out.println("decreasing key for trackers[3] (which is node with key 3.14) to 5.15");
    if (trackers[3].decreaseKey(5.15))
      System.out.println("The key was decreased");
    else
      System.out.println("The key was not decreased");

    for (int i=0; i < 4; i++){
      System.out.println("Removing the key " + h.minimumKey());
      System.out.println("   it had data " + h.extractMin());
      for (int j =0; j < 4; j++)
	  System.out.println("   trackers[" + j + "].inHeap() returns " + trackers[j].inHeap());
      System.out.println("The heap is:\n" + h);
    }
    System.out.println("There are " +  h.size() + " elements in the heap.");
  }
}
