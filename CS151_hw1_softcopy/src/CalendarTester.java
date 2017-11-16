
public class CalendarTester {

	public static void main(String[] args) {
		View Cal = new View();
		EventModel events = new EventModel();
		Controller connect = new Controller(events);
		connect.attach(Cal);

	}

}
