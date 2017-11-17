import java.io.IOException;

public class MVCTester {

	public static void main(String[] args) throws IOException {
		View Cal = new View();
		EventModel events = new EventModel(Cal);
		Controller connect = new Controller(events);
		events.attach(Cal);
		connect.attach(Cal);

	}

}
