package schedulingsimulator;

public class SchedulerFactory {

	/**
	 * Factory method to create a scheduler with a specific 
	 * scheduling algorithm and a cpu to run the processes.
	 * @param algorithm a string representing the scheduling
	 * algorithm that will be used to schedule the processes.
	 * It can be "FIFO", "SJN" (Shortest Job Next) or "SRT" 
	 * (Shortest Remaining Time).
	 * @param simulator the simulator the scheduler will belong to
	 * @return the created scheduler
	 */
	public static Scheduler createScheduler(String algorithm, SchedulingSimulator simulator) {
		// TODO implement SchedulerFactory.createScheduler(String algorithm, CPU cpu)
		return null;
	}

}
