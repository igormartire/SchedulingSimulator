package schedulingsimulator;

public class Event implements Comparable<Event>{
	public enum Type {
		FINISH (0),
		ARRIV  (1),
		SCHED  (2);		
		
		private int priorityOrder;

		private Type(int priorityOrder) {
			this.priorityOrder = priorityOrder;
		}
		
		/**
		 * A lower value means a higher priority ( as they say... less is more ;) )
		 */
		public int getPriorityOrder() {
			return this.priorityOrder;
		}
	}

	private Type type;
	
	private int time;
	
	private Process process;
	
	/**
	 * Creates an event
	 * @param type the event's type
	 * @param time the time the event will happen
	 * @param process the process the event refers to. 
	 * If the event's type is SCHED, this parameter has no use.
	 */
	public Event(Type type, int time, Process process) {
		this.type = type;
		this.time = time;
		this.process = process;
	}
	
	/**
	 * @return the event's type
	 */
	public Type getType() {
		return this.type;
	}
	
	/**
	 * @return the time the event will happen
	 */
	public int getTime() {
		return this.time;
	}
	
	/**  
	 * @return the process the event refers to 
	 */
	public Process getProcess() {
		return this.process;
	}
	
	/**
	 * @return a negative integer, zero, or a positive integer as 
	 * this event's priority is higher than, equal to, or lower than
	 * the specified event's priority.
	 */
	@Override
	public int compareTo(Event event) {
		if (this.time != event.getTime()) {
			return this.time - event.getTime();
		}
		else {
			return this.type.getPriorityOrder() - event.getType().getPriorityOrder();
		}
	}
	
	public boolean equals(Object other) {
		if (this == other) return true;
		else if (!(other instanceof Event)) return false;
		else return this.compareTo((Event)other) == 0; 
	}
	
	@Override
	public String toString() {
		return "{"+this.type+"("+this.time+")"+
				(this.process != null ? "->"+this.process+"}" : "}");
	}
}
