package schedulingsimulator;

import java.util.Arrays;

public class EventsQueue {
	
	private OrderedPriorityQueue<Event> eventsQueue;
	
	public EventsQueue() {
		this.eventsQueue = new OrderedPriorityQueue<Event>();
	}
	
	public boolean add(Event event) {
		//só permite um SCHED para o mesmo tempo
		if ( (event.getType() == Event.Type.SCHED) && (this.eventsQueue.contains(event)) ) {
			return false;
		}		
		return this.eventsQueue.add(event);
	}
	
	//DEBUG
	public void println() {
		System.out.println(Arrays.toString(this.eventsQueue.toArray()));
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
