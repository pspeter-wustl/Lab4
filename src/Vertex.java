import java.util.ArrayList;
import java.util.Iterator;

public class Vertex {
	// Instance Variables
	private String name;
	private ArrayList<Edge> out;

	// Constructor
	public Vertex(String name) {
		this.name = name;
		this.out = new ArrayList<Edge>();
	}

	// Add an edge to the vertex
	public void addEdge(Edge e) {
		out.add(e);
	}

	// Returns the iterator of edges
	public Iterator<Edge> edgeIterator() {
		return out.iterator();
	}

	// toString method
	public String toString() {
		return name;
	}

}
