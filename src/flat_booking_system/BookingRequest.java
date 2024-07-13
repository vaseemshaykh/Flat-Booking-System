package flat_booking_system;

public class BookingRequest 
{
    private String seekerName;
    private int flatId;

    public BookingRequest(String seekerName, int flatId) {
        this.seekerName = seekerName;
        this.flatId = flatId;
    }

    public String getSeekerName() {
        return seekerName;
    }

    public int getFlatId() {
        return flatId;
    }
}
