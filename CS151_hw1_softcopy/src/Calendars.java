
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;

/**
 * This gives the user the visual calendars on the initial screen and the day/month screen to determine events and what day is today
 * @author Vivian Hoang
 *
 */
public class Calendars {
	private GregorianCalendar cal = new GregorianCalendar();
	private int current_month;
	private int current_year;
	private int current_day;
	private int row = 6;
	private int col = 7;
	private MONTHS[] arrayOfMonths = MONTHS.values();
	private DAYS[] arrayOfDays = DAYS.values();
	private AB_MONTHS[] arrayOfABMonths = AB_MONTHS.values();
	private FULL_DAYS[] arrayOfFullDays = FULL_DAYS.values();
	private EventList list_of_month_events;
	private ArrayList<Integer> list_of_days;

	/**
	 * Initializes the object
	 * @param report the event list for the calendar
	 */
	public Calendars(EventList report) {
		current_month = cal.get(Calendar.MONTH);
		current_year = cal.get(Calendar.YEAR);
		current_day = cal.get(Calendar.DATE);
		list_of_month_events = new EventList();
		list_of_days = new ArrayList<Integer>(33);
		list_of_days.add(current_day);
	}

	/**
	 * This will only grab the events of the current month the user is looking at
	 * @param events events for current months
	 * @param n is used to determine the month for the event
	 */
	public void passEvents(EventList events, int n) {
		for (Event today : events) {
			if (today.getMonth() == (current_month + 1 + n)) {
				list_of_month_events.add(today);
				list_of_days.add(today.getDay());
			}
		}
	}
	
	/**
	 * Grab events for the Go_To function
	 * @param month
	 * @param events
	 */
	public void passEvents(int month, EventList events){
		for(Event today: events){
			if(today.getMonth() == month){
				list_of_month_events.add(today);
				list_of_days.add(today.getDay());
			}
		}
	}

	/**
	 * Returns all the instant variables back to default
	 */
	public void reset() {
		current_month = cal.get(Calendar.MONTH);
		current_year = cal.get(Calendar.YEAR);
		current_day = cal.get(Calendar.DATE);
		list_of_month_events.clear();
		list_of_days.clear();
	}

	/**
	 * Return total amount of event days to zero
	 */
	public void resetEvents() {
		list_of_days.clear();
	}

	/**
	 * Gets the month visual
	 * @param prevornext determines if user what's the next or previous month
	 */
	public int[][] get_Month_Calendar_Event(int prevornext) {
		current_month += prevornext;
		if (current_month == 12) {
			current_month = 0;
			current_year++;
		} else if (current_month == -1) {
			current_month = 11;
			current_year--;
		}
		System.out.println(arrayOfMonths[current_month] + " " + current_year);
		for (DAYS day : arrayOfDays) { // prints the days
			System.out.print(day + " ");
		}
		int[][] row_column = new int[row][col];
		GregorianCalendar tempo = new GregorianCalendar(current_year, current_month, 1);
		int totaldays = tempo.getActualMaximum(Calendar.DATE);
		int day_counter = 1;
		for (int x = 0; x < row; x++) { // numbers the calendar in the correct
										// spots
			if (day_counter > totaldays)
				break;
			if (x == 0) {
				for (int y = tempo.get(Calendar.DAY_OF_WEEK) - 1; y < col; y++) {
					row_column[x][y] = day_counter;
					day_counter++;
					if (day_counter > totaldays)
						break;
				}
			} else {
				for (int y = 0; y < col; y++) {
					row_column[x][y] = day_counter;
					day_counter++;
					if (day_counter > totaldays)
						break;
				}
			}
		}
		//System.out.println(toString(row_column));
		return row_column;
	}

	/**
	 * Gets the daily events if any
	 * @param prevornext user interacts with this class to see next or previous day 
	 */
	public String getDayView(int prevornext, EventList event) {
		int totaldays = cal.getActualMaximum(Calendar.DATE);
		current_day += prevornext;
		if (current_day > totaldays) {
			current_month++;
			if (current_month == 12) {
				current_month = 0;
				current_year++;
				passEvents(event,1);
			}
		} else if (current_day < 1) {
			current_month--;
			if (current_month == -1) {
				current_month = 11;
				current_year--;
				passEvents(event,-1);
			}
		}
		GregorianCalendar tempo = new GregorianCalendar(current_year, current_month, current_day);
		FULL_DAYS that_day = arrayOfFullDays[tempo.get(Calendar.DAY_OF_WEEK) - 1];
		AB_MONTHS that_month = arrayOfABMonths[tempo.get(Calendar.MONTH)];
		System.out.println(that_day + ", " + that_month + " " + current_day + ", " + current_year);
		String print = "";
		for (Event current : list_of_month_events) {
			if (current.getDay() == (current_day)) {
				if (!current.getEndtime().equals("n/a")) {
					print = current.getTitle() + " " + current.getStarttime() + " - " + current.getEndtime();
				} else {
					print = current.getTitle() + " " + current.getStarttime();
				}
				//System.out.println(print);
			}
		}
		return print;
	}

	/**
	 * Gets the DayView for the Go_To method
	 * @param date allows user to navigate straight to the day
	 */
	public void getDayView(String date) {
		int this_month = Integer.parseInt(date.substring(0, 2));
		int this_day = Integer.parseInt(date.substring(3, 5));
		int this_year = Integer.parseInt(date.substring(6, 10));
		GregorianCalendar tempo = new GregorianCalendar(this_year, this_month - 1, this_day);
		FULL_DAYS that_day = arrayOfFullDays[tempo.get(Calendar.DAY_OF_WEEK) - 1];
		AB_MONTHS that_month = arrayOfABMonths[tempo.get(Calendar.MONTH)];
		System.out.println(that_day + ", " + that_month + " " + this_day + ", " + this_year);
		for (Event current : list_of_month_events) {
			if (current.getDay() == (this_day)) {
				String print = current.getTitle() + " " + current.getStarttime() + " - " + current.getEndtime();
				System.out.println(print);
			}
		}

	}

	/**
	 * Prints out highlighted calendar of today or the events
	 * @param row_column determines how many days printed in a month
	 * @return the string of the formatted calendar
	 */
	public String toString(int[][] row_column) {
		String visual = "";
		for (int x = 0; x < row; x++) {
			for (int y = 0; y < col; y++) {
				if (row_column[x][y] < 10)
					visual += " ";
				if (list_of_days.contains(row_column[x][y]) && current_month == cal.get(Calendar.MONTH)
						&& list_of_month_events.size() == 0) {
					visual += "[" + row_column[x][y] + "]" + " ";
				} else if (list_of_days.contains(row_column[x][y])) {
					visual += "{" + row_column[x][y] + "}" + " ";
					;
				} else {
					if (row_column[x][y] != 0)
						visual += row_column[x][y] + " ";
					else {
						visual += "  ";
					}
				}
			}
			visual += "\n";
		}
		return "\n" + visual;
	}

}
