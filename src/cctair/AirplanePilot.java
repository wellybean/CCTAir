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

import java.util.ArrayList;

/**
 * An <code>AirplanePilot</code> object holds characteristics of its parent class <code>Pilot</code> and ones unique to it.
 * @author Wellington Regis
 * @author Marcus Vinicius
 * @version 1.0
 */
public class AirplanePilot extends Pilot {

    private ArrayList<Airplane> ableToFly;
    
    /**
     * Constructor accepting only a String with the pilot's name (usually only used in the <code>Setup</code> class for default pilots).
     * @param name holds the name of the pilot.
     */
    public AirplanePilot(String name) {
        super(name);
    }
    
    /**
     * Constructor accepting a String with the pilot's name and an array list containing <code>Airplane</code> objects which the pilot is able to fly.
     * @param name holds the name of the pilot.
     * @param ableToFly holds an array list with <code>Airplane</code> objects which the pilot is able to fly.
     */
    public AirplanePilot(String name, ArrayList<Airplane> ableToFly) {
        super(name);
        this.ableToFly = ableToFly;
    }
    
    /**
     * Getter for the array list of <code>Airplane</code> objects which the pilot can fly.
     * @return an array list containing <code>Airplane</code> objects that the pilot can fly.
     */
    public ArrayList<Airplane> getAbleToFly() {
        return ableToFly;
    }
}
