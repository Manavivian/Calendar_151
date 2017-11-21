import java.io.IOException;
/**
 * This is the main class tester of the GUI calendar
 * @author Vivian Hoang
 *
 */
public class SimpleCalendar {

	public static void main(String[] args) throws IOException {
		View Cal = new View();
		EventModel events = new EventModel(Cal);
		Controller connect = new Controller(events);
		events.attach(Cal);
		connect.attach(Cal);
	}

}
