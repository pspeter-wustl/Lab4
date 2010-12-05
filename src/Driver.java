import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Driver {
	// Instance variables
	private HashMap<String, Airport> airports;
	private Graph g;

	// Constructor
	private Driver() {
		// Initialize Variables
		airports = new HashMap<String, Airport>();
		g = new Graph();
		// Load airport data
		readAirports();
		// Load flight data
		readFlights();

	}

	// toString
	public String toString() {
		return g.toString();
	}

	// Read airport data
	private void readAirports() {
		BufferedReader r;
		try {
			InputStream is = new FileInputStream("airport-data.txt");
			r = new BufferedReader(new InputStreamReader(is));
		} catch (IOException e) {
			System.out.println("IOException while opening airport-data.txt\n"
					+ e);
			return;
		}
		try {
			String nextline = r.readLine();
			StringTokenizer st = new StringTokenizer(nextline);
			int numAirports = Integer.parseInt(st.nextToken());
			// numAirports is the number of airports, use it as needed
			for (int i = 0; i < numAirports; i++) {
				nextline = r.readLine();
				st = new StringTokenizer(nextline);
				String airportCode = st.nextToken();
				int gmtConv = Integer.parseInt(st.nextToken());
				Airport a = new Airport(airportCode, gmtConv);
				airports.put(airportCode, a);
				g.addVertex(a);
			}
		} catch (IOException e) {
			System.out.println("IOException while reading sequence from "
					+ "airport-data.txt\n" + e);
			return;
		}
	}

	// Read flight data
	private void readFlights() {
		BufferedReader r;
		try {
			InputStream is = new FileInputStream("flight-data.txt");
			r = new BufferedReader(new InputStreamReader(is));
		} catch (IOException e) {
			System.out.println("IOException while opening flight-data.txt\n"
					+ e);
			return;
		}
		try {
			String nextline = r.readLine();
			while (nextline != null && !nextline.trim().equals("")) {
				StringTokenizer st = new StringTokenizer(nextline);
				String airline = st.nextToken();
				int flightNum = Integer.parseInt(st.nextToken());
				Airport source = airports.get(st.nextToken());
				int localDepartTime = Integer.parseInt(st.nextToken());
				boolean am = st.nextToken().equals("A");
				GMTtime depart = new GMTtime(localDepartTime, source
						.getGmtOffset(), am);
				Airport destination = airports.get(st.nextToken());
				int localArriveTime = Integer.parseInt(st.nextToken());
				am = st.nextToken().equals("A");
				GMTtime arrive = new GMTtime(localArriveTime, destination
						.getGmtOffset(), am);
				Flight f = new Flight(airline, flightNum, source, destination,
						depart, arrive);
				g.addEdge(f);
				nextline = r.readLine();
			}
		} catch (IOException e) {
			System.out.println("IOException while reading sequence from "
					+ "flight-data.txt\n" + e);
			return;
		}
	}

	public static void main(String[] args) {
		Driver d = new Driver();
		Airport source = null;
		GMTtime arrive = null;
		boolean run = true;
		while (run) {
			// Get the input
			Terminal.print("Options: Compute Shortest Routes(1), ");
			Terminal.print("Show Route (2), Exit(3): ");
			int choice = Terminal.readInt();
			// Exit the program
			if (choice == 3)
				run = false;
			// Set the source
			if (choice == 1) {
				Terminal.print("Source Airport Code: ");
				String code = Terminal.readWord().toUpperCase();
				while (!d.airports.containsKey(code)) {
					Terminal.print("Source Airport Code: ");
					code = Terminal.readWord().toUpperCase();
				}
				source = d.airports.get(code);
				// Get the arrival time
				Terminal.print("Arrival Time at Airport: ");
				int arrival = Terminal.readInt();
				// Get the source time
				Terminal.print("AM or PM (A or P): ");
				boolean am = Terminal.readWord().toUpperCase().substring(0, 1)
						.equals("A");
				arrive = new GMTtime(arrival, source.getGmtOffset(), am);
			}
			// Get the path
			if (choice == 2) {
				if (source == null) {
					Terminal.println("Must set source Airport");
				} else {
					Terminal.print("Destination Airport Code: ");
					String code = Terminal.readWord().toUpperCase();
					while (!d.airports.containsKey(code)) {
						Terminal.print("Destination Airport Code: ");
						code = Terminal.readWord().toUpperCase();
					}
					Airport destination = d.airports.get(code);
					Iterator<Flight> flights = d.g.dijkstra(source,
							destination, arrive);
					int time = (int) destination.getWeight();
					int hours = time / 60;
					int minutes = time - (60 * hours);
					Terminal.println("Elapsed time from " + source
							+ " airport arrival to " + destination
							+ " arrival is " + hours + " hours and " + minutes
							+ " minutes.");
					while (flights.hasNext()) {
						Terminal.println(flights.next().toString());
					}
				}
			}
		}
	}
}
