package Common;

public enum TicketTable {
	DEPARTDATE("4"),
	DEPARTFROM("1"),
	ARRIVE("2"),
	SEATTYPE("3"),
	TICKETAMOUNT("7");
	
    private String id;

    TicketTable(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
