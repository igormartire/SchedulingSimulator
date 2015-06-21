package schedulingsimulator;

import java.util.List;
import java.util.Queue;

public interface SchedulingPolicy {
	public boolean schedule(Queue<Process> schedulerQueue, List<Process> readyQueue, CPU cpu);
}
