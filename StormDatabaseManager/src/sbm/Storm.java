
package sbm;

import java.io.Serializable;

/**
 * This class represents a Storm
 *
 * @author Spencer Nisonoff
 */
public class Storm implements Serializable {
    private String name;
    private double precipitation;
    private double windspeed;
    private String date; //formatted YYYY-MM-DD

    /**
     * Main constructor
     *
     * @param name the name of the storm
     * @param precipitation the precipitation in cm
     * @param windspeed the windspeed in km/h
     * @param date the date of the storm
     */
    public Storm(String name, double precipitation, double windspeed, String date) {
        this.name = name;
        this.precipitation = precipitation;
        this.windspeed = windspeed;
        this.date = date;
    }

    /**
     * Returns the name of the storm
     * @return the name of the storm
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for the instance variable 'name'
     * @param name the new value for the instance variable 'name'
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the precipitation of the storm in cm
     * @return the precipitation of the storm in cm
     */
    public double getPrecipitation() {
        return precipitation;
    }

    /**
     * Setter method for the instance variable 'precipitation'
     * @param precipitation the new value for the instance variable 'precipitation'
     */
    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

    /**
     * Returns the windspeed of the storm in km/h
     * @return the windspeed of the storm in km/h
     */
    public double getWindspeed() {
        return windspeed;
    }

    /**
     * Setter method for the instance variable 'windspeed'
     * @param windspeed the new value for the instance variable 'windspeed'
     */
    public void setWindspeed(double windspeed) {
        this.windspeed = windspeed;
    }

    /**
     * Returns the date of the storm
     * @return the date of the storm
     */
    public String getDate() {
        return date;
    }

    /**
     * Setter method for the instance variable 'date'
     * @param date the new value for the instance variable 'date'
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Returns the String form of this Storm
     * @return the String form of this Storm
     */
    public String toString() {
        return "Storm " + name + ": Date " + date + ", " + windspeed + " km/h winds, " + precipitation + " cm of rain";
    }
}