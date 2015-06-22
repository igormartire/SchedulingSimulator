package schedulingsimulator;

public class FCFS implements SchedulingPolicy {

	@Override
	public int compare(Process p1, Process p2) {
		return 0;
	}

	@Override
	public boolean isPreemptive() {
		return false;
	}

}
