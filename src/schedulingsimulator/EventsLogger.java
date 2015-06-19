package schedulingsimulator;

import java.util.Deque;
import java.util.LinkedList;

public class EventsLogger {
	
	private class LogEntry {
		String processId;
		int execStartTime;
		int execStopTime;
		boolean finished;
		
		private LogEntry(String processId, int execStartTime) {
			this.processId = processId;
			this.execStartTime = execStartTime;
		}
		
		private void setStopInfo(int execStopTime, boolean finished) {
			this.execStopTime = execStopTime;
			this.finished = finished;
		}
	}
	
	private Deque<LogEntry> log;
	
	public EventsLogger() {
		this.log = new LinkedList<LogEntry>();
	}
	
	

}
