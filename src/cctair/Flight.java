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

/**
 * A <code>Flight</code> object holds all the information belonging to a flight.
 * @author Wellington Regis
 * @author Marcus Vinicius
 * @version 1.0
 */
public class Flight {
    private final String origin;
    private final String destination;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private final LocalDate flightDate;
    private final Aircraft assignedAircraft;

    /**
     * Constructor for the <code>Flight</code> class that receives the origin of the flight, its destination, departure time, arrival time, date and assigned aircraft. 
     * @param origin holds a String with the city of departure.
     * @param destination holds a String with the city of arrival.
     * @param departureTime holds the departure time for the flight.
     * @param arrivalTime holds the arrival time for the flight.
     * @param flightDate holds the date of the flight.
     * @param assignedAircraft holds an object of type <code>Aircraft</code> which is assigned to the flight.
     */
    public Flight(String origin, String destination, LocalTime departureTime, LocalTime arrivalTime, LocalDate flightDate, Aircraft assignedAircraft) {
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.flightDate = flightDate;
        this.assignedAircraft = assignedAircraft;
    }
    
    /**
     * Setter for the arrival time of the flight.
     * @param arrivalTime is received from the method call and then assigned to the instance of the class <code>Flight</code>.
     */
    public void schedule(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    
    /**
     * Setter for the arrival and departure times of the flight.
     * @param arrivalTime is received from the method call and then assigned to the instance of the class <code>Flight</code>.
     * @param departureTime is received from the method call and then assigned to the instance of the class <code>Flight</code>.
     */
    public void schedule(LocalTime departureTime, LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }
    
    /**
     * Flight information printer.
     * @return a String with the flight date, its origin, destination, departure time, arrival time, aircraft type, its make (or brand), its model, its capacity and the assigned pilot's name.
     */
    @Override
    public String toString() {
        return "\nFlight information:\n    Date: " + flightDate.toString() 
                + "\n    From: " + origin + "\n    To: " + destination 
                + "\n    Departure time: " + departureTime.toString() 
                + "\n    Arrival time: " + arrivalTime.toString() + "\n"
                + assignedAircraft.toString();
    }
}
