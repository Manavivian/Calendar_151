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
	
	public void nextDay(){
		calendar.forwardDay();
		for (ChangeListener l : listeners)
			l.stateChanged(new ChangeEvent(this));
	}
	
	public void previousDay(){
		calendar.previousDay();
		for (ChangeListener l : listeners)
			l.stateChanged(new ChangeEvent(this));
	}
	
	public void nextMonth(){
		calendar.getMonth(1);
		for (ChangeListener l : listeners)
			l.stateChanged(new ChangeEvent(this));

	}
	
	public void previousMonth(){
		calendar.getMonth(-1);		
		for (ChangeListener l : listeners)
			l.stateChanged(new ChangeEvent(this));
	}
	
	public void go_To(int month, int day){
		calendar.Go_to(month,day);
		for(ChangeListener l: listeners)
			l.stateChanged(new ChangeEvent(this));
	}
	
	public int getMonthDate(){
		return calendar.getMonth();
	}
	
	public String currentDate(){
		String current = calendar.getMonth()+ "/" + calendar.getDay()+"/"+calendar.getYear();
		return current;
	}
	
	public EventList getData(){
		EventList events = calendar.getEvents();
		return events;
	}
	
	
	public Calendar_System getCalendar(){
		return calendar;
	}
	
}
