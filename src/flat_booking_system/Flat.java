package flat_booking_system;

//modal class
public class Flat {
	private int flatId;
	private String owner;
	private String location;
	private double rent;
	private int rooms;
	private boolean isBooked;

	public Flat(int flatId, String owner, String location, double rent, int rooms) {
		this.flatId = flatId;
		this.owner = owner;
		this.location = location;
		this.rent = rent;
		this.rooms = rooms;
		this.isBooked = false;
	}

	public int getFlatId() {
		return flatId;
	}

	public void setFlatId(int flatId) {
		this.flatId = flatId;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getRent() {
		return rent;
	}

	public void setRent(double rent) {
		this.rent = rent;
	}

	public int getRooms() {
		return rooms;
	}

	public void setRooms(int rooms) {
		this.rooms = rooms;
	}

	public boolean isBooked() {
		return isBooked;
	}

	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}

}
