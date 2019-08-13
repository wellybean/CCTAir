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

/** An <code>Airplane</code> object stores characteristics inherited from the <code>Aircraft</code> class and also ones specific to airplanes.
 * @author Wellington Regis
 * @author Marcus Vinicius
 * @version 1.0
 */
public class Airplane extends Aircraft {
    private String make;
    private String model;
    
    /**
     * Constructor for the <code>Airplane</code> object receiving its make (or brand), its model, its maximum capacity and an <code>AirplanePilot</code> object.
     * @param make holds the brand of the airplane.
     * @param model holds the model of the airplane.
     * @param capacity holds the maximum number of passengers of the instance of the <code>Airplane</code> object.
     * @param pilot holds an object <code>AirplanePilot</code> which is assigned to the instance of the <code>Airplane</code> object.
     */
    public Airplane(String make, String model, int capacity, AirplanePilot pilot) {
        super(capacity, pilot, "Airplane");
        this.make = make;
        this.model = model;
    }
    
    /**
     * Make (or brand) getter
     * @return the make (or the brand) of the instance of the <code>Airplane</code> object.
     */
    public String getMake() {
        return make;
    }
    
    /**
     * Model getter
     * @return the model of the instance of the <code>Airplane</code> object.
     */
    public String getModel() {
        return model;
    }
    
    /**
     * Airplane information printer
     * @return a String with the aircraft type, its make (or brand), its model, its capacity and the assigned pilot's name.
     */
    @Override
    public String toString() {
        return "Aircraft Information: \n    Aircraft type: " + getAircraftType() 
                + "\n    Make: " + getMake() + "\n    Model: " + getModel() 
                + "\n    Capacity: " + getCapacity() + " seats\n    Pilot: " 
                + getPilotName();
    }
}
