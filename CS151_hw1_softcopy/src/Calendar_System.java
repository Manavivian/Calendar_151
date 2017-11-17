
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
	private String message = "Select one of the following options: ";
	private String options = "[L]oad  [V]iew by  [C]reate,  [G]o to  [E]vent list  [D]elete  [Q]uit";
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
		System.out.println(message);
		System.out.println(options);
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
	 * Allows user to see a visual calendar either in Month or Day View
	 * 
	 * @param decision
	 *            this is user's input
	 */
	public void View(String decision) {
		if (events.isEmpty() == false) {
			visual.passEvents(events, 0);
		} else {
			visual.resetEvents();
		}
		if (decision.contains("D")) {
			visual.getDayView(0, events);
			do {
				System.out.println("[P]revious or [N]ext or [M]ain menu?");
				decision = choice.next();
				if (decision.contains("P")) {
					visual.getDayView(-1, events);
				} else if (decision.contains("N")) {
					visual.getDayView(1, events);
				} else if (decision.contains("M")) {
				}
			} while (!decision.contains("M"));
		} else if (decision.contains("M")) {
			visual.get_Month_Calendar_Event(0);
			do {
				System.out.println("[P]revious or [N]ext or [M]ain menu?");
				decision = choice.next();
				if (decision.contains("P")) {
					visual.resetEvents();
					visual.passEvents(events, -1);
					visual.get_Month_Calendar_Event(-1);

				} else if (decision.contains("N")) {
					visual.resetEvents();
					visual.passEvents(events, 1);
					visual.get_Month_Calendar_Event(1);

				}
			} while (!decision.contains("M"));
			visual.reset();
		}
	}

	public String getDate(){
		return visual.getCurrent();
	}
	
	public int[] getMonth(int offset) {
		int[][] convert = visual.get_Month_Calendar_Event(offset);
		int[] days = new int[42];
		for (int x = 0, z = 0; x < 6; x++) {
			for (int y = 0; y < 7; y++) {
				days[z]=convert[x][y];
				z++;
			}
		}

		return days;
	}
	
	public String getNameofMonth(int offset){
		return visual.getMonthName(offset);
	}
	
	public String[] getDayName(){
		return visual.getDayName();
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
			System.out.println(print);
		}
	}

	/**
	 * This will remove an event from the list
	 * 
	 * @param decision
	 *            this is user input
	 */
	public void Delete(String decision) {
		String comparison = null;
		if (decision.contains("S")) {
			do {
				System.out.println("Enter the date (MM/DD/YYYY): ");
				comparison = choice.next();
			} while (MyCalendarTester.qualifiedDate(comparison) == false);
			for (Event it : events.getSorted()) {
				if (it.getDate().equals(comparison))
					events.remove(it);
			}

		} else if (decision.contains("A")) {
			events = new EventList();
			visual.reset();
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

}
