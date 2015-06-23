package schedulingsimulator;

import java.util.Comparator;

public interface SchedulingPolicy extends Comparator<Process> {
	
	public boolean isPreemptive();
	
}
