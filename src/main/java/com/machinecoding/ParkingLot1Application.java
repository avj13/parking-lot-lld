package com.machinecoding;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParkingLot1Application {

	public static void main(String[] args) {
		SpringApplication.run(ParkingLot1Application.class, args);
		
		printCommands();
        readCommandAndExecute();
	}
        
        private static void readCommandAndExecute() {
            String inputLine;
            Scanner scanner = new Scanner(System.in);
            do {
                inputLine = scanner.nextLine();
                if (InputValidator.isValidInput(inputLine)) {
                    String[] input = inputLine.split(" ");
                    Command cmd;
                    try {
                        cmd = Command.valueOf(input[0]);
                        cmd.executeCommand(input);
                    } catch (Exception e) {
                        System.out.println("Please provide valid command....");
                    }
                }
            } while (!inputLine.equals("exit"));
        }

        private static void printCommands() {
            System.out.println("***************************************************************************************");
            System.out.println("**********************  WELCOME TO PARKING SYSTEM APPLICATION  ************************");
            System.out.println("***************************************************************************************");
            System.out.println("*******************************  SAMPLE INPUT COMMANDS  *******************************");
            System.out.println("1. create_parking_lot {capacity}");
            System.out.println("2. park {car_number}");
            System.out.println("3. leave {VehicleNUmber} {hours}");
            System.out.println("4. status");
        }
	

}
