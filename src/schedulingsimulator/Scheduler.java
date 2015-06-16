package schedulingsimulator;

import java.util.List;

public class Scheduler {
	
	/**
	 * The algorithm that will be used on the scheduling process.
	 */
	private SchedulingStrategy schedulingAlgorithm;
	
	/**
	 * The CPU that will be used to run the processes.
	 */
	private CPU cpu;
	
	/**
	 * The queue where the events are stored while waiting to 
	 * happen. They should be ordered by descending priority.
	 * Who guarantees this order is the 
	 * {@link #addEvent(Event event)} method.
	 * @see #addEvent(Event event)
	 */
	private List<Event> eventsQueue;
	
	/**
	 * The queue where the processes are stored while waiting
	 * to be scheduled. They should be in order of arrival.
	 * Who guarantees this order is the {@link #handleNextEvent()}
	 * method, when handling the ARRIV Event.
	 */
	private List<Process> schedulerQueue;
	
	/**
	 * The queue where the processes are stores while waiting
	 * to be executed. They should be ordered by descending priority
	 * of execution. Who guarantees this order is the 
	 * {@link #schedulingAlgorithm}.
	 */
	private List<Process> readyQueue;
	
	/**
	 * Instantiates a scheduler with the specified scheduling
	 * algorithm and cpu.
	 * @param schedulingAlgorithm the scheduling algorithm to be used
	 * by the scheduler when handling an SCHED Event.
	 * @param cpu the cpu to which the processes will be sent to run.
	 */
	public Scheduler(SchedulingStrategy schedulingAlgorithm, 
			CPU cpu) {
		this.schedulingAlgorithm = schedulingAlgorithm;
		this.cpu = cpu;
	}
	
	/**
	 * Starts the scheduler. Then, it will only stop when there
	 * are no more events to handle.
	 */
	public void start() {
		while ( hasNextEvent() ) {
			handleNextEvent();
		}
	}
	
	/**
	 * TODO write javadoc for Scheduler.addEvent(Event event)
	 * @param event
	 */
	public void addEvent(Event event) {
		// TODO implement Scheduler.addEvent(Event event)
	}

	/**
	 * Check if there is an event in the Events Queue.
	 * @return true if there is at least one event in the Events
	 * Queue, false otherwise.
	 */
	private boolean hasNextEvent() {		
		return !eventsQueue.isEmpty();
	}
	
	/**
	 * TODO write javadoc for Scheduler.handleNextEvent()
	 */
	private void handleNextEvent() {
		// TODO implement Scheduler.handleNextEvent()		
	}
	
	/**
	 * Getter method for the {@link #eventsQueue} field.
	 * @return the scheduler's events queue
	 */
	public List<Event> getEventsQueue() {
		return this.eventsQueue;
	}
	
	/**
	 * Getter method for the {@link #schedulerQueue} field.
	 * @return the scheduler's scheduler queue
	 */
	public List<Process> getSchedulerQueue() {
		return this.schedulerQueue;
	}
	
	/**
	 * Getter method for the {@link #readyQueue} field.
	 * @return the scheduler's ready queue
	 */
	public List<Process> getReadyQueue() {
		return this.readyQueue;
	}
	
	/**
	 * Getter method for the {@link #cpu} field.
	 * @return the cpu that is used by this scheduler.
	 */
	public List<Process> getReadyQueue() {
		return this.readyQueue;
	}
}
