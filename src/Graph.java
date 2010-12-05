import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class Graph {
	// Instance variables
	private int edges;
	private ArrayList<Vertex> vertices;

	// Constructor
	public Graph() {
		vertices = new ArrayList<Vertex>();
		edges = 0;
	}

	// add a vertex
	public void addVertex(Vertex v) {
		vertices.add(v);
	}

	// add an edge
	public void addEdge(Edge e) {
		edges++;
	}

	// get the number of vertices
	public int numVertices() {
		return vertices.size();
	}

	// get the number of edges
	public int numEdges() {
		return edges;
	}

	// get the iterator of edges from a vertex
	public Iterator<Edge> outEdges(Vertex v) {
		return v.edgeIterator();
	}

	// get an iterator of the vertices
	public Iterator<Vertex> vertexIterator() {
		return vertices.iterator();
	}

	// toString
	public String toString() {
		String temp = "";
		Iterator<Vertex> vi = vertexIterator();
		// Loop through the vertices
		while (vi.hasNext()) {
			Vertex v = vi.next();
			temp += v + "\n";
			Iterator<Edge> ei = outEdges(v);
			// Loop through the edges
			while (ei.hasNext()) {
				Edge e = ei.next();
				temp += "  " + e + "\n";
			}
		}
		return temp;
	}

	public Iterator<Flight> dijkstra(Airport source, Airport destination,
			GMTtime arrival) {
		HashSet<Airport> airports = new HashSet<Airport>();
		BinaryHeap heap = new BinaryHeap(numVertices());
		airports.add(source);
		source.reset();
		source.setTracker(heap.put(0, source));
		while (!heap.isEmpty()) {
			Airport a = (Airport) heap.extractMin();
			GMTtime start;
			if (a.getFlight() != null) {
				start = a.getFlight().getArrive();
			} else {
				start = arrival;
			}
			Iterator<Edge> flights = outEdges(a);
			while (flights.hasNext()) {
				Flight f = (Flight) flights.next();
				Airport b = (Airport) f.getTail();
				if (!airports.contains(b)) {
					b.setTracker(heap.put(java.lang.Integer.MAX_VALUE, b));
					airports.add(b);
				}
				int layover = f.getDepart().minutesSince(start);
				if (layover >= 60) {
					double weight = f.getWeight() + a.getWeight() + layover;
					b.setFlight(weight, f);
				}
			}
		}
		Airport i = destination;
		LinkedList<Flight> flights = new LinkedList<Flight>();
		while (i.getFlight() != null) {
			flights.addFirst(i.getFlight());
			i = (Airport) i.getFlight().getHead();
		}
		return flights.iterator();
	}
}
