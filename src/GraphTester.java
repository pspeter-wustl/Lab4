// This assumes the following classes and methods.  You may modify this as needed.
//   
// Vertex Class
//   constructor that takes a single input which is the name for the vertex
//   a toString method that uses the name provided as input to the constructor
//
// Edge Class
//   constructor that creates an edge defined by a head, tail and weight.  For example
//   for the edge from K -> J, K is the head, J is the tail, and the weight is 3.5
//
// Graph Class
//   constructor that takes no arguments and creates an empty graph
//   addVertex(Vertex v) that adds vertex v to the graph without any edges
//   addEdge(Vertex head,Vertex tail, double weight) adds the head head->tail with
//                      the given weight to the graph
//   numVertices() returns the current number of vertices
//   numEdges() returns the current number of edges
//   toString outputs for each vertex a list of adjacent edges (with the weight)
//         you should use the same method to do this here as you will use when
//         implementing Part 3

import java.util.*;
public class GraphTester {

    public static void main(String args[]) {
	Terminal.startTranscript("graphTester-output");
	Terminal.readInputFromFile("graph-data.txt");
	HashMap vertices = new HashMap();
	int n = Terminal.readInt();
	int m = Terminal.readInt();
	Graph g = new Graph();
	for (int i=0; i < n; i++){
	    String name = Terminal.readWord();
	    Vertex v = new Vertex(name);
	    vertices.put(name,v);
	    g.addVertex(v);
	}
	for (int j=0; j < m; j++) {
	    Vertex head = (Vertex) vertices.get(Terminal.readWord());
	    Vertex tail = (Vertex) vertices.get(Terminal.readWord());
	    double w = Terminal.readDouble();
	    Edge e = new Edge(head,tail,w);
	    g.addEdge(e);
	}

	Terminal.println("The graph has " + g.numVertices() + " vertices and " +
			 g.numEdges() + " edges.");
	Terminal.println("Adjacencies with weight given in parentheses are as follows:");
	Terminal.println("------------------------------------------------------------");
	Terminal.println(""+g);
	Terminal.stopTranscript();
    }
}
