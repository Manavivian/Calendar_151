
import java.util.ArrayList;

import java.util.TreeSet;
/**
 * This organizes the events
 * @author Vivian Hoang
 *
 */
public class EventList extends TreeSet<Event> {
	/**
	 * Organizes the entire Event list
	 * @return
	 */
	public ArrayList<Event> getSorted() {
		ArrayList<Event> sorted_list = new ArrayList<Event>(this);
		return sorted_list;
	}
}
