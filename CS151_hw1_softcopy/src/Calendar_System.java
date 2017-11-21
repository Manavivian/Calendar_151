
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * This the control center of the other classes and all the information is
 * accessible here
 * 
 * @author Vivian Hoang
 *
 */
public class Calendar_System {
	private EventList events;
	private Calendars visual, initial_screen;
	private Scanner choice = new Scanner(System.in);

	/**
	 * Initializes the starting screen and menu
	 */
	public Calendar_System() {
		events = new EventList();
		visual = new Calendars(events);
		initial_screen = new Calendars(null);
	}

	/**
	 * The menu options
	 */
	public void Message_Screen() {
		initial_screen.get_Month_Calendar_Event(0);
	}

	/**
	 * Will update events from old file and add onto current events, however if
	 * no file is available it will state a message stating this is the first
	 * run
	 */
	public void Load() throws IOException {
		File e = new File("event.txt");
		boolean exists = e.exists();
		if (exists == true) {
			try {
				ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("event.txt"));
				int events_to_read = (int) objectInputStream.readObject();
				while (events_to_read > 0) {
					Event current = (Event) objectInputStream.readObject();
					events.add(current);
					events_to_read--;
				}
				objectInputStream.close();
			} catch (ClassNotFoundException y) {
				System.out.println("File not found");
			}
		} else {
			System.out.println(
					"Events could not be loaded because this is the first run, please add events then quit to load next time.");
			System.out.println("Press any key to continue.");
			choice.nextLine();
		}
	}

	/**
	 * Gets the day
	 * @return
	 */
	public int getDay() {
		return visual.getDay();
	}

	/**
	 * Gets the current month
	 * @return
	 */
	public int getMonth() {
		return visual.getMonth();
	}

	/**
	 * Moves the date forward in calendar
	 */
	public void forwardDay() {
		visual.getDayView(1, events);
	}

	/**
	 * Moves the date backward in calendar
	 */
	public void previousDay() {
		visual.getDayView(-1, events);
	}

	/**
	 * Gives the year
	 * @return
	 */
	public String getYear() {
		return String.valueOf(visual.getYear());
	}

	/**
	 * Returns the single arrays of avaiable days
	 * @param offset
	 * @return
	 */
	public int[] getMonth(int offset) {
		int[][] convert = visual.get_Month_Calendar_Event(offset);
		int[] days = new int[42];
		for (int x = 0, z = 0; x < 6; x++) {
			for (int y = 0; y < 7; y++) {
				days[z] = convert[x][y];
				z++;
			}
		}

		return days;
	}

	/**
	 * Gets the current name of the current month
	 * @param offset
	 * @return
	 */
	public String getNameofMonth(int offset) {
		return visual.getMonthName(offset);
	}

	/**
	 * Gets all of the day names
	 * @return
	 */
	public String[] getDayNames() {
		return visual.getDayName();
	}

	/**
	 * Gets all the name of days
	 * @return
	 */
	public String getNameofDay() {
		return visual.getCurrentDay();
	}

	/**
	 * Makes an event
	 * 
	 * @param title
	 *            description and name of the event
	 * @param date
	 *            the MM/DD/YYYY taken place
	 * @param starttime
	 *            the starting hours of the event
	 * @param endtime
	 *            the ending hours of the event
	 */
	public void Create(String title, String date, String starttime, String endtime) {
		Event pending_new_event = new Event(title, date, starttime, endtime);
		events.add(pending_new_event);
	}

	/**
	 * Goes to a specific date and will load the event
	 */
	public String Go_to(String input) {
		visual.passEvents(Integer.parseInt(input.substring(0, 2)), events);
		return visual.getDayView(input);
	}

	/**
	 * Goes to a specific date and will load the event
	 */
	public void Go_to(int month, int day) {
		visual.passEvents(month, events);
		visual.getDayView(day);
	}

	public EventList Go_to(int day) {
		return visual.passTodayEvents(day, events);
	}

	/**
	 * This the list of events printed according to earliest to latest
	 */
	public void Event_list() {
		int current_year = 0;
		int prev_year = 0;
		for (Event print : events.getSorted()) {
			current_year = print.getYear();
			if (current_year != prev_year) {
				System.out.println(print.getYear());
				prev_year = print.getYear();
			}
			//System.out.println(print);
		}
	}

	/**
	 * This will remove an event from the list
	 * 
	 * @param decision
	 *            this is user input
	 */
	public void Delete(String date) {
		String[] dates = new String[3];
		int total = date.length();
		int start = 0;
		int count = 0;
		for (int i = 0; i < total; i++) {
			if (date.charAt(i) == ('/')) {
				dates[count] = date.substring(start, i);
				start = i+1;
				count++;
			} else if(i == total-1){
				dates[count]=date.substring(start,i+1);
			}
		}
		String month = dates[0];
		String day = dates[1];
		if(month.length() == 1)
			month = "0"+dates[0];
		if(day.length() ==1)
			day = "0" + dates[1];
		String year = dates[2];
		String thedate = month+"/"+day+"/"+year;
		for (Event it : events.getSorted()) {
				if (it.getDate().equals(thedate))
					events.remove(it);
		}
	}
	

	/**
	 * This will save all the events into a txt file and leave while closing the
	 * streams
	 * 
	 * @throws IOException
	 */
	public void Quit() throws IOException {
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("event.txt"));
		objectOutputStream.writeObject(events.size());
		for (Event m : events.getSorted())
			objectOutputStream.writeObject(m);
		objectOutputStream.close();

	}

	public EventList getEvents() {
		return events;
	}

	/**
	 * Determines if the start time follows the format given (HH:MM)
	 * 
	 * @param starttime
	 * @return
	 */
	public boolean qualifiedtime(String starttime, String endtime) {
		boolean isTrue = true;
		if (starttime.equals("HH:MM")) {
			return false;
		} else if (starttime.length() < 5) {
			return false;
		} else if (starttime.length() > 5) {
			return false;
		} else if (starttime.length() == 10) {
			for (int i = 0; i < starttime.length(); i++) {
				if (i != 2 || i != 5) {
					isTrue = Character.isLetter(starttime.charAt(i));
					if (isTrue == false)
						return false;
				}
			}
		}
		if (Integer.parseInt(starttime.substring(0, 2)) > 24)
			return false;

		isTrue = visual.getRange(starttime, endtime, events);

		return isTrue;
	}

	
}
