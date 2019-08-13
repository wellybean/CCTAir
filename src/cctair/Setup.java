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

import java.util.*;
import java.time.*;

/**
 *
 * @author welli
 */
public class Setup {
    
    private Airplane[] airplaneArray;
    private AirplanePilot[] pilotArray;
    private ArrayList flights;
    private int pilotPicker;

    public void setup() {

        AirplanePilot defaultPilot = new AirplanePilot("Setup Pilot");

        Airplane boeing737 = new Airplane("Boeing", "737", 250, defaultPilot);
        Airplane boeing777 = new Airplane("Boeing", "777", 300, defaultPilot);
        Airplane boeing747_800 = new Airplane("Boeing", "747-800", 400, defaultPilot);
        Airplane airbusA320 = new Airplane("Airbus", "A320", 230, defaultPilot);
        Airplane airbusA330 = new Airplane("Airbus", "A330", 280, defaultPilot);
        Airplane airbusA340 = new Airplane("Airbus", "A340", 320, defaultPilot);
        Airplane airbusA380 = new Airplane("Airbus", "A380", 400, defaultPilot);
        Airplane embraer1000 = new Airplane("Embraer", "Lineage 1000", 180, defaultPilot);

        airplaneArray = new Airplane[]{boeing737, boeing777, boeing747_800, airbusA320, airbusA330, airbusA340, airbusA380, embraer1000};

        AirplanePilot pilot1 = new AirplanePilot("Richard Mark", new ArrayList<>(Arrays.asList(boeing737, boeing777)));
        AirplanePilot pilot2 = new AirplanePilot("Antonio Banderas", new ArrayList<>(Arrays.asList(airbusA320, airbusA330)));
        AirplanePilot pilot3 = new AirplanePilot("Stephen McDonaugh", new ArrayList<>(Arrays.asList(airbusA340, airbusA380)));
        AirplanePilot pilot4 = new AirplanePilot("Rafael Alcantara", new ArrayList<>(Arrays.asList(embraer1000)));
        AirplanePilot pilot5 = new AirplanePilot("Miguel Correro", new ArrayList<>(Arrays.asList(boeing747_800, boeing777)));
        AirplanePilot pilot6 = new AirplanePilot("Tom Scott", new ArrayList<>(Arrays.asList(boeing737, boeing777, boeing747_800)));
        AirplanePilot pilot7 = new AirplanePilot("Britney Spears", new ArrayList<>(Arrays.asList(airbusA320, airbusA330, embraer1000)));

        pilotArray = new AirplanePilot[]{pilot1, pilot2, pilot3, pilot4, pilot5, pilot6, pilot7};

        flights = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            String origin = getRandomLocation();

            String destination = getRandomLocation();

            while (destination.equals(origin)) {
                destination = getRandomLocation();
            }

            LocalTime departureTime = getRandomTime();

            LocalTime arrivalTime = departureTime.plusMinutes(120L);

            LocalDate departureDate = getRandomDateInFollowingWeek();

            Random r = new Random();

            Airplane plane = airplaneArray[r.nextInt(airplaneArray.length)];
            
            while(true) {
                pilotPicker = r.nextInt(pilotArray.length);
                if(pilotArray[pilotPicker].getAbleToFly().contains(plane)){
                    plane.assignPilot(pilotArray[pilotPicker]);
                    break;
                }
            }

            flights.add(new Flight(origin, destination, departureTime, arrivalTime, departureDate, plane));
        }        
    }

    public Airplane[] getAirplaneArray() {
        return airplaneArray;
    }

    public AirplanePilot[] getPilotArray() {
        return pilotArray;
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public static String getRandomLocation() {
        String[] location = {"Dublin", "Belfast", "Cork", "Shannon",
            "London Gatwick", "London Heathrow", "Manchester", "Liverpool",
            "Leeds", "Birmingham", "EdinBurgh", "Amsterdam", "Porto", "Lisbon",
            "Madrid", "Eindhoven", "Paris", "Roma", "Oslo", "Warsow", "Berlim",
            "Hamburg", "Frankfurt", "Bratslava", "Vienna"};
        Random r = new Random();
        return location[r.nextInt(location.length)];
    }

    public static LocalTime getRandomTime() {
        Random r = new Random();
        int random = r.nextInt(24 * 60 - 120);
        int hour = random / 60;
        int minute = random % 60;
        return LocalTime.of(hour, minute, 0, 0);
    }

    public static LocalDate getRandomDateInFollowingWeek() {
        Random r = new Random();
        int random = r.nextInt(30);
        LocalDate today = LocalDate.now();
        return today.plusDays(random);
    }
}
