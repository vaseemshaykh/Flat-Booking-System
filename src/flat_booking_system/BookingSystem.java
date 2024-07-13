package flat_booking_system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingSystem {
	private final Map<String, User> users = new HashMap<>();

	private final Map<Integer, Flat> flats = new HashMap<>();

	private final Map<Integer, List<BookingRequest>> bookingRequests = new HashMap<>();

	private double companyWallet = 0;

	private int flatIdCounter = 1;

	public void registerUser(String username, String role, double initialAmount) {
		if (users.containsKey(username))// already contains
		{
			System.out.println(username + " is already registered.");
			return;
		}
		users.put(username, new User(username, role, initialAmount));
		System.out.println(username + " registered as " + role + " with wallet balance " + initialAmount);
	}

	public void postFlat(String ownerName, String location, double rent, int rooms) {
		if (!users.containsKey(ownerName) || !users.get(ownerName).getRole().equals("owner")) // validating if users is
																								// // present and is
																								// owner
		{
			System.out.println(ownerName + " is not authorized to post flats.");
			return;
		}
		Flat flat = new Flat(flatIdCounter++, ownerName, location, rent, rooms);
		flats.put(flat.getFlatId(), flat);
		System.out.println("Flat posted by " + ownerName + ": ID " + flat.getFlatId() + ", Location: " + location
				+ ", Rent: " + rent + ", Rooms: " + rooms);
	}

	public void showFlats(String username) {
		if (!users.containsKey(username)) {
			System.out.println("User " + username + " not registered.");
			return;
		}
		System.out.println("Available flats:");
		boolean hasAvailableFlats = false;
		for (Flat flat : flats.values()) {
			if (!flat.isBooked()) {
				System.out.println("ID: " + flat.getFlatId() + ", Location: " + flat.getLocation() + ", Rent: "
						+ flat.getRent() + ", Rooms: " + flat.getRooms());
				hasAvailableFlats = true;
			}
		}
		if (!hasAvailableFlats) {
			System.out.println("All Flats are Booked Currently. Will keep you notified with any new updates.");
		}
	}

	synchronized public void requestBooking(String seekerName, int flatId) {
		if (!users.containsKey(seekerName) || !users.get(seekerName).getRole().equals("seeker"))// validate if contains
		{
			System.out.println("User " + seekerName + " is not authorized to request bookings.");
			return;
		}

		if ((users.get(seekerName).getWallet() < flats.get(flatId).getRent())) // validate for insufficient wallet
																				// amount
		{
			System.out.println("Insufficient Balance in Wallet");
			return;
		}

		Flat flat = flats.get(flatId);
		if (flat == null || flat.isBooked()) // printing all available flats
		{
			System.out.println("Flat ID " + flatId + " is not available for booking.");
			return;
		}

		BookingRequest request = new BookingRequest(seekerName, flatId);
		bookingRequests.computeIfAbsent(flatId, k -> new ArrayList<>()).add(request);
		System.out.println("Booking request sent by " + seekerName + " for flat ID " + flatId);
	}

	public void updateWallet(String username, double amoutToAdd) {
		if (!users.containsKey(username)) // validating if present
		{
			System.out.println(username + " is not present so update amout Failed");
			return;
		}

		users.get(username).setWallet(users.get(username).getWallet() + amoutToAdd);

		System.out.println("User " + username + " Balance Updated. Latest Balance: " + users.get(username).getWallet());
	}

	public void acceptBooking(String ownerName, int flatId) {
		Flat flat = flats.get(flatId);
		if (flat == null || !flat.getOwner().equals(ownerName)) {
			System.out.println("Booking request cannot be accepted for flat ID " + flatId);
			return;
		}

		List<BookingRequest> requests = bookingRequests.get(flatId);
		if (requests == null || requests.isEmpty()) {
			System.out.println("No booking requests for flat ID " + flatId);
			return;
		}

		// Assume the first request is accepted
		BookingRequest acceptedRequest = requests.remove(0);
		String seekerName = acceptedRequest.getSeekerName();

		User seeker = users.get(seekerName);
		double rent = flat.getRent();

		if (seeker.getWallet() >= rent) {
			seeker.setWallet(seeker.getWallet() - rent);

			double ownerEarnings = rent * 0.9;

			users.get(ownerName).setWallet(users.get(ownerName).getWallet() + ownerEarnings);

			companyWallet += rent * 0.1;

			flat.setBooked(true);

			System.out.println("Booking accepted by " + ownerName + " for flat ID " + flatId);
			System.out.println("Transaction complete: " + seeker.getUsername() + "'s wallet balance: "
					+ seeker.getWallet() + ", " + ownerName + "'s wallet balance: " + users.get(ownerName).getWallet()
					+ ", Company wallet balance: " + companyWallet);
		} else {
			System.out.println("Insufficient Balance in Wallet");
		}
	}

	public void showTransactions() {
		System.out.println("Total brokerage in the company wallet: " + companyWallet);
	}
}
