package Common;

public enum Tab {
	LOGIN("Login"),
    REGISTER("Register"),
    LOGOUT("Log out"),
    FAQ("FAQ"),
    TIMETABLE("Timetable"),
    CONTACT("Contact"),
    TICKETPRICE("Ticket price"),
    MYTICKET("My ticket"),
    BOOKTICKET("Book ticket");
	
	private String displayName;

    Tab(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}


