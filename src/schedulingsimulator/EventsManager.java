package schedulingsimulator;

import java.util.PriorityQueue;

public class EventsManager {
	
	private SchedulingSimulator simulator;
	
	/**
	 * The queue where the events are stored while waiting to 
	 * happen. They are ordered by descending priority.
	 */
	private PriorityQueue<Event> eventsQueue;
	
	/**
	 * TODO write javadoc for 
	 * EventsManager(SchedulingSimulator simulator)
	 * @param simulator
	 */
	public EventsManager(SchedulingSimulator simulator) {
		this.simulator = simulator;
		eventsQueue = new PriorityQueue<Event>();
	}
	
	/**
	 * TODO write javadoc for EventsManager.addEvent(Event event)
	 * @param event
	 */
	public void addEvent(Event event) {
		this.eventsQueue.add(event);
	}
	
	/**
	 * Checks if there is an event in the Events Queue.
	 * @return true if there is at least one event in the Events
	 * Queue, false otherwise.
	 */
	public boolean hasNextEvent() {
		return !this.eventsQueue.isEmpty();
	}
	
	private Event nextEvent() {
		return this.eventsQueue.poll();
	}
	
	/**
	 * TODO write javadoc for EventsManager.dispatchNextEvent()
	 */
	public void dispatchNextEvent() {
		Event event = nextEvent();
		switch (event.getType()) {
		case FINISH:
			this.simulator.getScheduler().handleFinishEvent(event);
			break;
		case ARRIV:
			this.simulator.getScheduler().handleArrivEvent(event);
			break;
		case SCHED:
			this.simulator.getScheduler().handleSchedEvent(event);
			break;
		case EXEC:
			this.simulator.getScheduler().handleExecEvent(event);
			break;
		}
	}
}
