import java.util.Arrays;

public class BinaryHeap {
	// Instance variables
	private int elements; // Index for elements
	private Element[] heap; // Heap of elements

	// Default Constructor that uses a default size of 16
	public BinaryHeap() {
		elements = -1;
		heap = new Element[16];
	}

	public BinaryHeap(int size) {
		elements = -1;
		heap = new Element[size];
	}

	// Tracker
	public class Tracker {
		public int index;

		// Constructor
		public Tracker(int index) {
			this.index = index;
		}

		public boolean decreaseKey(double newKey) {
			if (inHeap()) {
				if (newKey < heap[index].key) {
					heap[index].key = newKey;
					decrease(index);
					return true;
				}
			}
			return false;
		}

		public boolean inHeap() {
			return index >= 0;
		}

		public double getKey() {
			if (inHeap()) {
				return heap[index].key;
			}
			return -1;
		}
	}

	// Holds information for the binary heap
	private class Element {
		public double key;
		public Object data;
		public Tracker tracker;

		// Constructor
		public Element(double key, Object data, Tracker tracker) {
			this.key = key;
			this.data = data;
			this.tracker = tracker;
		}

		// toString
		public String toString() {
			return "(" + key + ": " + data + ")";
		}
	}

	// Determine if the elements index is >= 0
	public boolean isEmpty() {
		return elements < 0;
	}

	// Put an object in the heap
	public Tracker put(double key, Object data) {
		elements++;
		Tracker t = new Tracker(elements);
		heap[elements] = new Element(key, data, t);
		// move the key
		decrease(elements);
		return t;
	}

	// Return the size
	public int size() {
		return elements + 1;
	}

	// Returns the minimum key
	public double minimumKey() {
		return heap[0].key;
	}

	// Extracts the min from the priority queue
	public Object extractMin() {
		Element min = heap[0];
		swap(0, elements);
		// Remove the tracker
		min.tracker.index = -1;
		elements--;
		// heapify
		heapify(0);
		return min.data;
	}

	// Private Heap Methods

	// Parent
	private int parent(int element) {
		return ((element + 1) / 2) - 1;
	}

	// Left
	private int left(int element) {
		return (2 * element) + 1;
	}

	// Right
	private int right(int element) {
		return (2 * element) + 2;
	}

	// Swap two elements and maintain the tracker
	private void swap(int a, int b) {
		Element temp = heap[a];
		heap[a] = heap[b];
		heap[a].tracker.index = a;
		heap[b] = temp;
		heap[b].tracker.index = b;
	}

	// Heapify an element
	private void heapify(int element) {
		// Set up pointers
		int index = element;
		int l = left(element);
		int r = right(element);
		// Check left first
		if (l <= elements && heap[l].key < heap[index].key) {
			index = l;
		}
		// Check the right next
		if (r <= elements && heap[r].key < heap[index].key) {
			index = r;
		}
		if (index != element) {
			swap(index, element);
			heapify(index);
		}
	}

	// decrease the value of a key
	private void decrease(int element) {
		int parent = parent(element);
		while (parent > -1 && heap[element].key < heap[parent].key) {
			swap(element, parent);
			element = parent;
			parent = parent(element);
		}
	}

	// toString
	public String toString() {
		String temp = "[";
		for (int i = 0; i <= elements; i++) {
			temp += heap[i];
			if (i != elements) {
				temp += ", ";
			}
		}
		return temp + "]";
	}
}
