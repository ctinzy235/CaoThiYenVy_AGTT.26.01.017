package DataObjects;

import EnumRailway.SeatType;

public class SeatPrice {

	private SeatType seatType;
    private String price;

    public SeatPrice(SeatType seatType, String price) {
        this.seatType = seatType;
        this.price = price;
    }

    public SeatType getSeatType() { 
    	return seatType; 
    }
    public String getPrice() { 
    	return price; 
    }
}
