package schedulingsimulator;

import java.util.AbstractQueue;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Similar to java.util.PriorityQueue, but respects FIFO order if the priority is the same.
 * @author igormartire
 */
public class OrderedPriorityQueue<E> extends AbstractQueue<E>{
	
	private List<E> queue = new LinkedList<E>();;	
	private Comparator<? super E> comparator;
	
	public OrderedPriorityQueue() {
		this.comparator = null;
	}
	
	public OrderedPriorityQueue(Comparator<? super E> comparator) {
		this.comparator = comparator;
	}
	
	/**
	 * 
	 * @param e1
	 * @param e2
	 * @return true if e1 is of equal or lower priority than e2,
	 * false otherwise.
	 */
	private boolean compare(E e1, E e2) {
		if (this.comparator != null) {
			return this.comparator.compare(e1,e2) >= 0;
		}
		else {
			@SuppressWarnings("unchecked")
			Comparable<? super E> e = (Comparable<? super E>) e1;
			return e.compareTo(e2) >= 0;			
		}		
	}
	
	@Override
	public boolean offer(E e) {
		ListIterator<E> it = this.queue.listIterator(this.size());  
		
		boolean found = false;
		while (!found && it.hasPrevious()) {
			found = compare(e,it.previous());
		}
		
		if (found) {
			it.next();
		}
		
		it.add(e);		
		return true;
	}

	@Override
	public E peek() {		
		return this.queue.get(0);
	}

	@Override
	public E poll() {
		return this.queue.remove(0);
	}

	@Override
	public Iterator<E> iterator() {		
		return this.queue.iterator();
	}

	@Override
	public int size() {
		return this.queue.size();
	}
	
}
