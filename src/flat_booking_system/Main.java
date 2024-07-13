package flat_booking_system;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		BookingSystem bookingSystem = new BookingSystem();

		Scanner scanner = new Scanner(System.in);

		while (true) 
		{
			System.out.println("Enter command:");
			
			String command = scanner.nextLine();
			
			String[] commandWords = command.split(" ");

			switch (commandWords[0].toUpperCase()) 
			{
				case "REGISTER":
					bookingSystem.registerUser(commandWords[1], commandWords[2], Double.parseDouble(commandWords[3]));
					break;
				case "POST_FLAT":
					bookingSystem.postFlat(commandWords[1], commandWords[2], Double.parseDouble(commandWords[3]), Integer.parseInt(commandWords[4]));
					break;
				case "SHOW_FLATS":
					bookingSystem.showFlats(commandWords[1]);
					break;
				case "REQUEST_BOOKING":
					bookingSystem.requestBooking(commandWords[1], Integer.parseInt(commandWords[2]));
					break;
				case "UPDATE_WALLET":
					bookingSystem.updateWallet(commandWords[1], Integer.parseInt(commandWords[2]));
					break;
				case "ACCEPT_BOOKING":
					bookingSystem.acceptBooking(commandWords[1], Integer.parseInt(commandWords[2]));
					break;
				case "SHOW_TRANSACTIONS":
					bookingSystem.showTransactions();
					break;
				case "EXIT":
					scanner.close();
					return;
				default:
					System.out.println("Invalid command.");
			}
		}
	}
}
