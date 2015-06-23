package schedulingsimulator;

import java.util.Arrays;
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
	private OrderedPriorityQueue<Process> readyQueue;
	
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
		this.readyQueue = new OrderedPriorityQueue<Process>(schedulingPolicy);
	}	
	
	public void addProcess(Process process) {
		this.schedulerQueue.add(process);
	}
	
	public void schedule() {
		
		while ( ! schedulerQueue.isEmpty() ) {
			readyQueue.add( schedulerQueue.poll() );						
		}
		
		boolean execNewProcess;
		
		if ( this.readyQueue.isEmpty() ) {
			execNewProcess = false;
		}
		else {
			//There is a process on the ready queue
			if ( this.cpu.isEmpty() ) {
				execNewProcess = true;
			}
			else {
				//The cpu is being used by another process
				if ( ! this.schedulingPolicy.isPreemptive() ) {
					execNewProcess = false;
				}				
				else {
					//The scheduling process is preemptive					
					execNewProcess = this.schedulingPolicy.compare(readyQueue.peek(), cpu.getProcess()) < 0;
				}
			}
		}
		
		if (execNewProcess) {
			this.cpu.setProcess(this.readyQueue.poll());
		}
	}
	
	@Override
	public String toString() {
		String s;
		s  = "SCHEDQ: "+Arrays.toString(this.schedulerQueue.toArray())+"\n";
		s += "READYQ: "+Arrays.toString(this.readyQueue.toArray());
		return s;
	}
}
