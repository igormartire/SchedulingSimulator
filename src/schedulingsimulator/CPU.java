package schedulingsimulator;

public class CPU {
	
	private Process process = null;
	
	public void setProcess(Process process) {
		this.process = process;		
	}
	
	public Process getProcess() {
		return this.process;		
	}
	
	public boolean isEmpty() {
		return this.process == null;
	}

	public void runFor(int elapsedTime) {
		if(!isEmpty()) {
			this.process.runFor(elapsedTime);
		}		
	}
}
