
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * This is the event object that will store information of the object for sorting later
 * @author Vivian Hoang
 *
 */
public class Event implements Comparable<Event>, Serializable {
	private String title;
	private String date;
	private String starttime;
	private String endtime;
	private int month;
	private int day;
	private int year;
	private int hour;
	private int minutes;

	/**
	 * Intializes all vairables of event including name and time
	 * @param title description of the event
	 * @param date MM/DD/YYYY
	 * @param starttime Starting hours and minutes
	 * @param endtime Ending hours and minutes
	 */
	public Event(String title, String date, String starttime, String endtime) {
		this.title = title;
		this.date = date;
		this.starttime = starttime;
		this.endtime = endtime;
		month = Integer.parseInt(date.substring(0, 2));
		day = Integer.parseInt(date.substring(3, 5));
		year = Integer.parseInt(date.substring(6, 10));
		hour = Integer.parseInt(starttime.substring(0, 2));
		minutes = Integer.parseInt(starttime.substring(3, 5));
	}

	/**
	 * Gives title/description
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Gives MM/DD/YYYY
	 * @return date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Gives year
	 * @return year
	 */
	public int getYear() {
		return year;
	}

	/**
	 *  Gives month
	 * @return month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 *  Gives day
	 * @return day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * Gives starting hours
	 * @return starttime
	 */
	public String getStarttime() {
		return starttime;
	}

	/**
	 * Gives ending hours
	 * @return end time
	 */
	public String getEndtime() {
		return endtime;
	}

	/**
	 * Determines if event order
	 */
	public int compareTo(Event input) {
		if ((int) Math.signum(year - input.year) == 0) {
			if ((int) Math.signum(month - input.month) == 0) {
				if ((int) Math.signum(day - input.day) == 0) {
					if ((int) Math.signum(hour - input.hour) == 0) {
						return (int) Math.signum(minutes - input.minutes);
					} else {
						return (int) Math.signum(hour - input.hour);
					}
				} else {
					return (int) Math.signum(day - input.day);
				}
			} else {
				return (int) Math.signum(month - input.month);
			}
		} else {
			return (int) Math.signum(year - input.year);
		}
	}

	/**
	 * Determines event order via contract
	 */
	public boolean equals(Object other_events) {
		return this.compareTo((Event) other_events) == 0;
	}

	/**
	 * Hashcode for the hashset of EventList
	 */
	public int hashCode() {
		return title.hashCode() + date.hashCode() + starttime.hashCode() + endtime.hashCode();
	}

	/**
	 * Print out string to readable format
	 */
	public String toString() {
		MONTHS[] arrayOfMonths = MONTHS.values();
		FULL_DAYS[] arrayOfDays = FULL_DAYS.values();
		GregorianCalendar tempo = new GregorianCalendar(year, month-1, day);
		int themonth = tempo.get(Calendar.MONTH);
		int theday = tempo.get(Calendar.DAY_OF_WEEK);
		if (endtime.equals("n/a")) {
			return "   " + arrayOfDays[theday-1] + " " + arrayOfMonths[themonth]+" " + day + " " + starttime + " " + title;
		}
		return "   " + arrayOfDays[theday-1] + " " + arrayOfMonths[themonth] +" "+ day + " " + starttime + " - " + endtime
				+ " " + title;
	}

}
