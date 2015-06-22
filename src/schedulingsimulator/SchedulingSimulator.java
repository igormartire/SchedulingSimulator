package schedulingsimulator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

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
	 * @throws FileNotFoundException 
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		SchedulingSimulator simulator = new SchedulingSimulator("FCFS");
		simulator.run(args[0]);
	}
	
	public SchedulingSimulator(String policy) {
		CPU cpu = new CPU();
		this.cpu = cpu;
		this.scheduler = SchedulerFactory.createScheduler(policy, cpu);
		this.events = new EventsQueue();
		this.log = new Log();
		this.time = 0;
	}
	
	public void run(String inputFilePath) throws FileNotFoundException, UnsupportedEncodingException {
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
	private void init(String inputFilePath) throws FileNotFoundException {
		try ( Scanner scanner = new Scanner( new File(inputFilePath), "UTF-8" ) ) {
			while ( scanner.hasNext()) {
				String processId = scanner.next();
				int burstTime = scanner.nextInt();
				int arrivTime = scanner.nextInt();
				Process process = new Process(processId, burstTime);
				Event event = new Event(Event.Type.ARRIV, arrivTime, process);
				this.events.add(event);
			}
		}
	}
	
	private void debug() {
		System.out.println("Time: "+this.time);
		System.out.print("EVENTS: ");
		this.events.println();		
		System.out.print("SCHEDQ: ");
		this.scheduler.printlnSchedQueue();
		System.out.print("READYQ: ");
		this.scheduler.printlnReadyQueue();		
		System.out.print("C.P.U.: ");
		this.cpu.println();
		System.out.println();
	}
	
	/**
	 * Starts the scheduling simulation. Will stop when there are
	 * no more events to handle.
	 */
	private void start() {
		while ( this.events.hasNext() ) {		
			debug();
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
					this.scheduler.addProcess(preScheduleRunningProcess);
					//There is no need to create an SCHED event 
					//because the scheduling won't change until
					//another process arrives or the cpu's process
					//ends. When this happen, there will be an
					//SCHED event.
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
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	private void end() throws FileNotFoundException, UnsupportedEncodingException {
		try ( PrintWriter writer = new PrintWriter( "output.txt", "UTF-8" ) ) {
			for ( Log.Entry entry : this.log) {
				writer.println(entry);
			}
		}
	}	
}
