import java.io.*;
import java.util.*;

/**
 * Population Database - Example used for Searching and Sorting
 * 
 * @author Mr. Reid and Aarya Shah
 * @course ICS4UC
 * @date 2020/05/15
 */
public class PopulationDatabase {
	// Attributes
	// Array to hold cities (old school)
	private int numCities = 0;
	private CityData[] cities = null;
	private int timesShuffled = 0;
	private int timesSorted = 0;

	/**
	 * Read the city data in from the data file
	 * @param filename
	 * @throws IOException
	 */
	public void readDataBaseFromFile(String filename) throws IOException  {
		// Setup the IO
		Scanner sc = new Scanner(new FileReader(filename));

		// Read the file to see how big it is
		int numLines = 0;
		while (sc.hasNext()) {
			sc.nextLine();
			numLines++;
		}
		
		// Create Array to fit the data (less the header)
		this.cities = new CityData[numLines-1];
		
		// Reset file
		sc = new Scanner(new FileReader(filename));

		// Read out the header (throw it away)
		String line = sc.nextLine();
		
		// Read the file to get data
		this.numCities = 0;
		while (sc.hasNext()) {
			// Read a city data string
			line = sc.nextLine();
			
			// Parse the line
			CityData tempCity = new CityData(line);
			this.cities[this.numCities] = tempCity;
			this.numCities++;			
		}
		System.out.println("Read "+this.numCities+" cities into database\n");
	}

	/**
	 * Display as many of the cities as requested
	 * @param num
	 */
	public void display(int num) {
		// Can only show as many as we have
		if (num > this.numCities) {
			num = this.numCities;
		}

		// Show all the cities
		System.out.println("Showing "+num+" cities in current order:");
		for (int i=0; i<num; i++) {
			System.out.println(this.cities[i]);			
		}
		System.out.println();
	}
	
	/**
	 * Shuffle data
	 */
	public void shuffle(int howMuch) {
		// Increasing the number of times the order has been shuffled
		this.timesShuffled++;
		
		// Random number generator
		Random rand = new Random();
		
		// Loop how many times?
		for (int i=0; i<howMuch; i++) {
			int pos1 = rand.nextInt(this.numCities);
			int pos2 = rand.nextInt(this.numCities);

			// Swap cities
			CityData temp = cities[pos1];
			cities[pos1] = cities[pos2];
			cities[pos2] = temp;				
		}	
	}

	
	/**
	 * Bubble Sort algorithm
	 */
	public void bubbleSort() {
		// Increasing the number of times the order has been shuffled
		this.timesSorted++;
		
		// Track if we are done 
		boolean sorted = false;

		// Keep looping until sorted
		while (!sorted) {
			sorted = true;
			// Go through list of cities
			for (int i=0; i<this.numCities-1; i++) {			
				CityData cityA = cities[i];
				CityData cityB = cities[i+1];
				
				// Are they of order (positive if out of order)
				if (cityA.compareTo(cityB) > 0) {
					// Swap them
					cities[i] = cityB;
					cities[i+1] = cityA;
					sorted = false;
				}
			}
			
		}
	}

	/**
	 * Selection Sort algorithm
	 */
	public void selectionSort() {
		// Increasing the number of times the order has been shuffled
		this.timesSorted++;
		// Looping until the end of the array
		for (int i = 0; i < this.numCities; i++) {
			// Loops through all objects in the array
			for (int j = i; j < this.numCities; j++) {
				// Comparing the cities
				CityData largestPop = this.cities[i];
				if (cities[i].compareTo(cities[j])>0) {
					cities[j] = largestPop;
					cities[i] = cities[j];
				}
			}
		}
	}
	/**
	 * Linear search by the name
	 *
	 * @param name
	 * @return
	 */
	public CityData linearSearchByName(String name) {
		// Linear search
		CityData result = null;
		int index = 0;
		// Look for the city
		while (result == null && index < this.numCities) {
			// Does the name match
			if (this.cities[index].getCityName().equals(name)) {
				result = this.cities[index];
			}
			else {
				index++;
			}
		}
		return result;
	}
	
	/**
	 * Binary search by the name
	 * @param name
	 * @return
	 */
	public CityData binarySearchByName(String name) {
		CityData result = null;
		// Create an array of city names
		int nameArray[] = null;
		// Binary search
		int firstIndex = 0;
		int lastIndex = nameArray.length-1;
		// Look for the city
		while (firstIndex <= lastIndex) {
			// Getting the middle of the array
			int middle = (firstIndex + lastIndex)/2;
			if (this.cities[middle].getCityName().equals(name)) {
				result = this.cities[middle];
			}
		}
		return result;
	}

	/**
	 * Locate a city by name (first one it finds)
	 * @param string
	 * @return
	 */
	public CityData findByName(String name) {
		CityData result = null;
		// If times sorted is greater than times shuffled, binary search
		if (this.timesShuffled > this.timesSorted) {
			result = this.binarySearchByName(name);
		}
		else {
			result = this.linearSearchByName(name);
		}
		return result;
	}

}
