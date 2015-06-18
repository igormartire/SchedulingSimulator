package schedulingsimulator;

import java.util.List;

public class Scheduler {
	
	/**
	 * The algorithm that will be used on the scheduling process.
	 */
	private SchedulingStrategy schedulingAlgorithm;
	
	/**
	 * The simulator this scheduler belongs to.
	 */
	private SchedulingSimulator simulator;
			
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
	 * @param simulator the simulator this scheduler belongs to
	 */
	public Scheduler(SchedulingStrategy schedulingAlgorithm, 
			SchedulingSimulator simulator) {
		this.schedulingAlgorithm = schedulingAlgorithm;
		this.simulator = simulator;
	}
	
	/**
	 * Write javadoc for Scheduler.handleFinishEvent(Event event)
	 * @param event
	 */
	public void handleFinishEvent(Event event) {
		//TODO implement Scheduler.handleFinishEvent(Event event)
	}
	
	/**
	 * Write javadoc for Scheduler.handleArrivEvent(Event event)
	 * @param event
	 */
	public void handleArrivEvent(Event event) {
		//TODO implement Scheduler.handleArrivEvent(Event event)
	}
	
	/**
	 * Write javadoc for Scheduler.handleSchedEvent(Event event)
	 * @param event
	 */
	public void handleSchedEvent(Event event) {
		//TODO implement Scheduler.handleSchedEvent(Event event)
	}
	
	/**
	 * Write javadoc for Scheduler.handleExecEvent(Event event)
	 * @param event
	 */
	public void handleExecEvent(Event event) {
		//TODO implement Scheduler.handleExecEvent(Event event)
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
}
