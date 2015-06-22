package schedulingsimulator;

public class SchedulerFactory {

	/**
	 * Factory method to create a scheduler with a specific 
	 * scheduling algorithm and a cpu to run the processes.
	 * @param algorithm a string representing the scheduling
	 * algorithm that will be used to schedule the processes.
	 * It can be "FCFS", "SJN" (Shortest Job Next) or "SRT" 
	 * (Shortest Remaining Time).
	 * @param simulator the simulator the scheduler will belong to
	 * @return the created scheduler
	 */
	public static Scheduler createScheduler(String policy, CPU cpu) {
		
		//Instantiates the requested Scheduling Policy
		SchedulingPolicy schedulingPolicy = null;		
		switch (policy) {
		case "FCFS":
			schedulingPolicy = new FCFS();
			break;
		case "SJN":
			schedulingPolicy = new ShortestJobNext();
			break;
		case "SRT":
			schedulingPolicy = new ShortestRemainingTime();
			break;
		default:
			throw new IllegalArgumentException("Error: "+policy+" is not a valid policy.");
		}
		
		return new Scheduler(schedulingPolicy, cpu);
		
	}

}
