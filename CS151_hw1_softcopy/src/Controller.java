import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Controller {
	private EventModel model;

	/**
	 * Initializes the Controller class
	 * 
	 * @param model
	 */
	public Controller(EventModel model) {
		this.model = model;
	}

	/**
	 * This will allow the the Add button in View to be clicked and will save
	 * the the text from the View to be saved into the TextModel
	 * 
	 * @param v
	 */
	public void attach(View main) {
		main.getCreateButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFrame create_window = new JFrame("Create Event");
				JPanel time = new JPanel();
				
				TextField name_of_event = new TextField("Untitled Event");
				TextField current_date = new TextField("Current Date");
				current_date.setEditable(false);
				TextField starting_time = new TextField();
				TextField ending_time = new TextField();
				
				JButton save = new JButton("Save");
				save.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if((name_of_event.getText() != null) && (starting_time.getText() != null))
							model.add(name_of_event.getText(), date, starting_time.getText(), ending_time.getText());
						create_window.dispose();
					}
					
				});

				time.add(current_date);
				time.add(starting_time);
				time.add(ending_time);
				time.add(save);
				
				create_window.add(name_of_event, BorderLayout.NORTH);
				create_window.add(time, BorderLayout.CENTER);
							
				create_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				create_window.pack();
				create_window.setVisible(true);
			}
		});
		main.getForwardButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		main.getPreviousButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		main.getQuitButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					main.getCalendar().Quit();
				} catch (IOException ok) {
					System.out.println("Location: Quit: "+ ok);
				}
				main.getMainFrame().dispose();
			}
		});
	}
}
