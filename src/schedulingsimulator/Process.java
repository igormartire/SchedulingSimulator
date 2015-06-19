package schedulingsimulator;

public class Process {
	private String id;
	private int remainingTime;
	
	public Process(String id, int executionTime) {
		this.id = id;
		this.remainingTime = executionTime;
	}
}
