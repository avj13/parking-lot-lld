package com.machinecoding.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.machinecoding.InputValidator;
import com.machinecoding.exception.InvalidVehicleNumber;
import com.machinecoding.exception.NoEmptySlotAvailable;
import com.machinecoding.strategy.ParkingCostStrategy;

public class Parking implements ParkingFloor {
	
	private static Parking parkingLot;
	private final int floorNumber;
	private final List<Slot> slots;
	
	//when constructor called, floor number is finalized and new List of slots is generated.
	private Parking(int floorNumber) {
        this.floorNumber = floorNumber;
        this.slots = new ArrayList<>();
    }
	
//	singleton pattern to return parkingFloor
	public static Parking getParkingFloor(int floorNumber) {
        if (parkingLot == null)
            parkingLot = new Parking(floorNumber);
        return parkingLot;
    }
	
	public static void clearAll() {
        parkingLot = null;
    }
	
	private Slot getNextEmptySlotOnFloot() throws NoEmptySlotAvailable{
		for(Slot s: slots) {
			if(!s.isEmpty)
				return s;
		}
		throw new NoEmptySlotAvailable("For floorNumber " + floorNumber + " No Empty Slot available");
	}

	@Override
	public boolean createParkingSlot(int numberOfSlots) {
		if(InputValidator(InputValidator.isValidSlotNumber(numberOfSlots)) || slots.size() > 0)
			return false;
		
		for(int i=1;i<=numberOfSlots;i++) {
			slots.add(new Slot(UUID.randomUUID().toString(), i ));
		}
		
		System.out.printf("Created a parking lot with %s slots %n", numberOfSlots);
        return true;
			
	}

	private boolean InputValidator(boolean validSlotNumber) {
		if (!validSlotNumber) {
            return true;
        }
        return false;
	}

	@Override
	public boolean parkVehicle(Vehicle vehicle) throws NoEmptySlotAvailable {
		Slot nextEmptySlotOnFloor = getNextEmptySlotOnFloot();
		nextEmptySlotOnFloor.placeVehicle(vehicle);
		
		System.out.printf("Allocated slot number: %d \n", nextEmptySlotOnFloor.getSlotNumber());
		return true;
	}

	@Override
	public int unPark(String vehicleNumber, int numberOfHours, ParkingCostStrategy parkingCostStrategy)
			throws InvalidVehicleNumber {
		int costByHour = 0;
		try {
			Slot s = getSlotByVehicleNumber(vehicleNumber);
			s.removeVehicle();
			costByHour = getCostByHours(numberOfHours, parkingCostStrategy);
			System.out.println("Vehicle Registration number " + vehicleNumber + " with slot number " + s.getSlotNumber() +
                    " is free to go with Charge " + costByHour);
		}
		catch(InvalidVehicleNumber invalidVehicleNumber) {
            System.out.println(invalidVehicleNumber.getMessage());
            throw invalidVehicleNumber;
		}
		return costByHour;
	}
	
	private int getCostByHours(int parkHours, ParkingCostStrategy parkingCostStrategy) {
        return parkingCostStrategy.getCost(parkHours);
    }
	
	private Slot getSlotByVehicleNumber(String vehicleNumber) throws InvalidVehicleNumber {
        for (Slot slot : slots) {
            Vehicle vehicle = slot.getParkVehicle();
            if (vehicle != null && vehicle.getVehicleNumber().equals(vehicleNumber)) {
                return slot;
            }
        }
        throw new InvalidVehicleNumber("Registration number " + vehicleNumber + " not found");
    }
	
	 public void printStatus() {
	        System.out.println("Slot No.  Registration No");
	        slots.forEach(slot -> {
	            if (!slots.isEmpty()) {
	                Vehicle parkVehicle = slot.getParkVehicle();
	                if (parkVehicle != null)
	                    System.out.printf("   %d       %s\n", slot.getSlotNumber(), parkVehicle.getVehicleNumber());
	            }
	        });
	    }

}
