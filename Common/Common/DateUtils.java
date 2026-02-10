package Common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

	public static String getFutureDate(int days, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDate.now().plusDays(days).format(formatter);
    }
	
	public static String addDaysToDate(String dateStr, int days, String format) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
	    LocalDate date = LocalDate.parse(dateStr, formatter);
	    return date.plusDays(days).format(formatter);
	}
}
