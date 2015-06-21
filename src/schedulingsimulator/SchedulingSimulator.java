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
	private EventsQueue events;
	private Log log;
	private CPU cpu;
	private int time;
	
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
		SchedulingSimulator simulator = new SchedulingSimulator("FCFS");		
		simulator.run(args[0]);
	}
	
	public SchedulingSimulator(String algorithm) {
		CPU cpu = new CPU();
		this.cpu = cpu;
		this.scheduler = SchedulerFactory.createScheduler(algorithm, cpu);
		this.events = new EventsQueue();
		this.log = new Log();
		this.time = 0;
	}
	
	public void run(String inputFilePath) {
		this.init(inputFilePath);
		this.start();
		this.end();
	}
	
	/**
	 * This functions parses the input file, creating the 
	 * corresponding processes and generating the initial
	 * events that will allow the start of the scheduling
	 * process.
	 * <p>
	 * Call this before starting the scheduler. 
	 * @param inputFilePath a string representing the path
	 * to the input file
	 * @throws FileNotFoundException if the file specified was not found
	 */
	private void init(String inputFilePath){
		// TODO implement SchedulingSimulator.init()
	}
	
	/**
	 * Starts the scheduling simulation. Will stop when there are
	 * no more events to handle.
	 */
	private void start() {
		while ( this.events.hasNext() ) {
			Event newEvent = this.events.next();
			int elapsedTime = newEvent.getTime() - this.time;
			this.cpu.runFor(elapsedTime);
			this.time += elapsedTime;
			this.handleEvent(newEvent);			
		}
	}
	
	private void handleEvent(Event event) {
		switch(event.getType()) {
		case ARRIV:
			this.scheduler.addProcess(event.getProcess());
			this.events.add(new Event(Event.Type.SCHED, event.getTime(), null));
			break;		
		case SCHED:
			Process preScheduleRunningProcess = this.cpu.getProcess();
			this.scheduler.schedule();
			Process currentRunningProcess = this.cpu.getProcess();
			
			//Se houve mudança do processo executado na CPU
			if (preScheduleRunningProcess != currentRunningProcess) {
				
				//Se antes havia um processo sendo executado
				if (preScheduleRunningProcess != null) {
					this.events.remove(new Event(Event.Type.FINISH, this.time + preScheduleRunningProcess.getBurstTime(), preScheduleRunningProcess));
					this.log.addExecutionStop(this.time, false);
				}
				
				//Se um novo processo processo passou a ser executado
				if (currentRunningProcess != null) {
					this.events.add(new Event(Event.Type.FINISH, this.time + currentRunningProcess.getBurstTime(), currentRunningProcess));
					this.log.addExecutionStart(currentRunningProcess, this.time);
				}
			}
			break;
		case FINISH:			
			this.cpu.setProcess(null);			
			this.events.add(new Event(Event.Type.SCHED, event.getTime(), null));
			this.log.addExecutionStop(this.time, true);
			break;
		}
	}

	/**
	 * Saves the results of the scheduling process on the output 
	 * file.
	 */
	private void end() {
		// TODO implement SchedulingSimulator.end()
	}	
}
