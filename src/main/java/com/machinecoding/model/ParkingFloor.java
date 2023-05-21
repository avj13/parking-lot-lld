package com.machinecoding.model;

import com.machinecoding.exception.InvalidVehicleNumber;
import com.machinecoding.exception.NoEmptySlotAvailable;
import com.machinecoding.strategy.ParkingCostStrategy;

public interface ParkingFloor {
	boolean createParkingSlot(int numberOfSlots);

    boolean parkVehicle(Vehicle vehicle) throws NoEmptySlotAvailable;

    int unPark(String vehicleNumber, int numberOfHours, ParkingCostStrategy parkingCostStrategy) throws InvalidVehicleNumber;

}
