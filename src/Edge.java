public class Edge {
	// Instance variables
	private Vertex head;
	private Vertex tail;
	private double weight;

	// Constructor
	public Edge(Vertex head, Vertex tail, double weight) {
		this.head = head;
		this.tail = tail;
		this.weight = weight;
		// Add this edge to the head
		this.head.addEdge(this);
	}

	// toString
	public String toString() {
		return "<" + head + ", " + tail + ", " + weight + ">";
	}

	// returns the Head
	public Vertex getHead() {
		return head;
	}

	// returns the Tail
	public Vertex getTail() {
		return tail;
	}

	// returns the Weight
	public double getWeight() {
		return weight;
	}
}
