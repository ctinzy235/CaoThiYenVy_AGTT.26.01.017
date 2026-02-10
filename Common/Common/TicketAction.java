package Common;

public enum TicketAction {

	CHECK_PRICE("check price"),
    BOOK_TICKET("book ticket");

    private final String value;

    TicketAction(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
