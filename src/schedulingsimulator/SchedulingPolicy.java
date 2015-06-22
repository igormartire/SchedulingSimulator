package schedulingsimulator;

import java.util.Comparator;

public interface SchedulingPolicy extends Comparator<Process> {
	
	//public boolean schedule(Queue<Process> schedulerQueue, Queue<Process> readyQueue, CPU cpu);
	
	public boolean isPreemptive();
	
}
