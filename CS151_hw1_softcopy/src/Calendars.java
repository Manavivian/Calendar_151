
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;

/**
 * This gives the user the visual calendars on the initial screen and the
 * day/month screen to determine events and what day is today
 * 
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
	 * 
	 * @param report
	 *            the event list for the calendar
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
	 * Returns the day
	 * @return
	 */
	public int getDay() {
		return current_day;
	}

	/**
	 * Returns the current month
	 * @return
	 */
	public int getMonth() {
		return current_month + 1;
	}

	/**
	 * Returns the current year
	 * @return
	 */
	public int getYear() {
		return current_year;
	}

	/**
	 * This will only grab the events of the current month the user is looking
	 * at
	 * 
	 * @param events
	 *            events for current months
	 * @param n
	 *            is used to determine the month for the event
	 */
	public void passEvents(EventList events, int n) {
		for (Event today : events) {
			if (today.getMonth() == (current_month + 1 + n) && today.getYear() == current_year) {
				list_of_month_events.add(today);
				list_of_days.add(today.getDay());
			}
		}
	}

	/**
	 * Grab events for the Go_To function
	 * 
	 * @param month
	 * @param events
	 */
	public void passEvents(int month, EventList events) {
		for (Event today : events) {
			if (today.getMonth() == month && today.getYear() == current_year) {
				list_of_month_events.add(today);
				list_of_days.add(today.getDay());
			}
		}
	}

	/**
	 * This passes all the events for the current day
	 * @param day
	 * @param events
	 * @return
	 */
	public EventList passTodayEvents(int day, EventList events) {
		EventList todayact = new EventList();
		for (Event today : events) {
			if (today.getDay() == current_day && today.getMonth() == current_month + 1
					&& today.getYear() == current_year) {
				list_of_month_events.add(today);
				todayact.add(today);
				list_of_days.add(today.getDay());
			}
		}
		return todayact;
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
	 * 
	 * @param prevornext
	 *            determines if user what's the next or previous month
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
		return row_column;
	}

	/**
	 * Gets the name of the current month
	 * 
	 * @param prevornext
	 * @return
	 */
	public String getMonthName(int prevornext) {
		current_month += prevornext;
		if (current_month == 12) {
			current_month = 0;
			current_year++;
		} else if (current_month == -1) {
			current_month = 11;
			current_year--;
		}
		String currentmonthyear = String.valueOf(arrayOfMonths[current_month]) + " " + current_year;
		return currentmonthyear;
	}

	public String[] getDayName() {
		String[] days = new String[7];
		int x = 0;
		for (DAYS day : arrayOfDays) {
			days[x] = String.valueOf(day);
			x++;
		}
		return days;
	}

	/**
	 * Gets the daily events if any
	 * 
	 * @param prevornext
	 *            user interacts with this class to see next or previous day
	 */
	public int getDayView(int prevornext, EventList event) {
		int totaldays = cal.getActualMaximum(Calendar.DATE);
		current_day += prevornext;
		if (current_day > totaldays) {
			current_day = 1;
			current_month++;
			if (current_month == 12) {
				current_month = 0;
				current_year++;
				passEvents(event, 1);
			}
		} else if (current_day < 1) {
			current_month--;
			GregorianCalendar tempo = new GregorianCalendar(current_year, current_month, 1);
			totaldays = tempo.getActualMaximum(Calendar.DATE);
			current_day = totaldays;
			if (current_month == -1) {
				current_month = 11;
				current_year--;
				passEvents(event, -1);
			}
		}
		return current_day;
	}

	/**
	 * Gets the DayView for the Go_To method
	 * 
	 * @param date
	 *            allows user to navigate straight to the day
	 */
	public String getDayView(String date) {
		int this_month = Integer.parseInt(date.substring(0, 2));
		int this_day = Integer.parseInt(date.substring(3, 5));
		int this_year = Integer.parseInt(date.substring(6, 10));
		GregorianCalendar tempo = new GregorianCalendar(this_year, this_month - 1, this_day);
		FULL_DAYS that_day = arrayOfFullDays[tempo.get(Calendar.DAY_OF_WEEK) - 1];
		AB_MONTHS that_month = arrayOfABMonths[tempo.get(Calendar.MONTH)];
		System.out.println(that_day + ", " + that_month + " " + this_day + ", " + this_year);
		String print = "";
		for (Event current : list_of_month_events) {
			if (current.getDay() == (this_day)) {
				print = current.getTitle() + " " + current.getStarttime() + " - " + current.getEndtime();
				System.out.println(print);
			}
		}
		return print;
	}

	/**
	 * Determines if the time are within reasonable range
	 * @param starttime
	 * @param endtime
	 * @param events
	 * @return
	 */
	public boolean getRange(String starttime, String endtime, EventList events) {
		EventList todayact = new EventList();
		for (Event today : events) {
			if (today.getDay() == current_day && today.getMonth() == current_month + 1
					&& today.getYear() == current_year) {
				list_of_month_events.add(today);
				todayact.add(today);
				list_of_days.add(today.getDay());
			}
		}
		int start =0;
		int end = 0;
		if (endtime.equals("HH:MM")) {
			int start_min = Integer.valueOf(starttime.substring(3, 5));
			int start_hr = Integer.valueOf(starttime.substring(0, 2));
			start = (60*start_hr) + start_min;
			end = (60 * 24);
		} else {
			int start_min = Integer.valueOf(starttime.substring(3, 5));
			int start_hr = Integer.valueOf(starttime.substring(0, 2));
			int end_min = Integer.valueOf(endtime.substring(3, 5));
			int end_hr = Integer.valueOf(endtime.substring(0, 2));
			start = (60 * start_hr) + start_min;
			end = (60 * end_hr) + end_min;
		}

		for (Event today : todayact) {
			int startmin = Integer.valueOf(today.getStarttime().substring(3, 5));
			int starthr = Integer.valueOf(today.getStarttime().substring(0, 2));
			int endmin = Integer.valueOf(today.getEndtime().substring(3, 5));
			int endhr = Integer.valueOf(today.getEndtime().substring(0, 2));
			int starting = (60 * starthr) + startmin;
			int ending = (60 * endhr) + endmin;
			if ((start >= starting && start <= ending) || (start <= starting && end <= ending && end >= starting))
				return false;
		}
		return true;
	}

	/**
	 * Gets the DayView for the Go_To method
	 * 
	 * @param date
	 *            allows user to navigate straight to the day
	 */
	public void getDayView(int day) {
		current_day = day;
	}

	/**
	 * Returns the name of the current day
	 * 
	 * @return
	 */
	public String getCurrentDay() {
		GregorianCalendar tempo = new GregorianCalendar(current_year, current_month, current_day);
		FULL_DAYS that_day = arrayOfFullDays[tempo.get(Calendar.DAY_OF_WEEK) - 1];
		return String.valueOf(that_day);
	}

	/**
	 * Returns the entire date
	 * 
	 * @return
	 */
	public String getCurrent() {
		String date = (current_month + 1) + "/" + current_day + "/" + current_year;
		return date;
	}

	/**
	 * Prints out highlighted calendar of today or the events
	 * 
	 * @param row_column
	 *            determines how many days printed in a month
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
