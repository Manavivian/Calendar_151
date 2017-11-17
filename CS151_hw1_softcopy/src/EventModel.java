import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EventModel {
	
	private Calendar_System calendar;
	private ArrayList<ChangeListener> listeners;
	
	public EventModel(View stuff){
		calendar = stuff.getCalendar();
		listeners = new ArrayList<ChangeListener>();
	}
	
	public void attach(ChangeListener c){
		listeners.add(c);
	}
	
	public void add(String event, String date, String start, String end){
		calendar.Create(event, date, start, end);
		for (ChangeListener l : listeners)
			l.stateChanged(new ChangeEvent(this));
	}
	
	public EventList getData(){
		return calendar.getEvents();
	}
	
	public String currentDate(){
		return calendar.getDate();
	}
	
}
