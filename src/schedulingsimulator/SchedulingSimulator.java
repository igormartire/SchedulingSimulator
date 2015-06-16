package schedulingsimulator;

/**
 * This is the main class. It reads the input file from the user,
 * initializes the scheduling process and, after it has finished,
 * saves the result on the output file. 
 * 
 * @author igormartire
 */
public class SchedulingSimulator {
	private Scheduler scheduler;
	private CPU cpu;
	
	/**
	 * The very start point of the program. 
	 * <p>
	 * The user must pass the input file's path as the first 
	 * parameter. Any aditional parameters that the user, in 
	 * its own delusion, might try to pass, will be completely 
	 * and pleasantly ignored.
	 * <p>
	 * This function creates a SchedulingSimulator, initially
	 * populated with the processes and events taken from the
	 * input file.
	 * @param args the arguments passed to the program. 
	 */
	public static void main(String[] args) {
		CPU cpu = new CPU();
		Scheduler scheduler = SchedulerFactory
				.createScheduler("FIFO", cpu);
		SchedulingSimulator simulator = 
				new SchedulingSimulator(scheduler, cpu);
		simulator.init(args[1]);
		simulator.start();
		simulator.end();		
	}
	
	/**
	 * SchedulingSimulator's constructor
	 * @param scheduler the scheduler that will be used in the 
	 * simulation
	 * @param cpu the cpu that will be used in the simulation
	 */
	public SchedulingSimulator(Scheduler scheduler, CPU cpu) {
		this.scheduler = scheduler;
		this.cpu = cpu;		
	}
	
	/**
	 * This functions parses the input file, creating the 
	 * corresponding processes and generating the initial
	 * events that will allow the start of the scheduling
	 * process.
	 * <p>
	 * Call this before starting the scheduler. 
	 * @param inputFilePath a string representing the path to the
	 * input file
	 */
	private void init(String inputFilePath) {
		// TODO implement SchedulingSimulator.init()
	}
	
	/**
	 * Starts the scheduling simulation.
	 */
	private void start() {
		this.scheduler.start();
	}
	
	/**
	 * Saves the results of the scheduling process on the output 
	 * file.
	 */
	private void end() {
		// TODO implement SchedulingSimulator.end()
	}
}
