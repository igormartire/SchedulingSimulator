package schedulingsimulator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class DAO {
	
	private static DAO instance = new DAO();
	
	public static DAO getInstance() {
		return instance;
	}
	
	public List<Event> getEventsFromFile(String filePath) throws FileNotFoundException, ParseException {
		File inputFile = new File(filePath);
		
		List<Event> events = new LinkedList<Event>();
		int lineCount = 0;
		try ( Scanner scanner = new Scanner( inputFile ) ) {
			while ( scanner.hasNext() ) {
				lineCount++;
				String processId = scanner.nextLine();
				lineCount++;
				int burstTime = Integer.parseInt(scanner.nextLine());
				lineCount++;
				int arrivTime = Integer.parseInt(scanner.nextLine());
				Process process = new Process(processId, burstTime);
				Event event = new Event(Event.Type.ARRIV, arrivTime, process);
				events.add(event);
			}
		}
		catch (NoSuchElementException ex) {
			throw new ParseException("Arquivo de entrada <"+filePath+"> com formato de conteúdo inválido.\n" +
					"Esperava-se mais uma linha.", lineCount);
		}
		catch (NumberFormatException ex) {
			throw new ParseException("Arquivo de entrada <"+filePath+"> com formato de conteúdo inválido.\n" +
					"Número inválido.", lineCount);
		}
		return events;
				
	}
	
	public void writeLogToFile(Log log, String filePath) throws FileNotFoundException {
		try ( PrintWriter writer = new PrintWriter( "output.txt" ) ) {
			for ( Log.Entry entry : log ) {
				writer.println(entry);
			}
		}
	}
	
	private DAO() {}
	
}
