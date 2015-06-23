package schedulingsimulator;

import java.io.FileNotFoundException;
import java.text.ParseException;

/**
 * This is the main class. It reads the input file from the user,
 * initializes the scheduling process and, after it has finished,
 * saves the result on the output file. 
 * 
 * @author igormartire
 */
public class SchedulingSimulator {
	
	private static final String OUTPUT_FILE_PATH = "output.txt";
	
	private Scheduler scheduler;
	private EventsQueue events;
	private Log log;
	private CPU cpu;
	private int time;
	private boolean verbose = false;
	
	/**
	 * The very start point of the program. 
	 * <p>
	 * The user must pass the input file's path as the first 
	 * parameter, and, optionally, the scheduling policy as 
	 * the second. Also optionally, as the third paramenter, 
	 * the user may pass "--verbose" to toggle verbose mode on. 
	 * Any aditional parameters the user, in its own delusion,
	 * might try to pass, will be completely and pleasantly ignored.
	 * <p>
	 * This function creates a SchedulingSimulator, initially
	 * populated with the processes and events taken from the
	 * input file.
	 * @param args the arguments passed to the program.
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Arquivo de entrada não especificado.");
		}
		else {
			String schedulingPolicy = null;
			boolean verbose = false;
			if (args.length >= 2) {
				schedulingPolicy = args[1];
				if ( ! SchedulerFactory.validateSchedulingPolicyName(schedulingPolicy) ) {
					System.out.println("<"+schedulingPolicy+"> não é uma política de escalonamento válida.");
					return;
				}
				if (args.length >= 3 && args[2].equals("--verbose")) {
					verbose = true;
				}
			}
			SchedulingSimulator simulator = new SchedulingSimulator(schedulingPolicy, verbose);
			try {
				simulator.run(args[0]);
			}
			catch (FileNotFoundException ex) {
				System.out.println("Arquivo de entrada não encontrado.");
			}
			catch (ParseException ex) {
				System.out.println(ex.getMessage());
				System.out.println("Falha na linha: "+ex.getErrorOffset());
			}
		}
	}
	
	public SchedulingSimulator(String schedulingPolicy, boolean verbose) {
		CPU cpu = new CPU();
		this.cpu = cpu;
		this.scheduler = SchedulerFactory.createScheduler(schedulingPolicy, cpu);
		this.events = new EventsQueue();
		this.log = new Log();
		this.time = 0;
		this.verbose = verbose;
	}
	
	public void run(String inputFilePath) throws FileNotFoundException, ParseException {
		this.init(inputFilePath);		
		this.start();
		this.end(OUTPUT_FILE_PATH);
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
	 * @throws ParseException 
	 * @throws IllegalArgumentException 
	 */
	private void init(String inputFilePath) throws FileNotFoundException, ParseException {
		for (Event event : DAO.getInstance().getEventsFromFile(inputFilePath)) {
			this.events.add(event);
		}
	}
	
	/**
	 * Starts the scheduling simulation. Will stop when there are
	 * no more events to handle.
	 */
	private void start() {
		if (verbose) System.out.println("Início do escalonamento\n");
		while ( this.events.hasNext() ) {		
			if (verbose) System.out.println(this+"\n");
			Event newEvent = this.events.next();
			int elapsedTime = newEvent.getTime() - this.time;
			this.cpu.runFor(elapsedTime);
			this.time += elapsedTime;
			this.handleEvent(newEvent);
		}
		if (verbose) System.out.println(this + "\n\nFim do escalonamento\n");
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
	private void end(String outputFilePath) throws FileNotFoundException {
		DAO.getInstance().writeLogToFile(this.log, outputFilePath);
	}
	
	@Override
	public String toString() {
		String s;
		s  = "Time: "+this.time+"\n";
		s += this.events+"\n";
		s += this.scheduler+"\n";
		s += this.cpu;
		return s;
	}
}
