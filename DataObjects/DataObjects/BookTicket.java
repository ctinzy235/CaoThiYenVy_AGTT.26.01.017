package DataObjects;

import EnumRailway.SeatType;
import EnumRailway.Station;

public class BookTicket {
	private String departDate;
    private Station departStation;
    private Station arriveStation;
    private SeatType seatType;
    private String ticketAmount;
    
    public BookTicket(String departDate, Station departStation, Station arriveStation, SeatType seatType, String ticketAmount) {
    	this.departDate = departDate;
    	this.departStation = departStation;
    	this.arriveStation = arriveStation;
    	this.seatType = seatType;
    	this.ticketAmount = ticketAmount;
    }
    
	public String getDepartDate() {
		return departDate;
	}
	public void setDepartDate(String departDate) {
		this.departDate = departDate;
	}
	public Station getDepartFrom() {
		return departStation;
	}
	public void setDepartFrom(Station departFrom) {
		this.departStation = departFrom;
	}
	public Station getArrive() {
		return arriveStation;
	}
	public void setArrive(Station arrive) {
		this.arriveStation = arrive;
	}
	public SeatType getSeatType() {
		return seatType;
	}
	public void setSeatType(SeatType seatType) {
		this.seatType = seatType;
	}
	public String getTicketAmount() {
		return ticketAmount;
	}
	public void setTicketAmount(String ticketAmount) {
		this.ticketAmount = ticketAmount;
	}
    
    
}
