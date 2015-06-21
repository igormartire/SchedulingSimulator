package schedulingsimulator;

import java.util.Deque;
import java.util.LinkedList;

public class Log {
	
	public class Entry {	
		private String processId;
		private int execStartTime;
		private int execStopTime;
		private boolean finished;
		
		private Entry(String processId, int execStartTime) {
			this.processId = processId;
			this.execStartTime = execStartTime;
		}
		
		private void setStopInfo(int execStopTime, boolean finished) {
			this.execStopTime = execStopTime;
			this.finished = finished;
		}
		
		public String getProcessId() {
			return this.processId;
		}
		
		public int getExecStartTime() {
			return this.execStartTime;
		}
		
		public int getExecStopTime() {
			return this.execStopTime;
		}
		
		public boolean isFinished() {
			return this.finished;
		}
	}
	
	private Deque<Entry> log;
	
	public Log() {
		this.log = new LinkedList<Entry>();
	}
	
	public void addExecutionStart(Process p, int execStartTime) {
		Entry newEntry = new Entry(p.getId(), execStartTime);
		this.log.addLast(newEntry);
	}

	public void addExecutionStop(int execStopTime, boolean finished) {
		Entry lastEntry = this.log.getLast();
		lastEntry.setStopInfo(execStopTime, finished);
	}
}
