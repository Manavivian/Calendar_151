
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class View implements ChangeListener {
	private JButton quit;
	private JButton create;
	private JButton forward;
	private JButton previous;
	Calendar_System calendar;
	private JFrame frame;
	private JTextField fields;
	private EventList events;
	private EventModel m;

	public View() {
		calendar = new Calendar_System();
		events = calendar.getEvents();
		// Hierarchies
		frame = new JFrame("Calendar");
		JPanel previousbutton = new JPanel(new BorderLayout());
		JPanel forwardbutton = new JPanel(new BorderLayout());
		JPanel quitbutton = new JPanel(new BorderLayout());
		JPanel createbutton = new JPanel(new BorderLayout());
		JPanel all_buttons = new JPanel();
		JPanel textfield = new JPanel(new BorderLayout());

		// Buttons for the locations
		quit = new JButton("Quit");
		create = new JButton("Create");
		previous = new JButton("<");
		forward = new JButton(">");

		// Text field and area
		JTextArea viewevents = new JTextArea();
		viewevents.setEnabled(true);

		// Adding into the panels
		textfield.add(viewevents, BorderLayout.CENTER);
		previousbutton.add(previous, BorderLayout.EAST);
		forwardbutton.add(forward, BorderLayout.WEST);
		quitbutton.add(quit, BorderLayout.CENTER);
		createbutton.add(create, BorderLayout.WEST);

		all_buttons.setLayout(new BoxLayout(all_buttons, BoxLayout.X_AXIS));
		all_buttons.add(createbutton);
		all_buttons.add(previousbutton);
		all_buttons.add(forwardbutton);
		all_buttons.add(quitbutton);

		// Adding the panels into the frame
		frame.add(all_buttons, BorderLayout.NORTH);
		// frame.add(textfield,BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		events = m.getData();

	}

	/**
	 * Gets the Quit button
	 * 
	 * @return
	 */
	public JButton getQuitButton() {
		return quit;
	}

	/**
	 * Returns the Forward Button
	 * 
	 * @return
	 */
	public JButton getForwardButton() {
		return forward;
	}

	/**
	 * Returs the previous button
	 * 
	 * @return
	 */
	public JButton getPreviousButton() {
		return previous;
	}

	/**
	 * Returns the create button
	 * 
	 * @return
	 */
	public JButton getCreateButton() {
		return create;
	}

	/**
	 * Returns the Calendar System
	 * 
	 * @return
	 */
	public Calendar_System getCalendar() {
		return calendar;
	}

	/**
	 * Returns the main window
	 * 
	 * @return
	 */
	public JFrame getMainFrame() {
		return frame;
	}
}
