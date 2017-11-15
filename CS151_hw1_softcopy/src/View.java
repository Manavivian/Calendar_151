
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class View {
	public static void main(String[] args) {
		Calendar_System calendar = new Calendar_System();
		//Hierarchies
		JFrame frame = new JFrame("Calendar");
		JPanel previousbutton = new JPanel(new BorderLayout());
		JPanel forwardbutton = new JPanel(new BorderLayout());
		JPanel quitbutton = new JPanel(new BorderLayout());
		JPanel createbutton = new JPanel(new BorderLayout());
		JPanel all_buttons = new JPanel();
		JPanel textfield = new JPanel(new BorderLayout());
		
		//Buttons for the locations
		JButton quit = new JButton("QUIT");
		quit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					calendar.Quit();
				} catch (IOException e) {
					System.out.println("Location: Quit: "+e);
				}
				frame.dispose();
			}
		});
		JButton create = new JButton("Create");
		JButton previous = new JButton("<");
		JButton forward = new JButton(">");
		
		//Text field
		JTextArea viewevents = new JTextArea();
		JScrollBar scroll = new JScrollBar(JScrollBar.VERTICAL);
		viewevents.setEnabled(true);
		
		

		//Adding into the panels
		textfield.add(viewevents,BorderLayout.CENTER);
		textfield.add(scroll,BorderLayout.EAST);
		previousbutton.add(previous,BorderLayout.EAST);
		forwardbutton.add(forward,BorderLayout.WEST);
		quitbutton.add(quit,BorderLayout.CENTER);
		createbutton.add(create, BorderLayout.WEST);
		
		all_buttons.setLayout(new BoxLayout(all_buttons, BoxLayout.X_AXIS));
		all_buttons.add(createbutton);
		all_buttons.add(previousbutton);
		all_buttons.add(forwardbutton);
		all_buttons.add(quitbutton);
		
		//Adding the panels into the frame
		frame.add(all_buttons,BorderLayout.NORTH);
		frame.add(textfield,BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
