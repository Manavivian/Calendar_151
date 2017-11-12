
import java.io.IOException;
import java.util.Scanner;

/**
 * This creates the main menu and allows the user to interact with various
 * options
 * 
 * @author Vivian Hoang
 *
 */
public class MyCalendarTester {
	public static void main(String[] args) throws IOException {
		Scanner user_input = new Scanner(System.in);
		Calendar_System calendar = new Calendar_System();
		String decision;
		do {
			calendar.Message_Screen();
			decision = user_input.next();
			if (decision.contains("L"))
				calendar.Load();
			else if (decision.contains("V")) {
				System.out.println("[D]ay view or [M]view");
				calendar.View(decision = user_input.next());
			} else if (decision.contains("C")) {
				System.out.println("Put in title of event: ");
				String title = user_input.next() + user_input.nextLine();
				String date = "";
				do {
					System.out.println("Enter the date (MM/DD/YYYY): ");
					date = user_input.next();
				} while (qualifiedDate(date) == false);
				System.out.println("Type out times according to this format (HH:MM) please");
				String starttime = "";
				do {
					System.out.println("Select start time: ");
					starttime = user_input.next();
				} while (qualifiedstarttime(starttime) == false);
				String endtime = "";
				do {
					System.out.println("Select a legal endtime,if no endtime type [n/a]: ");
					endtime = user_input.next();
				} while (qualifiedendtime(starttime, endtime) == false);
				calendar.Create(title, date, starttime, endtime);
			} else if (decision.contains("G")) {
				do {
					System.out.println("Enter a date (MM/DD/YYYY): ");
					decision = user_input.next();
				} while (qualifiedDate(decision) == false);
				calendar.Go_to(decision);
			} else if (decision.contains("E"))
				calendar.Event_list();
			else if (decision.contains("D")) {
				System.out.println("[S]elected or [A]ll events to be deleted?");
				calendar.Delete(decision = user_input.next());
			}
		} while (!decision.contains("Q"));
		user_input.close();
		calendar.Quit();
	}

	/**
	 * Determines if the start time follows the format given (HH:MM)
	 * @param starttime
	 * @return
	 */
	public static boolean qualifiedstarttime(String starttime) {
		boolean isTrue = true;
		if (starttime.length() < 5) {
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
		return isTrue;
	}

	/**
	 * Determines if the end time makes sense such as not ending before the start time
	 * 
	 * @param starttime
	 * @param endtime
	 * @return true or false
	 */
	public static boolean qualifiedendtime(String starttime, String endtime) {
		boolean isTrue = true;
		if (endtime.equals("n/a") == true)
			return isTrue;
		if (endtime.length() < 5) {
			return false;
		} else if (endtime.length() > 5) {
			return false;
		} else if (endtime.length() == 10) {
			for (int i = 0; i < starttime.length(); i++) {
				if (i != 2 || i != 5) {
					isTrue = Character.isLetter(starttime.charAt(i));
					if (isTrue == false)
						return isTrue;
				}
			}
		}
		
		if (Integer.parseInt(starttime.substring(0, 2)) > Integer.parseInt(endtime.substring(0, 2))
				&& Integer.parseInt(starttime.substring(3, 4)) > Integer.parseInt(endtime.substring(3, 4)) && Integer.parseInt(endtime.substring(0, 2)) > 24) {
			return false;
		}
		return true;
	}

	/**
	 * Determines if the user typed a legal date MM/DD/YYYY
	 * 
	 * @param date
	 * @return boolean of true or false
	 */
	public static boolean qualifiedDate(String date) {
		boolean isTrue = true;
		if (date.length() < 10) {
			return false;
		} else if (date.length() > 10) {
			return false;
		} else if (date.length() == 10) {
			for (int i = 0; i < date.length(); i++) {
				if (i != 2 || i != 5) {
					isTrue = Character.isLetter(date.charAt(i));
					if (isTrue == true)
						return false;
				}
			}
		}
		return true;
	}

}
