import java.io.IOException;

/**
 * Test Program for the population database
 * 
 * @author Mr. Reid and Aarya Shah
 * @course ICS4UC
 * @date 2020/05/15
 *
 */
public class TestPopulationDB {

	public static void main(String[] args) throws IOException {
		// Create a database
		PopulationDatabase popDB = new PopulationDatabase();
		
		// Get data from the file
		popDB.readDataBaseFromFile("populations2016_Short.csv");
		
		// Show us what you got
		popDB.display(15);

		// Sort Them
		//CityData.setSortKey("cityName");
		popDB.bubbleSort();		
		popDB.display(15);

		// Find
		CityData foundCity = popDB.linearSearchByName("Toronto");
		System.out.println(foundCity);
		
	}

}
