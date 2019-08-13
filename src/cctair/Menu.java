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
 * This class is used for handling the menu, which is repeatedly printed until the program is exited
 * @author Wellington Regis
 * @author Marcus Vinicius
 * @version 1.0
 */
public class Menu {
    
    private final Scanner scan;
    private final FlightAddition flightAdd;

    public Menu() {
        scan = new Scanner(System.in);
        flightAdd = new FlightAddition();
    }
    
    public void run(Setup s) {
        
        String newDepartureTime;
        String newArrivalTime;
        int flightOption;
        int menuOption = 0;
        int arrivalHour;
        int arrivalMin;
        int departHour;
        int departMin;
        boolean exit = false;
        
        // Runs until program is exited
        while(!exit) {
            
            // Menu printing
            System.out.println("********************************************\n"
                    + "Welcome to CCTAir! Here is our menu: \n"
                    + " (1) See all available flights. \n"
                    + " (2) Add another flight. \n"
                    + " (3) Reschedule arrival time of selected flight. \n"
                    + " (4) Reschedule arrival time and departure time of seleceted flight. \n"
                    + " (5) Exit."
            );
            System.out.print("Please select one of the options above: ");

            while (true) {
                try {
                    menuOption = scan.nextInt();
                    if (menuOption > 0 && menuOption <= 5) {
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

            switch (menuOption) {
                case 1:
                    for (int k = 0; k < s.getFlights().size(); k++) {
                        System.out.println("\n*********\nFlight " + (k + 1) + "\n*********");
                        System.out.println(s.getFlights().get(k).toString());
                    }
                    System.out.println("\n");
                    break;
                case 2:
                    flightAdd.addFlight(s);
                    break;
                case 3:
                    System.out.print("\nPlease select the flight which you would like to change the arrival time: ");
                    
                    while (true) {
                        try {
                            flightOption = scan.nextInt() - 1;
                            if (flightOption >= 0 && flightOption < s.getFlights().size()) {
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

                    System.out.print("Please type in the new arrival time in the format hh:mm: ");
                    scan.nextLine();
                    newArrivalTime = scan.nextLine();
                    while (true) {
                        if (newArrivalTime.length() == 5 && newArrivalTime.substring(2, 3).equals(":")) {
                            try {
                                arrivalHour = Integer.parseInt(newArrivalTime.substring(0, 2));
                                try {
                                    arrivalMin = Integer.parseInt(newArrivalTime.substring(3, 5));
                                    if (arrivalHour >= 0 && arrivalHour < 24 && arrivalMin >= 0 && arrivalMin < 60) {
                                        break;
                                    } else {
                                        System.out.print("INVALID FORMAT! Please type in a time in the format 'hh:mm': ");
                                        newArrivalTime = scan.nextLine();
                                    }
                                } catch (NumberFormatException ex) {
                                    System.out.print("INVALID FORMAT! Please type in a date in the format 'hh:mm': ");
                                    newArrivalTime = scan.nextLine();
                                }
                            } catch (NumberFormatException ex) {
                                System.out.print("INVALID FORMAT! Please type in a date in the format 'hh:mm': ");
                                newArrivalTime = scan.nextLine();
                            }
                        } else {
                            System.out.print("INVALID FORMAT! Please type in a time in the format 'hh:mm': ");
                            newArrivalTime = scan.nextLine();
                        }
                    }
                    s.getFlights().get(flightOption).schedule(LocalTime.of(arrivalHour, arrivalMin));
                    System.out.println("\nArrival time changed sucessfully!\n\n");
                    break;
                case 4:
                    System.out.print("\nPlease select the flight which you would like to change the departure and arrival times: ");
                    while (true) {
                        try {
                            flightOption = scan.nextInt() - 1;
                            if (flightOption >= 0 && flightOption < s.getFlights().size()) {
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
                    System.out.print("Please type in the new departure time in the format hh:mm: ");
                    scan.nextLine();
                    newDepartureTime = scan.nextLine();
                    while (true) {
                        if (newDepartureTime.length() == 5 && newDepartureTime.substring(2, 3).equals(":")) {
                            try {
                                departHour = Integer.parseInt(newDepartureTime.substring(0, 2));
                                try {
                                    departMin = Integer.parseInt(newDepartureTime.substring(3, 5));
                                    if (departHour >= 0 && departHour < 24 && departMin >= 0 && departMin < 60) {
                                        break;
                                    } else {
                                        System.out.print("INVALID FORMAT! Please type in a time in the format 'hh:mm': ");
                                        newDepartureTime = scan.nextLine();
                                    }
                                } catch (NumberFormatException ex) {
                                    System.out.print("INVALID FORMAT! Please type in a date in the format 'hh:mm': ");
                                    newDepartureTime = scan.nextLine();
                                }
                            } catch (NumberFormatException ex) {
                                System.out.print("INVALID FORMAT! Please type in a date in the format 'hh:mm': ");
                                newDepartureTime = scan.nextLine();
                            }
                        } else {
                            System.out.print("INVALID FORMAT! Please type in a time in the format 'hh:mm': ");
                            newDepartureTime = scan.nextLine();
                        }
                    }
                    System.out.print("Please type in the new arrival time in the format hh:mm: ");
                    newArrivalTime = scan.nextLine();
                    while (true) {
                        if (newArrivalTime.length() == 5 && newArrivalTime.substring(2, 3).equals(":")) {
                            try {
                                arrivalHour = Integer.parseInt(newArrivalTime.substring(0, 2));
                                try {
                                    arrivalMin = Integer.parseInt(newArrivalTime.substring(3, 5));
                                    if (arrivalHour >= 0 && arrivalHour < 24 && arrivalMin >= 0 && arrivalMin < 60) {
                                        break;
                                    } else {
                                        System.out.print("INVALID FORMAT! Please type in a time in the format 'hh:mm': ");
                                        newArrivalTime = scan.nextLine();
                                    }
                                } catch (NumberFormatException ex) {
                                    System.out.print("INVALID FORMAT! Please type in a date in the format 'hh:mm': ");
                                    newArrivalTime = scan.nextLine();
                                }
                            } catch (NumberFormatException ex) {
                                System.out.print("INVALID FORMAT! Please type in a date in the format 'hh:mm': ");
                                newArrivalTime = scan.nextLine();
                            }
                        } else {
                            System.out.print("INVALID FORMAT! Please type in a time in the format 'hh:mm': ");
                            newArrivalTime = scan.nextLine();
                        }
                    }
                    s.getFlights().get(flightOption).schedule(LocalTime.of(departHour,departMin), LocalTime.of(arrivalHour, arrivalMin));
                    System.out.println("\nArrival time changed sucessfully!\n\n");
                    break;
                case 5:
                    exit = true;
                    break;              
            }
        }
        scan.close();
    }
}
