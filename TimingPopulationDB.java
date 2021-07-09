import java.io.IOException;

/**
 * Timing Program for the population database
 * 
 * @author Mr. Reid and Aarya Shah
 * @course ICS4UC
 * @date 2020/05/15
 *
 */
public class TimingPopulationDB {

	public static void main(String[] args) throws IOException {
		// Create a database
		PopulationDatabase popDB = new PopulationDatabase();
				
		// Get data from the file
		long startTime = System.currentTimeMillis();
		popDB.readDataBaseFromFile("populations2016.csv");
		long endTime = System.currentTimeMillis();
		System.out.println("Reading took " + (endTime - startTime) + " milliseconds");
		System.out.println();
		
		// Sort and shuffle many times
		for (int i=0; i<5; i++) {					
			// Time the sorting
			startTime = System.currentTimeMillis();
			popDB.bubbleSort();		
			endTime = System.currentTimeMillis();
			System.out.println("Sorting took " + (endTime - startTime) + " milliseconds");

			// Shuffle it up
			popDB.shuffle(1000);
		}
			
	}

}
