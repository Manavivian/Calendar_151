import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * This kees track of the data and notifies the change in View
 * @author Vivian Hoang
 *
 */
public class EventModel {
	
	private Calendar_System calendar;
	private ArrayList<ChangeListener> listeners;
	
	/**
	 * This will grab the calendar from the View to modify the information
	 * @param stuff
	 */
	public EventModel(View stuff){
		calendar = stuff.getCalendar();
		listeners = new ArrayList<ChangeListener>();
	}
	
	/**
	 * Attaching and notifying the View
	 * @param c
	 */
	public void attach(ChangeListener c){
		listeners.add(c);
	}
	
	/**
	 * This will add the event to the view
	 * @param event
	 * @param date
	 * @param start
	 * @param end
	 */
	public void add(String event, String date, String start, String end){
		calendar.Create(event, date, start, end);
		for (ChangeListener l : listeners)
			l.stateChanged(new ChangeEvent(this));
	}
	
	/**
	 * The view will show the next day event
	 */
	public void nextDay(){
		calendar.forwardDay();
		for (ChangeListener l : listeners)
			l.stateChanged(new ChangeEvent(this));
	}
	
	/**
	 * This will notify the view to show the previous day
	 */
	public void previousDay(){
		calendar.previousDay();
		for (ChangeListener l : listeners)
			l.stateChanged(new ChangeEvent(this));
	}
	
	
	/**
	 * This will notify the view to show the next month
	 */
	public void nextMonth(){
		calendar.getMonth(1);
		for (ChangeListener l : listeners)
			l.stateChanged(new ChangeEvent(this));

	}
	
	/**
	 * This will notify the view to show the previous month
	 */
	public void previousMonth(){
		calendar.getMonth(-1);		
		for (ChangeListener l : listeners)
			l.stateChanged(new ChangeEvent(this));
	}
	
	/**
	 * This will notify the view to go to the navigated day
	 * @param month
	 * @param day
	 */
	public void go_To(int month, int day){
		calendar.Go_to(month,day);
		for(ChangeListener l: listeners)
			l.stateChanged(new ChangeEvent(this));
	}
	
	/**
	 * This will delete all the events for the current day
	 * @param date
	 */
	public void Delete(String date){
		calendar.Delete(date);
		for(ChangeListener l: listeners)
			l.stateChanged(new ChangeEvent(this));
	}
	
	/**
	 * Will get the monthdate for the controller function
	 * @return
	 */
	public int getMonthDate(){
		return calendar.getMonth();
	}
	
	/**
	 * Gives the current date
	 * @return
	 */
	public String currentDate(){
		String current = calendar.getMonth()+ "/" + calendar.getDay()+"/"+calendar.getYear();
		return current;
	}
	
	/**
	 * Gives the event data
	 * @return
	 */
	public EventList getData(){
		EventList events = calendar.getEvents();
		return events;
	}
	
	/**
	 * determine if the date is qualifiable
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	public boolean qualifiedTime(String starttime, String endtime){
		return calendar.qualifiedtime(starttime,endtime);
	}
	

	/**
	 * This gets the calendar from the view
	 * @return
	 */
	public Calendar_System getCalendar(){
		return calendar;
	}
	
}
