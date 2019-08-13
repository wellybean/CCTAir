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
 * Driver class
 * @author Wellington Regis
 * @author Marcus Vinicius
 * @version 1.0
 */
public class CCTAir {

    public static void main(String[] args) {
        // Program initialisation
        Setup s = new Setup();
        s.setup();
        
        // User interaction
        Menu menu = new Menu();
        menu.run(s);
    }
}
