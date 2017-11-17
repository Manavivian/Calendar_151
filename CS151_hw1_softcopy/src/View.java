
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.IOException;

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
	private JButton fmonth;
	private JButton pmonth;
	
	Calendar_System calendar;
	private JFrame frame;
	private JTextField fields[];
	private EventList events;
	private EventModel m;
	private JButton[] buttons;
	private JTextField currentdate;
	private String date,day;
	private int currentday;
	
	public View() throws IOException {
		calendar = new Calendar_System();
		calendar.Load();
		events = calendar.getEvents();
		day = calendar.getNameofDay();
		date = day + " " + calendar.getDate().substring(0,5);
		currentday= Integer.parseInt(calendar.getDate().substring(3,5));
		
		// Hierarchies
		frame = new JFrame("Calendar");
		JPanel quitbutton = new JPanel(new BorderLayout());
		JPanel createbutton = new JPanel(new BorderLayout());
		JPanel all_buttons = new JPanel();
		JPanel textfield = new JPanel(new BorderLayout());
		JPanel days = new JPanel(new BorderLayout());
		JPanel nestmonth = new JPanel(new BorderLayout());
		JPanel allnextprev_buttons = new JPanel(new GridLayout(1,4));

		// Buttons for the locations
		quit = new JButton("Quit");
		create = new JButton("Create");
		previous = new JButton("<");
		forward = new JButton(">");
		fmonth = new JButton(">>");
		pmonth = new JButton("<<");
		
		

		// Buttons for the days of the month
		int[] has_days = calendar.getMonth(0);
		buttons = new JButton[31];
		for (int i = 0; i < 31; i++) {
			buttons[i] = new JButton(String.valueOf(i + 1));
		}
		
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 7));
		for (int i = 0, x = 0; i < 42; i++) {
			if (has_days[i] != 0) {
				panel.add(buttons[x]);
				x++;
			} else {
				panel.add(new JPanel());
			}
		}
		buttons[currentday-1].setBackground(Color.LIGHT_GRAY);
		buttons[currentday-1].setEnabled(false);
		
		
		// Text field and area
		currentdate = new JTextField(date);
		fields = new JTextField[7];
		JPanel nameofday = new JPanel();
		nameofday.setLayout(new BoxLayout(nameofday, BoxLayout.X_AXIS));
		String[] names = calendar.getDayNames();
		int x =0;
		for(JTextField current: fields){
			current= new JTextField(names[x]);
			nameofday.add(current);
			x++;
			current.setEnabled(false);
		}
		JTextArea viewevents = new JTextArea();
		JTextField month = new JTextField(calendar.getNameofMonth(0));
		viewevents.setEnabled(true);
		month.setEnabled(false);		
		
		
		
		// Adding into the panels	
		days.add(nameofday,BorderLayout.NORTH);
		days.add(panel,BorderLayout.CENTER);
		nestmonth.add(month,BorderLayout.NORTH);
		nestmonth.add(days,BorderLayout.CENTER);
		textfield.add(currentdate, BorderLayout.NORTH);
		textfield.add(viewevents, BorderLayout.CENTER);
		allnextprev_buttons.add(pmonth);
		allnextprev_buttons.add(previous);
		allnextprev_buttons.add(forward);
		allnextprev_buttons.add(fmonth);
		
		quitbutton.add(quit, BorderLayout.EAST);
		createbutton.add(create, BorderLayout.WEST);

		all_buttons.setLayout(new BoxLayout(all_buttons, BoxLayout.X_AXIS));
		all_buttons.add(createbutton);
		all_buttons.add(allnextprev_buttons);
		all_buttons.add(quitbutton);

		// Adding the panels into the frame
		frame.add(nestmonth,BorderLayout.WEST);
		frame.add(all_buttons, BorderLayout.NORTH);
		frame.add(textfield,BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public void stateChanged(ChangeEvent e) {
		buttons[currentday-1].setBackground(null);
		buttons[currentday-1].setEnabled(true);
		//events = m.getData();
		day = calendar.getNameofDay();
		date = day + " " + calendar.getDate().substring(0,5);
		currentday = Integer.parseInt(calendar.getDate().substring(3,5));
		buttons[currentday-1].setBackground(Color.LIGHT_GRAY);
		buttons[currentday-1].setEnabled(false);
	}

	/**
	 * 
	 * @return
	 */
	public JButton[] getDays(){
		return buttons;
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

	public JButton getForwardMonthButton(){
		return fmonth;
	}
	
	public JButton getPreviousMonthButton(){
		return pmonth;
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
