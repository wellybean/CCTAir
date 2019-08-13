/* 
 * Copyright (C) 2019 Wellington Regis, Marcus Vinicius
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package cctair;

import java.time.*;
import java.util.*;

/**
 * This class adds a new flight to the pool of flights through user input
 * @author Wellington Regis
 * @author Marcus Vinicius
 * @version 1.0
 */
public class FlightAddition {
    private int numberAddedFlightsCounter;
    private final Scanner scan;

    /**
     * Constructor for objects of type <code>FlightAddition</code>
     */
    public FlightAddition() {
        numberAddedFlightsCounter = 0;
        scan = new Scanner(System.in);
    }
    
    /**
     * Method used to add a flight through user input
     * @param s holds the <code>Setup</code> object created in the Driver class
     */
    public void addFlight(Setup s) {
        
        LocalDate date;
        LocalTime departTime;
        LocalTime arrivalTime;
        Airplane plane;
        String origin;
        String destination;
        ArrayList<Pilot> listOfPilotsAbleToFlySelectedAircraft = new ArrayList<>();
        
        if(numberAddedFlightsCounter < 5) {
            // Gets departure location from user
            origin = getOriginFromUser();
            
            // Gets destination location from user
            destination = getDestinationFromUser();
            
            // Gets flight date from user
            date = getFlightDateFromUser();
            
            // Gets departure time from user
            departTime = getDepartTimeFromUser();
            
            // Gets arrival time from user
            arrivalTime = getArrivalTimeFromUser();
            
            // Lets user select an airplane from list of available airplanes.
            plane = getAirplaneSelectionFromUser(s);
            
            // Lets the user select a pilot from a list of pilots that are able to fly the selected plane
            assignPilotToAircraftFromUserInput(s, plane, listOfPilotsAbleToFlySelectedAircraft);

            // Creates new flight
            s.getFlights().add(new Flight(origin, destination, departTime, arrivalTime, date, plane));
            
            // Used to fix glitch with Scanner
            scan.nextLine();
            
            // Printed to tell the user that the addition was succesful
            System.out.println("\nFlight added succesfully!\n\n");
            
            // Accounts for number of flights added
            numberAddedFlightsCounter++;
        } else {
            // Printed in case the maximum number of added flights has been reached
            System.out.println("The maximum number of flights has been reached.");
        }       
    }
    
    private void assignPilotToAircraftFromUserInput(Setup s, Airplane plane, ArrayList<Pilot> listOfPilotsAbleToFlySelectedAircraft) {
        int pilotOption;
        
        System.out.println("Here is a list of pilots that are able to fly the selected airplane: ");
        
        // This block checks the array of pilots for pilots that are able to fly 
        // the selected aircraft. If there's a match, the pilot is then added to
        // an array list containing only pilots that are able to fly the selected
        // aircraft.
        for (int i = 0; i < s.getPilotArray().length; i++) {
            if (s.getPilotArray()[i].getAbleToFly().contains(plane)) {
                listOfPilotsAbleToFlySelectedAircraft.add(s.getPilotArray()[i]);
            }
        }
        
        // This blocks lists the names of the pilots that can fly the selected aircraft
        for (int i = 0; i < listOfPilotsAbleToFlySelectedAircraft.size(); i++) {
            System.out.println("    (" + (i + 1) + ") " + listOfPilotsAbleToFlySelectedAircraft.get(i).getName());
        }
        
        System.out.print("Please type in your choice of pilot: ");
        
        // This loops runs until the user has type in a valid pilot option
        while (true) {
            try {
                pilotOption = scan.nextInt();
                if (pilotOption > 0 && pilotOption <= listOfPilotsAbleToFlySelectedAircraft.size()) {
                    break;
                } else {
                    System.out.print("INVALID OPTION! Please type in a valid option: ");
                }
            } catch (NumberFormatException ex) {
                System.out.print("INVALID OPTION! Please type in a valid option: ");
            } catch (InputMismatchException ex) {
                System.out.print("INVALID OPTION! Please type in a valid option: ");
                scan.next();
            }
        }
        
        // The selected pilot is then assigned to the selected aircraft
        plane.assignPilot(listOfPilotsAbleToFlySelectedAircraft.get(pilotOption - 1));
    }
    
    private Airplane getAirplaneSelectionFromUser(Setup s) {
        
        int planeOption;
        
        System.out.println("Here is a list of available airplanes: ");
        
        // Displays list of available planes
        for (int i = 0; i < s.getAirplaneArray().length; i++) {
            System.out.println("    (" + (i + 1) + ") " + s.getAirplaneArray()[i].getMake() + " " + s.getAirplaneArray()[i].getModel());
        }
        
        System.out.print("Please make an airplane selection: ");
        
        
        // Runs until a valid plane option is typed in
        while (true) {
            try {
                planeOption = (scan.nextInt()) - 1;
                if (planeOption >= 0 && planeOption < s.getAirplaneArray().length) {
                    break;
                } else {
                    System.out.print("INVALID OPTION! Please type in a valid option: ");
                }
            } catch (NumberFormatException ex) {
                System.out.print("INVALID OPTION! Please type in a valid option: ");
            } catch (InputMismatchException ex) {
                System.out.print("INVALID OPTION! Please type in a valid option: ");
                scan.next();
            }
        }
        return s.getAirplaneArray()[planeOption];
    }
    
    private LocalTime getArrivalTimeFromUser() {
        
        String arrivalTime;
        int arrivalHour;
        int arrivalMin;
        
        System.out.print("Please type in arrival time of the flight in the format 'hh:mm': ");
        arrivalTime = scan.nextLine();
        
        // Runs until a time in the right format is input
        while (true) {
            if (arrivalTime.length() == 5 && arrivalTime.substring(2, 3).equals(":")) {
                try {
                    arrivalHour = Integer.parseInt(arrivalTime.substring(0, 2));
                    try {
                        arrivalMin = Integer.parseInt(arrivalTime.substring(3, 5));
                        if (arrivalHour >= 0 && arrivalHour < 24 && arrivalMin >= 0 && arrivalMin < 60) {
                            break;
                        } else {
                            System.out.print("INVALID FORMAT! Please type in a time in the format 'hh:mm': ");
                            arrivalTime = scan.nextLine();
                        }
                    } catch (NumberFormatException ex) {
                        System.out.print("INVALID FORMAT! Please type in a date in the format 'hh:mm': ");
                        arrivalTime = scan.nextLine();
                    }
                } catch (NumberFormatException ex) {
                    System.out.print("INVALID FORMAT! Please type in a date in the format 'hh:mm': ");
                    arrivalTime = scan.nextLine();
                }
            } else {
                System.out.print("INVALID FORMAT! Please type in a time in the format 'hh:mm': ");
                arrivalTime = scan.nextLine();
            }
        }
        return LocalTime.of(arrivalHour, arrivalMin);
    }

    private LocalTime getDepartTimeFromUser() {
        String departureTime;
        int departHour;
        int departMin;

        System.out.print("Please type in departure time of the flight in the format 'hh:mm': ");
        
        departureTime = scan.nextLine();
        
        // Runs until a time in the right format is input
        while (true) {
            if (departureTime.length() == 5 && departureTime.substring(2, 3).equals(":")) {
                try {
                    departHour = Integer.parseInt(departureTime.substring(0, 2));
                    try {
                        departMin = Integer.parseInt(departureTime.substring(3, 5));
                        if (departHour >= 0 && departHour < 24 && departMin >= 0 && departMin < 60) {
                            break;
                        } else {
                            System.out.print("INVALID FORMAT! Please type in a time in the format 'hh:mm': ");
                            departureTime = scan.nextLine();
                        }
                    } catch (NumberFormatException ex) {
                        System.out.print("INVALID FORMAT! Please type in a date in the format 'hh:mm': ");
                        departureTime = scan.nextLine();
                    }
                } catch (NumberFormatException ex) {
                    System.out.print("INVALID FORMAT! Please type in a date in the format 'hh:mm': ");
                    departureTime = scan.nextLine();
                }
            } else {
                System.out.print("INVALID FORMAT! Please type in a time in the format 'hh:mm': ");
                departureTime = scan.nextLine();
            }
        }
        return LocalTime.of(departHour, departMin);
    }

    private LocalDate getFlightDateFromUser() {
        String date;
        int year;
        int month;
        int day;
        LocalDate userDate;

        System.out.print("Please type in the date of the flight in the format yyyy-mm-dd: ");
        date = scan.nextLine();
        
        // Runs until a date in the right format is input
        while (true) {
            if (date.length() == 10 && date.substring(4, 5).equals("-") && date.substring(7, 8).equals("-")) {
                try {
                    year = Integer.parseInt(date.substring(0, 4));
                    try {
                        month = Integer.parseInt(date.substring(5, 7));
                        try {
                            day = Integer.parseInt(date.substring(8, 10));
                            userDate = LocalDate.of(year, month, day);
                            // Checks if the date input is in the following year starting from the current day
                            if (userDate.isAfter(LocalDate.now()) && !(userDate.isAfter(LocalDate.now().plusYears(1L)))) {
                                break;
                            } else {
                                System.out.print("INVALID FORMAT! Please type in a date in the format yyyy-mm-dd: ");
                                date = scan.nextLine();
                            }
                        } catch (NumberFormatException|DateTimeException ex) {
                            System.out.print("INVALID FORMAT! Please type in a date in the format yyyy-mm-dd: ");
                            date = scan.nextLine();
                        }
                    } catch (NumberFormatException ex) {
                        System.out.print("INVALID FORMAT! Please type in a date in the format yyyy-mm-dd: ");
                        date = scan.nextLine();
                    }
                } catch (NumberFormatException ex) {
                    System.out.print("INVALID FORMAT! Please type in a date in the format yyyy-mm-dd: ");
                    date = scan.nextLine();
                }
            } else {
                System.out.print("INVALID FORMAT! Please type in a date in the format yyyy-mm-dd: ");
                date = scan.nextLine();
            }
        }

        return LocalDate.of(year, month, day);
    }
    
    private String getOriginFromUser() {
        System.out.print("\nPlease type in your departure location: ");
        return scan.nextLine();
    }
    
    private String getDestinationFromUser() {
        System.out.print("Please type in your destination location: ");
        return scan.nextLine();
    }
}
