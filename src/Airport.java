import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Airport extends Vertex {
	// Instance Variables
	private int gmtOffset;
	private Flight flight;
	private BinaryHeap.Tracker tracker;
	private double weight;

	// Constructor
	public Airport(String name, int gmtOffset) {
		super(name);
		this.gmtOffset = gmtOffset;
	}

	// get the GMT Offset for the airport
	public int getGmtOffset() {
		return gmtOffset;
	}

	// reset the Airport
	public void reset() {
		tracker = null;
		flight = null;
		weight = 0;
	}

	// set the Tracker
	public void setTracker(BinaryHeap.Tracker t) {
		this.tracker = t;
	}

	// set the flight and priority
	public void setFlight(double priority, Flight f) {
		if (tracker.decreaseKey(priority)) {
			this.flight = f;
			weight = priority;
		}
	}

	// get the priority
	public double getWeight() {
		return weight;
	}

	// get the Flight
	public Flight getFlight() {
		return flight;
	}
}
