package schedulingsimulator;

public class Process {
	private String id;
	private int burstTime;
	
	public Process(String id, int executionTime) {
		this.id = id;
		this.burstTime = executionTime;
	}
	
	public String getId() {
		return this.id;
	}
	
	public int getBurstTime() {
		return this.burstTime;
	}
	
	public void runFor(int elapsedTime) {
		this.burstTime -= elapsedTime;
	}
	
}
