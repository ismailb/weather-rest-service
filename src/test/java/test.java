import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class test {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DateTime dateTime = DateTime.now();
		DateTimeFormatter format = DateTimeFormat.forPattern("HH:mm").withLocale(new Locale("" ,"US"));
		System.out.println( format.print(dateTime));
		
		
		Date date = DateTime.now().toDate();
		Calendar cal = Calendar.getInstance(new Locale("" ,"IN"));

		SimpleDateFormat sm = new SimpleDateFormat("HH:mm", new Locale("" ,"US"));
		sm.setCalendar(cal);
		System.out.println(sm.format(date));
	}

}
