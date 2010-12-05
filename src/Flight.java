public class Flight extends Edge {
	// Instance Variables
	private String airline;
	private int flightNum;
	private GMTtime depart;
	private GMTtime arrive;

	// Constructor
	public Flight(String airline, int flightNum, Airport source,
			Airport destination, GMTtime depart, GMTtime arrive) {
		super(source, destination, arrive.minutesSince(depart));
		this.airline = airline;
		this.flightNum = flightNum;
		this.depart = depart;
		this.arrive = arrive;
	}

	// Accessors
	public String getAirline() {
		return airline;
	}

	public int getFlightNum() {
		return flightNum;
	}

	public GMTtime getDepart() {
		return depart;
	}

	public GMTtime getArrive() {
		return arrive;
	}

	public String toString() {
		return airline + " " + flightNum + " (" + super.getHead() + " "
				+ depart + " --> " + super.getTail() + " " + arrive + ")";
	}
}
