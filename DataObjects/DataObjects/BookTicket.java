package DataObjects;

public class BookTicket {
	private String departDate;
    private String departFrom;
    private String arrive;
    private String seatType;
    private String ticketAmount;
    
    public BookTicket(String departDate, String departFrom, String arrive, String seatType, String ticketAmount) {
    	this.departDate = departDate;
    	this.departFrom = departFrom;
    	this.arrive = arrive;
    	this.seatType = seatType;
    	this.ticketAmount = ticketAmount;
    }
    
	public String getDepartDate() {
		return departDate;
	}
	public void setDepartDate(String departDate) {
		this.departDate = departDate;
	}
	public String getDepartFrom() {
		return departFrom;
	}
	public void setDepartFrom(String departFrom) {
		this.departFrom = departFrom;
	}
	public String getArrive() {
		return arrive;
	}
	public void setArrive(String arrive) {
		this.arrive = arrive;
	}
	public String getSeatType() {
		return seatType;
	}
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
	public String getTicketAmount() {
		return ticketAmount;
	}
	public void setTicketAmount(String ticketAmount) {
		this.ticketAmount = ticketAmount;
	}
    
    
}
