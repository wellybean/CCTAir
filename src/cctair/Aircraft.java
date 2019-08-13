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

/**
 * An <code>Aircraft</code> object stores characteristics commonly shared by any type of aircraft.
 * @author Wellington Regis
 * @author Marcus Vinicius
 * @version 1.0
 */
public class Aircraft {
    private final int capacity;
    private Pilot pilot;
    private final String aircraftType;
    
    /**
     * Constructor for the <code>Aircraft</code> object receiving its capacity, its aircraft type and an object of type <code>Pilot</code>.
     * @param capacity holds the maximum number of passengers of the instance of the <code>Aircraft</code> object.
     * @param pilot holds an object <code>Pilot</code> which is assigned to the instance of the <code>Aircraft</code> object.
     * @param aircraftType holds a String with the type of the aircraft. 
     */
    public Aircraft(int capacity, Pilot pilot, String aircraftType) {
        this.capacity = capacity;
        this.pilot = pilot;
        this.aircraftType = aircraftType;
    }
    
    /**
     * Capacity getter
     * @return the capacity of the instance of the <code>Aircraft</code> object.
     */
    public int getCapacity() {
        return capacity;
    }
    
    /**
     * Aircraft type getter
     * @return the aircraft type of the instance of the <code>Aircraft</code> object.
     */
    public String getAircraftType() {
        return aircraftType;
    }
    
    /**
     * Pilot name getter
     * @return name of pilot assigned to the instance of the <code>Aircraft</code> object.
     */
    public String getPilotName() {
        return pilot.getName();
    }
    
    /**
     * Assigns a pilot to the aircraft
     * @param pilot of type <code>Pilot</code> is received from the method call and then assigned to the instance of the <code>Aircraft</code> object.
     */
    public void assignPilot(Pilot pilot) {
        this.pilot = pilot;
    }
    
    /**
     * Aircraft information printer
     * @return a String with the aircraft type, its capacity and the assigned pilot's name.
     */
    @Override
    public String toString() {
        return "Aircraft Information: \n\nAircraft type: " + getAircraftType() 
                + "\nCapacity: " + getCapacity() + "seats\nPilot: " 
                + getPilotName();
    }
}
