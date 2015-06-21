package schedulingsimulator;

import java.util.PriorityQueue;

public class EventsQueue {
	
	private PriorityQueue<Event> eventsQueue;
	
	public EventsQueue() {
		this.eventsQueue = new PriorityQueue<Event>();
	}
	
	public boolean add(Event event) {
		//só permite um SCHED para o mesmo tempo
		if ( (event.getType() == Event.Type.SCHED) && (this.eventsQueue.contains(event)) ) { 
			System.out.println("DEBUG: new SCHED addition correctly ignored. :)");
			return false;
		}		
		return this.eventsQueue.add(event);
	}
	
	/**
	 * Checks if there is an event in the Events Queue.
	 * @return true if there is at least one event in the Events
	 * Queue, false otherwise.
	 */
	public boolean hasNext() {
		return !this.eventsQueue.isEmpty();
	}
	
	public Event next() {
		return this.eventsQueue.poll();
	}
	
	
	public void remove(Event event) {
		this.eventsQueue.remove(event);
	}
}
