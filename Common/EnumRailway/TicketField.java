package EnumRailway;

public enum TicketField {
	DEPARTDATE("Date"),
	DEPARTFROM("DepartStation"),
	ARRIVE("ArriveStation"),
	SEATTYPE("SeatType"),
	TICKETAMOUNT("TicketAmount");
	
    private String id;

    TicketField(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
