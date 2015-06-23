package schedulingsimulator;

public class SchedulerFactory {

	/**
	 * Factory method to create a scheduler with a specific 
	 * scheduling algorithm and a cpu to run the processes.
	 * @param algorithm a string representing the scheduling
	 * algorithm that will be used to schedule the processes.
	 * It can be "FCFS", "SJN" (Shortest Job Next) or "SRT" 
	 * (Shortest Remaining Time). If it is null, "FCFS" is 
	 * chosen.
	 * @param simulator the simulator the scheduler will belong to
	 * @return the created scheduler
	 */
	public static Scheduler createScheduler(String policy, CPU cpu) {
		
		//Instantiates the requested Scheduling Policy
		SchedulingPolicy schedulingPolicy = null;
		if ( policy == null ) {
			schedulingPolicy = new FCFS();
		}
		else {
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
				throw new IllegalArgumentException("Erro: <"+policy+"> não é uma política de escalonamento válida.");
			}
		}
		
		return new Scheduler(schedulingPolicy, cpu);
		
	}
	
	public static boolean validateSchedulingPolicyName(String schedulingPolicy) {
		switch (schedulingPolicy) {
		case "FCFS":
		case "SJN":
		case "SRT":
			return true;
		default:
			return false;
		}
	}
	
	private SchedulerFactory() {}

}
