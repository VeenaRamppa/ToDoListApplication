package individualProject_Classes;
/* DateValidator Class 
 * --- this class is used to validate the date entered by the user 
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class DateValidator {
	public static Date isThisDateValid(String dateToValidate) throws ParseException {
		if (dateToValidate == null) {
			return null;
		}
		Date date;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		sdf.setLenient(false);
		try {
			// if not valid, it will throw ParseException
			date = sdf.parse(dateToValidate);
			if (date.compareTo(new Date()) == -1)
				return null;
		} catch (ParseException e) {
			System.out.println("Enter date in correct format (MM/dd/yyyy)");
			return null;
		}
		return date;
	}
}