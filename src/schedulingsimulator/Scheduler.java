package schedulingsimulator;

import java.util.Queue;
import java.util.LinkedList;

public class Scheduler {
	
	/**
	 * The algorithm that will be used on the scheduling process.
	 */
	private SchedulingPolicy schedulingPolicy;
			
	/**
	 * The queue where the processes are stored while waiting
	 * to be scheduled. They should be in order of arrival.
	 */
	private Queue<Process> schedulerQueue;
	
	/**
	 * The queue where the processes are stores while waiting
	 * to be executed. They should be ordered by descending priority
	 * of execution. Who guarantees this order is the 
	 * {@link #schedulingPolicy}.
	 */
	
	//LinkedList, pois só ela é List e Queue ao mesmo tempo
	//sem adicionar mais complexidade do que o necessário.
	private LinkedList<Process> readyQueue;
	
	private CPU cpu;	
	
	/**
	 * Instantiates a scheduler with the specified scheduling
	 * algorithm and cpu.
	 * @param schedulingPolicy the scheduling algorithm to be used
	 * by the scheduler when handling an SCHED Event.
	 * @param cpu the cpu the scheduler will use
	 */
	public Scheduler(SchedulingPolicy schedulingPolicy, CPU cpu) {
		this.schedulingPolicy = schedulingPolicy;
		this.cpu = cpu;
		this.schedulerQueue = new LinkedList<Process>();
		this.readyQueue = new LinkedList<Process>();
	}	
	
	public void addProcess(Process process) {
		this.schedulerQueue.add(process);
	}
	
	public void schedule() {
		boolean execNewProcess = this.schedulingPolicy.schedule(
				this.schedulerQueue, this.readyQueue, this.cpu);
		if (execNewProcess) {
			this.cpu.setProcess(this.readyQueue.poll());
		}
	}
	
	public void setPolicy(SchedulingPolicy schedulingPolicy) {
		this.schedulingPolicy = schedulingPolicy;
	}
}
