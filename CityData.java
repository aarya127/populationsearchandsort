import java.util.Calendar;

/**
 * Object to contain the data for a single city
 * @author Mr. Reid and Aarya Shah
 * @date 2020/05/15
 * @course ICS4UC
 */
public class CityData {
	// Attributes
	private String cityName = "";
	private String country = "";
	private double lat = 0;
	private double lng = 0;
	private boolean capital = false;
	private int population = 0;
	private int id = 0;
	
	private static String sortKey = "";
	
	
	/**
	 * Constructor - watch for missing data
	 * @param line
	 */
	public CityData(String line) {
		// Parse the data
		String[] parts = line.split(",");
	
		// Name of City and country
		this.cityName = parts[0];
		this.country = parts[3];

		// Map location
		try {
			this.lat = Double.parseDouble(parts[1]);
			this.lng = Double.parseDouble(parts[2]);
		} catch (Exception e) {
			this.lat = 0;
			this.lng = 0;
		}

		// Is it the capital?
		if (parts[4].contentEquals("primary")) {
			this.capital = true;
		} else {
			this.capital = false;
		}

		// Population and id number
		try {
			this.population = Integer.parseInt(parts[5]);
		} catch (Exception e) {
			this.population = 0;
		}
		try {
			this.id = Integer.parseInt(parts[6]);
		} catch (Exception e) {
			this.id = 0;
		}		
	}

	/**
	 * toString override
	 * @return city data as a string
	 */
	public String toString() {		
		String result = String.format("%10d %s : %s", this.population, this.cityName, this.country);
		return result;
	}

	/**
	 * Returns which city comes before another
	 * (simplest by comparing populations)
	 * @param other - other city to compare to
	 * @return > 0 is this is great than other
	 * 			< 0 is this is less than other
	 */
	public int compareTo(CityData other) {	
		int result = 0;
		
		// switch based on sortKey
		if (sortKey.equals("cityName")) {
			result = this.cityName.compareTo(other.cityName);
		} 
		else if (sortKey.equals("country")) {
			result = this.country.compareTo(other.country);
		} 
		else { 
			// Default is by population
			result = other.population - this.population; 
		}
		return result;
	}

	/**
	 * Set the sorting key - options
	 * Options:
	 *    population - sort by size of city
	 *    cityName - sort by name of city
	 *    country - sort by name of country
	 * @param key
	 */
	public static void setSortKey(String key) {
		CityData.sortKey = key;
	}

	/**
	 * Get the cityName attribute
	 * @return
	 */
	public String getCityName() {
		return this.cityName;
	}
	
	
}
