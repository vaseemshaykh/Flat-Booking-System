package flat_booking_system;

//modal class
public class User {
	private String username;
	private String role;
	private double wallet;

	public User(String username, String role, double wallet) {
		this.username = username;
		this.role = role;
		this.wallet = wallet;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public double getWallet() {
		return wallet;
	}

	public void setWallet(double wallet) {
		this.wallet = wallet;
	}
	
	
}
