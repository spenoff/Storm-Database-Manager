/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sbm;

import java.io.*;
import java.io.FileInputStream;
import java.util.*;

/**
 * This class allows the user to interact with a database of Storms. It provides the user with a menu-based interface
 * that allows them to add, remove, and edit storms. You will need to be able to serialize (save) the database at the end
 * of the program and deserialize (load) the database if a file containing the database already exists. The database will
 * have storm names as the key and the associated Storm object as the value. Names should be converted to uppercase.
 *
 * @author Spencer Nisonoff
 */
public class StormStatServer {
    private static HashMap <String, Storm> database = new HashMap<String, Storm>();
    public static void main(String[] args) {
        System.out.println("Welcome to the StormStatServer, we may not be able to make it rain, but we sure can tell " +
                "you when it happened!");

        //Attempt to load data
        try {
            String fileName = "hurricane.ser";
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream inStream = new ObjectInputStream(file);
            database = (HashMap<String, Storm>)inStream.readObject();
            inStream.close();
            System.out.println("\n" + fileName + " was found and loaded." + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("\nNo previous data found.\n");
        } catch (IOException e) {
            System.out.println("An IOException occurred.");
        } catch(ClassNotFoundException e) {
            System.out.println("No previous data found.");
        }
        finally { //list of options
                System.out.println("Menu:");
                System.out.println("  A) Add A Storm");
                System.out.println("  L) Look Up A Storm");
                System.out.println("  D) Delete A Storm");
                System.out.println("  E) Edit Storm Data");
                System.out.println("  R) Print Storms Sorted By Rainfall");
                System.out.println("  W) W-Print Storms by Windspeed");
                System.out.println("  X) Save and quit");
                System.out.println("  Q) Quit and delete saved data\n\n\n");
                promptForOption();
        }
    }

    /**
     * Allows the user to enter one of the options
     */
    public static void promptForOption() {
        Scanner stdin = new Scanner(System.in);
        boolean userQuits = false;
        while(!userQuits) {
            System.out.print("Please select an option: ");
            String option = stdin.next().toUpperCase();
            switch (option) {
                case "A": addStorm(); break;
                case "L": lookUpStorm(); break;
                case "D": deleteStorm(); break;
                case "E": editStormData(); break;
                case "R": rPrint(); break;
                case "W": wPrint(); break;
                case "X": quit(true); userQuits = true; break;
                case "Q": quit(false); userQuits = true; break;
                default: System.out.println("Please select one of the options from above! ('Q' to quit).");
            }
        }
    }

    /**
     * Called if the user enters 'A'
     */
    public static void addStorm() {
        Scanner stdin = new Scanner(System.in);
        System.out.print("Please enter name: ");
        String name = stdin.next();
        System.out.print("Please enter date (yyyy-mm-dd): ");
        String date = stdin.next();
        if(!isFormatedDate(date)) {
            System.out.println("Invalid date format. Please try again."); addStorm(); return;
        }
        System.out.print("Please enter precipitation (cm): ");
        double precipitation = stdin.nextDouble();
        if(precipitation < 0) {
            System.out.println("Precipitation cannot be negative. Try again!");
            addStorm(); return;
        }
        System.out.print("Please enter windspeed (km/h): ");
        double windspeed = stdin.nextDouble();
        if(windspeed < 0) {
            System.out.println("Windspeed cannot be negative. Try again!");
            addStorm(); return;
        }

        database.put(name, new Storm(name, precipitation, windspeed, date));
        System.out.println(name + " added.");
    }

    /**
     * Called if the user enters 'L'
     */
    public static void lookUpStorm() {
        Scanner stdin = new Scanner(System.in);
        System.out.print("Please enter name: ");
        String name = stdin.next();
        Storm storm = database.get(name);
        System.out.println("\n" + storm.toString() + "\n");
    }

    /**
     * Called if the user enters 'D'
     */
    public static void deleteStorm() {
        Scanner stdin = new Scanner(System.in);
        System.out.print("Please enter name: ");
        String name = stdin.next();
        if(database.containsKey(name)) {
            database.remove(name);
            System.out.println("Storm " + name + " has been deleted");
        }
        else {
            System.out.println("Key not found.");
        }
    }

    /**
     * Called if the user enters 'E'
     */
    public static void editStormData() {
        Scanner stdin = new Scanner(System.in);
        System.out.print("Please enter name: ");
        String name = stdin.nextLine();
        System.out.println("In Edit Mode, please enter without any input to leave data unchanged. (You might need to hit" +
                " enter multiple times in this program)");
        System.out.print("Please enter date (yyyy-mm-dd): ");
        String date = stdin.nextLine();
        if(date == null || date.length() == 0){
            date = null;
        }
        else if(!isFormatedDate(date)) {
            System.out.println("Invalid date format. Please try again.");
            editStormData(); return;
        }
        double precipitation = -1;
        double windspeed = -1;
        String input;
        System.out.print("Please enter precipitation (cm): ");
        input = stdin.nextLine();
        if(input != null && input.length() > 0) {
            precipitation = Double.parseDouble(input);
        }
        System.out.print("Please enter windspeed (km/h): ");
        input = stdin.nextLine();
        if(input != null && input.length() > 0) {
            windspeed = Double.parseDouble(input);
        }
        if (date != null) {
            database.get(name).setDate(date);
        }
        if (precipitation >= 0) {
            database.get(name).setPrecipitation(precipitation);
        }
        if(windspeed >= 0) {
            database.get(name).setWindspeed(windspeed);
        }
    }

    /**
     * Checks if the date is formatted correctly
     * @param date the date
     * @return true if the date is correctly formatted, otherwise false
     */
    public static boolean isFormatedDate(String date) {
        if(date.length() != 10) {
            return false;
        }
        for(int i = 0; i < date.length(); i++) {
            if( (i < 4 && !(Character.isDigit(date.charAt(i))))) {
                return false;
            }
            if (i == 4 && date.charAt(i) != '-') {
                return false;
            }
            //Ignoring the space for now
            if(i > 4 && i < 7 && !(Character.isDigit(date.charAt(i)))) {
                return false;
            }
            if(i > 7 && !(Character.isDigit(date.charAt(i)))){
                return false;
            }
        }
        String month = date.substring(5, 7);
        String day = date.substring(8);
        if(Integer.parseInt(month) > 12){
            System.out.println("tip: months are number 01-12");
            return false;
        }
        if(Integer.parseInt(day) > 31){
            System.out.println("tip: days are numbered 01-31");
            return false;
        }
        if(Integer.parseInt(month) == 2 && Integer.parseInt(day) > 29){
            return false; //TODO do I need this?
        }
        return true;
    }

    /**
     * Called if the user enters 'R';
     * Prints the list of storms sorted by precipitation
     */
    public static void rPrint(){
        List<Storm> storms = new ArrayList<Storm>(database.values());
        Collections.sort(storms, new PrecipitationComparator());
        System.out.println("\nName               Windspeed   Rainfall");
        System.out.println("-------------------------------------");
        for(Storm s : storms) {
            System.out.println(s.getName() + "               " + s.getWindspeed() + "   " + s.getPrecipitation());
        }
    }

    /**
     * Called if the user enters 'W';
     * Prints the list of storms sorted by windspeed
     */
    public static void wPrint(){
        List<Storm> storms = new ArrayList<Storm>(database.values());
        Collections.sort(storms, new WindSpeedComparator());
        System.out.println("\nName               Windspeed   Rainfall");
        System.out.println("-------------------------------------");
        for(Storm s : storms) {
            System.out.println(s.getName() + "               " + s.getWindspeed() + "   " + s.getPrecipitation());
        }
    }

    /**
     * Called of the user enters 'X' or 'Q'
     * @param save true if the user enters 'X', false if the user enters 'Q'
     */
    public static void quit(boolean save) {
        String fileName = "hurricane.ser";
        if(save) {
            try {
                FileOutputStream file = new FileOutputStream(fileName);
                ObjectOutputStream outStream = new ObjectOutputStream(file);
                outStream.writeObject(database.values());
                System.out.println("File saved to " + fileName + "; feel free to use the weather channel in the meantime.");
            } catch (FileNotFoundException e) {
                System.out.println("Something went wrong when going through the directory.");
            } catch (IOException e) {
                System.out.println("An IO Exception occurred.");
            }
        }
        else {
            File file = new File(fileName);
            if(file.exists()) {
                file.delete();
            }
            System.out.println("Goodbye, it's hard to hold an electric candle in the cold November rain!");
        }
    }
    
}
