
import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class View {
	public static void main(String[] args) {
		//Hierarchies
		JFrame frame = new JFrame("Calendar");
		JPanel previousbutton = new JPanel(new BorderLayout());
		JPanel forwardbutton = new JPanel(new BorderLayout());
		JPanel quitbutton = new JPanel(new BorderLayout());
		JPanel createbutton = new JPanel(new BorderLayout());
		JPanel all_buttons = new JPanel();
		
		//Buttons for the locations
		JButton quit = new JButton("QUIT");
		JButton create = new JButton("Create");
		JButton previous = new JButton("<");
		JButton forward = new JButton(">");

		//Adding into the panels
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
