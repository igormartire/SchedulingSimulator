package schedulingsimulator;

public class ShortestRemainingTime implements SchedulingPolicy {

	@Override
	public int compare(Process p1, Process p2) {
		return p1.getBurstTime() - p2.getBurstTime();
	}

	@Override
	public boolean isPreemptive() {
		return true;
	}

}
