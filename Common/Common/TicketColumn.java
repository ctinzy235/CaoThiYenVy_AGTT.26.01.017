package Common;

public enum TicketColumn {

	DEPART_STATION("Depart Station"),
    ARRIVE_STATION("Arrive Station"),
    SEAT_TYPE("Seat Type"),
    DEPART_DATE("Depart Date"),
    BOOK_DATE("Book Date"),
    EXPIRED_DATE("Expired Date"),
    AMOUNT("Amount"),
    TOTAL_PRICE("Total Price");

    private final String value;
    
    TicketColumn(String value) { 
    	this.value = value; 
    }
    
    public String getValue() { 
    	return value; 
    }
}
